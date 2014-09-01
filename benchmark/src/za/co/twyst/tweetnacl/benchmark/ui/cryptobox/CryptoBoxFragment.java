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

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.TweetNaCl.KeyPair;
import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;
import za.co.twyst.tweetnacl.benchmark.util.Util;

public class CryptoBoxFragment extends CryptoFragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG          = CryptoBoxFragment.class.getSimpleName();
    private static final int    MESSAGE_SIZE = 16384;
    private static final int    LOOPS        = 1024;
    
    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_encrypt, 
                                           R.string.column_decrypt 
                                         };

    
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
        final Grid     grid  = (Grid) root.findViewById(R.id.grid);

        // ... initialise default setup
        
        size.setText (Integer.toString(MESSAGE_SIZE));
        loops.setText(Integer.toString(LOOPS));

        // ... initialise grid
        
        grid.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
        grid.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        grid.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);
        
        // ... attach handlers
        
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
            Grid grid = (Grid) view.findViewById(R.id.grid);
            
            grid.setValue(0,0,String.format("%s/s",Util.format(encryption.throughput,true)));
            grid.setValue(1,0,String.format("%s/s",Util.format(encryption.mean,      true)));
            grid.setValue(2,0,String.format("%s/s",Util.format(encryption.minimum,   true)));
            grid.setValue(3,0,String.format("%s/s",Util.format(encryption.maximum,   true)));
            
            grid.setValue(0,1,String.format("%s/s",Util.format(decryption.throughput,true)));
            grid.setValue(1,1,String.format("%s/s",Util.format(decryption.mean,      true)));
            grid.setValue(2,1,String.format("%s/s",Util.format(decryption.minimum,   true)));
            grid.setValue(3,1,String.format("%s/s",Util.format(decryption.maximum,   true)));
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
