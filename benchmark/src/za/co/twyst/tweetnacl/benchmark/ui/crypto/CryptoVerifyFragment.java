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

public class CryptoVerifyFragment extends CryptoFragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG   = CryptoVerifyFragment.class.getSimpleName();
    private static final int    LOOPS = 65536;
    
    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_verify16, 
                                           R.string.column_verify32 
                                         };

    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoVerifyFragment();
    }
    
    // CONSTRUCTOR
    
    public CryptoVerifyFragment() {
        super(new Measured(TYPE.CRYPTO_VERIFY16),new Measured(TYPE.CRYPTO_VERIFY32));
    }
    
    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(R.layout.fragment_verify,container,false);
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
        new CryptoVerifyTask(this,bar,loops).execute();
    }
    
    // INNER CLASSES
    
    private static class CryptoVerifyTask extends CryptoTask {
        private final int       loops;
        private final TweetNaCl tweetnacl;

        private CryptoVerifyTask(CryptoVerifyFragment fragment,ProgressBar bar,int loops) {
            super(fragment,bar);
            
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }
        
        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                Random   random  = new Random();
                Result[] results = new Result[2];
                byte[]   x16     = new byte[16];
                byte[]   x32     = new byte[32];
                byte[]   y;
                long     start;
                long     total;
                int      progress;

                random.nextBytes(x16);
                random.nextBytes(x32);

                // ... crypto_verify16

                y        = x16.clone();
                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoVerify16(x16,y);
                    
                      total += x16.length;
                      progress(++progress,2*loops);
                    }
                
                results[0] = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_verify32
                
                y      = x32.clone();
                start  = System.currentTimeMillis();
                total  = 0;
                
                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoVerify32(x32,y);
                  
                      total += x32.length;
                      progress(++progress,2*loops);
                    }

                results[1] = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return results;
                
            } catch(Throwable x) {
            }

            return null;
        }
    }
}
