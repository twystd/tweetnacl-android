package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class CryptoScalarMultFragment extends CryptoFragment {
    // CONSTANTS

    private static final String TAG   = CryptoScalarMultFragment.class.getSimpleName();
    private static final int    LOOPS = 16384;
    
    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_scalarmultbase, 
                                           R.string.column_scalarmult 
                                         };
    // CLASS METHODS

    /** Factory constructor for CryptoScalarMultFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoScalarMultFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoScalarMultFragment();
    }

    // CONSTRUCTOR
    
    public CryptoScalarMultFragment() {
        super(new Measured(TYPE.CRYPTO_SCALARMULT_BASE),new Measured(TYPE.CRYPTO_SCALARMULT));
    }
    
    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(R.layout.fragment_scalarmult,container,false);
        final EditText    loops = (EditText) root.findViewById(R.id.loops); 
        final Button      run   = (Button) root.findViewById(R.id.run);
        final Grid        grid  = (Grid) root.findViewById(R.id.grid);
        final ProgressBar bar   = (ProgressBar) root.findViewById(R.id.progressbar);

        // ... initialise default setup
        
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
                                                 { int _loops = Integer.parseInt(loops.getText().toString());
                                                   
                                                   hideKeyboard(loops);
                                                   run         (_loops,bar);
                                                 }
                                              catch(Throwable x)
                                                 { // TODO
                                                 }
                                            }
                                   });
        
        return root;
    }
    
    // INTERNAL
    
    private void run(int loops,ProgressBar bar) {
        new CryptoScalarMultTask(this,bar,loops).execute();
    }
    
    // INNER CLASSES
    
    private static class CryptoScalarMultTask extends RunTask {
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;

        private CryptoScalarMultTask(CryptoScalarMultFragment fragment,ProgressBar bar,int loops) {
            super(fragment,bar);
            
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }

        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                Random  random  = new Random();
                byte[]  n       = new byte[TweetNaCl.SCALARMULT_SCALARBYTES];
                byte[]  p       = new byte[TweetNaCl.SCALARMULT_BYTES];
                long    start;
                long    total;
                int     progress;
                
                // ... crypto_scalarmultbase

                random.nextBytes(n);

                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoScalarMultBase(n);
                      total += n.length;
                      progress(++progress,2*loops);
                    }
                
                Result scalarmultbase = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_scalarmult

                random.nextBytes(n);
                random.nextBytes(p);

                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoScalarMult(n,p);
                      total += n.length;
                      progress(++progress,2*loops);
                    }

                Result scalarmult = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { scalarmultbase,scalarmult };
                
            } catch(Throwable x) {
                Log.e(TAG,"Error running crypto_core benchmark",x);
            }

            return null;
        }
    }
}
