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
import za.co.twyst.tweetnacl.benchmark.entity.Measured;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class CryptoCoreFragment extends CryptoFragment {
    // CONSTANTS

    private static final String TAG   = CryptoCoreFragment.class.getSimpleName();
    private static final int    LOOPS = 65536;
    
    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_hsalsa20, 
                                           R.string.column_salsa20 
                                         };
    
    private static final byte[] KEY = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d, (byte) 0x5b, 
                                        (byte) 0xa4, (byte) 0xce, (byte) 0x2d, (byte) 0xe1,
                                        (byte) 0x72, (byte) 0x8e, (byte) 0x3b, (byte) 0xf4,
                                        (byte) 0x80, (byte) 0x35, (byte) 0x0f, (byte) 0x25,
                                        (byte) 0xe0, (byte) 0x7e, (byte) 0x21, (byte) 0xc9,
                                        (byte) 0x47, (byte) 0xd1, (byte) 0x9e, (byte) 0x33,
                                        (byte) 0x76, (byte) 0xf0, (byte) 0x9b, (byte) 0x3c,
                                        (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42 
                                      };

    private static final byte[] CONSTANT = { (byte) 0x65, (byte) 0x78, (byte) 0x70, (byte) 0x61,
                                             (byte) 0x6e, (byte) 0x64, (byte) 0x20, (byte) 0x33,
                                             (byte) 0x32, (byte) 0x2d, (byte) 0x62, (byte) 0x79,
                                             (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6b
                                           };
    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoCoreFragment();
    }
    
    // CONSTRUCTOR
    
    public CryptoCoreFragment() {
        super(new Measured(TYPE.CRYPTO_CORE_HSALSA20),new Measured(TYPE.CRYPTO_CORE_SALSA20));
    }
    

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(R.layout.fragment_core,container,false);
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
        new CryptoCoreTask(this,bar,loops).execute();
    }
    
    // INNER CLASSES
    
    private static class CryptoCoreTask extends RunTask  {
        private final int       loops;
        private final TweetNaCl tweetnacl;

        private CryptoCoreTask(CryptoCoreFragment fragment,ProgressBar bar,int loops) {
            super(fragment,bar);

            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }

        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                Random  random  = new Random();
                byte[]  message;
                long    start;
                long    total;
                int     progress;
                
                // ... crypto_core_hsalsa20

                message = new byte[TweetNaCl.HSALSA20_INPUTBYTES];
                
                random.nextBytes(message);

                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoCoreHSalsa20(message,KEY,CONSTANT);
                      total += message.length;
                      progress(++progress,2*loops);
                    }
                
                Result hsalsa20 = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_core_salsa20

                message = new byte[TweetNaCl.SALSA20_INPUTBYTES];
                
                random.nextBytes(message);

                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoCoreSalsa20(message,KEY,CONSTANT);
                      total += message.length;
                      progress(++progress,2*loops);
                    }

                Result salsa20 = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { hsalsa20,salsa20 };
                
            } catch(Throwable x) {
                Log.e(TAG,"Error running crypto_core benchmark",x);
            }

            return null;
        }
    }
}
