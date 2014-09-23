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
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class CryptoSecretBoxFragment extends CryptoFragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG          = CryptoSecretBoxFragment.class.getSimpleName();
    private static final int    MESSAGE_SIZE = 16384;
    private static final int    LOOPS        = 1024;
    private static final byte[] KEY          = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64,
                                                 (byte) 0x73, (byte) 0xe9, (byte) 0x85, (byte) 0xd4,
                                                 (byte) 0x62, (byte) 0xcd, (byte) 0x51, (byte) 0x19,
                                                 (byte) 0x7a, (byte) 0x9a, (byte) 0x46, (byte) 0xc7,
                                                 (byte) 0x60, (byte) 0x09, (byte) 0x54, (byte) 0x9e,
                                                 (byte) 0xac, (byte) 0x64, (byte) 0x74, (byte) 0xf2,
                                                 (byte) 0x06, (byte) 0xc4, (byte) 0xee, (byte) 0x08,
                                                 (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89 };

    private static final int[] ROWS    = { R.string.results_measured,
                                           R.string.results_average,
                                           R.string.results_min,
                                           R.string.results_max
                                         };

    private static final int[] COLUMNS = { R.string.column_secretbox, 
                                           R.string.column_secretbox_open 
                                         };

    
    // INSTANCE VARIABLES
    
    private Measured[] measured = { new Measured(),
                                    new Measured()
                                  };
    
    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoSecretBoxFragment();
    }
    
    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(R.layout.fragment_box,container,false);
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
                                                 { // IGNORE
                                                 }
                                            }
                                   });
        
        return root;
    }
    
    // INTERNAL
    
    private void run(int bytes,int loops,ProgressBar bar) {
        new CryptoSecretBoxTask(this,bar,bytes,loops).execute();
    }
    
    @Override
    protected void done(Result...results) {
        View view = getView();
        View busy;
        View bar;

        // ... hide windmill
        
        if (view != null) {
            if ((busy = view.findViewById(R.id.busy)) != null) {
                busy.setVisibility(View.GONE);
            }
            
            if ((bar = view.findViewById(R.id.progressbar)) != null) {
                bar.setVisibility(View.VISIBLE);
            }
        }
        
        // ... update benchmarks

        Grid grid = (Grid) view.findViewById(R.id.grid);

        for (int i=0; i<measured.length; i++) {
            measured[i].update(results[i].bytes,results[i].dt);

            grid.setValue(0,i,format(measured[i].throughput));
            grid.setValue(1,i,format(measured[i].mean));
            grid.setValue(2,i,format(measured[i].minimum));
            grid.setValue(3,i,format(measured[i].maximum));
        }
        
        // ... update global measurements
        
        this.measured(new Benchmark(TYPE.CRYPTO_SECRETBOX,     format(measured[0].mean)),
                      new Benchmark(TYPE.CRYPTO_SECRETBOX_OPEN,format(measured[1].mean)));
    }
    
    // INNER CLASSES
    
    private static class CryptoSecretBoxTask extends RunTask {
        private final int       bytes;
        private final int       loops;
        private final TweetNaCl tweetnacl;

        private CryptoSecretBoxTask(CryptoSecretBoxFragment fragment,ProgressBar bar,int bytes,int loops) {
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
                byte[]  nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
                byte[]  crypttext;
                long    start;
                long    total;
                int     progress;
                
                random.nextBytes(message);
                
                // ... crypto_secretbox

                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoSecretBox(message,nonce,KEY);
                    
                      total += message.length;
                      publishProgress(++progress/(2*loops));
                    }
                
                Result encrypt = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_secretbox_open
                
                crypttext = tweetnacl.cryptoSecretBox(message,nonce,KEY);
                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { message = tweetnacl.cryptoSecretBoxOpen(crypttext,nonce,KEY);
                  
                      total += message.length;
                      publishProgress(++progress/(2*loops));
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
