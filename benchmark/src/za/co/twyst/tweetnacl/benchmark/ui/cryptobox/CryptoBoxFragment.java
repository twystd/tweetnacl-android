package za.co.twyst.tweetnacl.benchmark.ui.cryptobox;

import java.lang.ref.WeakReference;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.TweetNaCl.KeyPair;
import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.util.Util;

public class CryptoBoxFragment extends Fragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG          = CryptoBoxFragment.class.getSimpleName();
    private static final int    MESSAGE_SIZE = 16384;
    private static final int    LOOPS        = 1024;
    
    // INSTANCE VARIABLES
    
    private Measured encryption = new Measured();
    private Measured decryption = new Measured();
    
    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoBoxFragment();
    }

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View     root  = inflater.inflate(R.layout.fragment_cryptobox,container,false);
        final EditText size  = (EditText) root.findViewById(R.id.size); 
        final EditText loops = (EditText) root.findViewById(R.id.loops); 
        final Button   run   = (Button) root.findViewById(R.id.run);
        
        size.setText (Integer.toString(MESSAGE_SIZE));
        loops.setText(Integer.toString(LOOPS));
        
        run.setOnClickListener(new OnClickListener()
                                   { @Override
                                     public void onClick(View view)
                                            { try
                                                 { int _size  = Integer.parseInt(size.getText ().toString());
                                                   int _loops = Integer.parseInt(loops.getText().toString());
                                                   
                                                   run(_size,_loops);
                                                 }
                                              catch(Throwable x)
                                                 { // TODO
                                                 }
                                            }
                                   });
        
        return root;
    }
    
    // INTERNAL
    
    private void run(int bytes,int loops) {
        new RunTask(this,bytes,loops).execute();
    }
    
    private void busy() {
        View view;
        View windmill;
        
        if ((view = getView()) != null) {
            if ((windmill = view.findViewById(R.id.windmill)) != null) {
                windmill.setVisibility(View.VISIBLE);
            }
        }
    }

    private void done(Result encrypt,Result decrypt) {
        View view = getView();
        View windmill;

        // ... hide windmill
        
        if (view != null) {
            if ((windmill = view.findViewById(R.id.windmill)) != null) {
                windmill.setVisibility(View.GONE);
            }
        }
        
        // ... update benchmarks
        
        encryption.update(encrypt.bytes,encrypt.dt);
        decryption.update(decrypt.bytes,decrypt.dt);

        if (view != null) {
            // ... update encryption results
            
            { TextView current = (TextView) view.findViewById(R.id.encrypt_current);
              TextView average = (TextView) view.findViewById(R.id.encrypt_average);
              TextView min     = (TextView) view.findViewById(R.id.encrypt_min);
              TextView max     = (TextView) view.findViewById(R.id.encrypt_max);

              current.setText(String.format("%s/s",Util.format(encryption.throughput,true)));
              average.setText(String.format("%s/s",Util.format(encryption.mean,      true)));
              min.setText    (String.format("%s/s",Util.format(encryption.minimum,   true)));
              max.setText    (String.format("%s/s",Util.format(encryption.maximum,   true)));
            }
            
            // ... update decryption results
            
            { TextView current = (TextView) view.findViewById(R.id.decrypt_current);
              TextView average = (TextView) view.findViewById(R.id.decrypt_average);
              TextView min     = (TextView) view.findViewById(R.id.decrypt_min);
              TextView max     = (TextView) view.findViewById(R.id.decrypt_max);

              current.setText(String.format("%s/s",Util.format(decryption.throughput,true)));
              average.setText(String.format("%s/s",Util.format(decryption.mean,      true)));
              min.setText    (String.format("%s/s",Util.format(decryption.minimum,   true)));
              max.setText    (String.format("%s/s",Util.format(decryption.maximum,   true)));
            }
        }
    }
    
    // INNER CLASSES
    
    private static class Result { 
        private final long bytes;
        private final long dt;
              
        private Result(long bytes,long dt) {
            this.bytes = bytes;
            this.dt    = dt;
        }
    }

    private static class Measured { 
        public long mean;
        public long throughput;
        private long minimum = Long.MAX_VALUE;
        private long maximum = Long.MIN_VALUE;
        private long bytes   = 0;
        private long dt      = 0;
        
        private void update(long bytes,long dt) {
            this.bytes     += bytes;
            this.dt        += dt;
            this.throughput = 1000 * bytes/dt;
            this.mean       = 1000 * this.bytes/this.dt;
            this.minimum    = Math.min(minimum,throughput);
            this.maximum    = Math.max(maximum,throughput);
        }
    }

    private static class RunTask extends AsyncTask<Void,Void,Result[]> {
        private final WeakReference<CryptoBoxFragment> reference;
        private final int                              bytes;
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;

        private RunTask(CryptoBoxFragment fragment,int bytes,int loops) {
            this.reference = new WeakReference<CryptoBoxFragment>(fragment);
            this.bytes     = bytes;
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }
        @Override
        protected void onPreExecute() {
            CryptoBoxFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.busy();
            }
        }

        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                KeyPair alice   = tweetnacl.cryptoBoxKeyPair();
                KeyPair bob     = tweetnacl.cryptoBoxKeyPair();
                byte[]  message = new byte[bytes];
                byte[]  nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
                byte[]  crypttext;
                long    start;
                long    total;
                
                // ... crypto_box

                start = System.currentTimeMillis();
                total = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                    
                      total += message.length;
                    }
                
                Result encrypt = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_box_open
                
                crypttext = tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { message = tweetnacl.cryptoBoxOpen(crypttext,nonce,alice.publicKey,bob.secretKey);
                  
                      total += message.length;
                    }

                Result decrypt = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { encrypt,decrypt };
                
            } catch(Throwable x) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Result[] result) {
            CryptoBoxFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result[0],result[1]);
            }
        }
    }
}
