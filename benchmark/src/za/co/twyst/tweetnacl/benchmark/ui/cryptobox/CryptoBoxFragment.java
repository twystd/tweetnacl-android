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
    
    private long minimum = Long.MAX_VALUE;
    private long maximum = Long.MIN_VALUE;
    private long bytes   = 0;
    private long dt      = 0;
    
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

    private void done(Result result) {
        View view = getView();
        View windmill;

        // ... hide windmill
        
        if (view != null) {
            if ((windmill = view.findViewById(R.id.windmill)) != null) {
                windmill.setVisibility(View.GONE);
            }
        }
        
        // ... update benchmarks

        if (result != null) {
            if (view != null) {
                TextView current = (TextView) view.findViewById(R.id.current);
                TextView average = (TextView) view.findViewById(R.id.average);
                TextView min     = (TextView) view.findViewById(R.id.min);
                TextView max     = (TextView) view.findViewById(R.id.max);

                bytes  += result.bytes;
                dt     += result.duration;

                long throughput = 1000 * result.bytes/result.duration;
                long mean       = 1000 * bytes/dt;
                
                minimum = Math.min(minimum,throughput);
                maximum = Math.max(maximum,throughput);

                current.setText(String.format("%s/s",Util.format(throughput,true)));
                average.setText(String.format("%s/s",Util.format(mean,      true)));
                min.setText    (String.format("%s/s",Util.format(minimum,   true)));
                max.setText    (String.format("%s/s",Util.format(maximum,   true)));
            }
            
        }
    }
    
    // INNER CLASSES
    
    private static class Result { 
        private final long bytes;
        private final long duration;
              
        private Result(long bytes,long duration) {
            this.bytes    = bytes;
            this.duration = duration;
        }
    }
    
    private static class RunTask extends AsyncTask<Void,Void,Result> {
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
        protected Result doInBackground(Void... params) {
            try {
                KeyPair alice   = tweetnacl.cryptoBoxKeyPair();
                KeyPair bob     = tweetnacl.cryptoBoxKeyPair();
                byte[]  message = new byte[bytes];
                byte[]  nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
                long    start   = System.currentTimeMillis();
                long    total   = 0;
                
                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                    
                      total += message.length;
                    }
                
                long   dt = System.currentTimeMillis() - start;
                
                return new Result(total,dt);
                
            } catch(Throwable x) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            CryptoBoxFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result);
            }
        }
    }
}
