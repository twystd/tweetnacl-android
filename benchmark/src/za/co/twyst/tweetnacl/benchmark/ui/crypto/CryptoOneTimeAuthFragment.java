package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Measured;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class CryptoOneTimeAuthFragment extends CryptoFragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG          = CryptoOneTimeAuthFragment.class.getSimpleName();
    private static final int    MESSAGE_SIZE = 16384;
    private static final int    LOOPS        = 1024;
    
    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_onetimeauth, 
                                           R.string.column_onetimeauth_verify 
                                         };
    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoOneTimeAuthFragment();
    }

    // CONSTRUCTOR
    
    public CryptoOneTimeAuthFragment() {
        super(R.layout.fragment_onetimeauth,
              new Measured(TYPE.CRYPTO_ONETIMEAUTH),new Measured(TYPE.CRYPTO_ONETIMEAUTH_VERIFY));
    }
    
    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = super.onCreateView(inflater,container,state);
        final EditText    size  = (EditText) root.findViewById(R.id.size); 
        final EditText    loops = (EditText) root.findViewById(R.id.loops); 
        final Button      run   = (Button) root.findViewById(R.id.run);
        final Grid        grid  = (Grid) root.findViewById(R.id.grid);
        final ProgressBar bar   = (ProgressBar) root.findViewById(R.id.progressbar);

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
                                                   
                                                   hideKeyboard(size,loops);
                                                   run         (_size,_loops,bar);
                                                 }
                                              catch(Throwable x)
                                                 { // TODO
                                                 }
                                            }
                                   });
        
        return root;
    }
    
    // INTERNAL
    
    private void run(int bytes,int loops,ProgressBar bar) {
        new CryptoOneTimeAuthTask(this,bar,bytes,loops).execute();
    }
    
    // INNER CLASSES
    
    private static class CryptoOneTimeAuthTask extends CryptoTask {
        private final int       bytes;
        private final int       loops;
        private final TweetNaCl tweetnacl;

        private CryptoOneTimeAuthTask(CryptoOneTimeAuthFragment fragment,ProgressBar bar,int bytes,int loops) {
            super(fragment,bar);

            this.bytes     = bytes;
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }
        
        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                Random  random  = new Random();
                byte[]  message = new byte[bytes];
                byte[]  key     = new byte[TweetNaCl.ONETIMEAUTH_KEYBYTES];
                byte[]  auth;
                long    start;
                long    total;
                int     progress;
                
                random.nextBytes(key);
                random.nextBytes(message);

                // ... crypto_onetimeauth

                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++) { 
                    if (tweetnacl.cryptoOneTimeAuth(message,key) == null) {
                        throw new Exception("Invalid onetime_auth result");
                    }
                    
                    total += message.length;
                    progress(++progress,2*loops);
                }
                
                Result encrypt = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_onetimeauth_verify
                
                auth  = tweetnacl.cryptoOneTimeAuth(message, key);
                start = System.currentTimeMillis();
                total = 0;
                
                for (int i=0; i<loops; i++) { 
                    if (!tweetnacl.cryptoOneTimeAuthVerify(auth,message,key)) {
                        throw new Exception("onetimeauth_verify failed");
                    }
                  
                    total += message.length;
                    progress(++progress,2*loops);
                }

                Result decrypt = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { encrypt,decrypt };
                
            } catch(Throwable x) {
            }

            return null;
        }
    }
}
