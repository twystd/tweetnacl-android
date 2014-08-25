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
import android.widget.TextView;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.TweetNaCl.KeyPair;
import za.co.twyst.tweetnacl.benchmark.R;

public class CryptoBoxFragment extends Fragment {
    // CONSTANTS

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
        View   root = inflater.inflate(R.layout.fragment_cryptobox,container,false);
        Button run  = (Button) root.findViewById(R.id.run);
        
        run.setOnClickListener(new OnClickListener()
                                   { @Override
                                     public void onClick(View view)
                                            { run(1024);
                                            }
                                   });
        
        return root;
    }
    
    // INTERNAL
    
    private void run(int loops) {
        new RunTask(this,loops).execute();
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

    private void done(Double ops) {
        View view = getView();
        View windmill;

        // ... hide windmill
        
        if (view != null) {
            if ((windmill = view.findViewById(R.id.windmill)) != null) {
                windmill.setVisibility(View.GONE);
            }
        }
        
        // ... update benchmarks

        TextView operations;
        
        if (ops != null) {
            if (view != null) {
                if ((operations = (TextView) view.findViewById(R.id.ops)) != null) {
                    operations.setText(String.format("%.2f",ops.doubleValue()));
                }
            }
            
        }
    }
    
    // INNER CLASSES
    
    private static class RunTask extends AsyncTask<Void,Void,Double> {
        private final WeakReference<CryptoBoxFragment> reference;
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;

        private RunTask(CryptoBoxFragment fragment,int loops) {
            this.reference = new WeakReference<CryptoBoxFragment>(fragment);
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
        protected Double doInBackground(Void... params) {
            try {
                KeyPair alice   = tweetnacl.cryptoBoxKeyPair();
                KeyPair bob     = tweetnacl.cryptoBoxKeyPair();
                byte[]  message = new byte[16384];
                byte[]  nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
                long    start   = System.currentTimeMillis();
                
                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                    }
                
                long   dt  = System.currentTimeMillis() - start;
                double ops = 1000.0 * (double) loops/(double) dt;
                
                return ops;
                
            } catch(Throwable x) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Double result) {
            CryptoBoxFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result);
            }
        }
    }
}
