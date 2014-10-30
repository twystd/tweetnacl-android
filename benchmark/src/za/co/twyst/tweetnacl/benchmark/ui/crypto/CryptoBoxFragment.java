package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import static za.co.twyst.tweetnacl.TweetNaClZ.BOX_ZEROBYTES;

import java.util.Arrays;
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
import za.co.twyst.tweetnacl.TweetNaClZ;
import za.co.twyst.tweetnacl.TweetNaCl.KeyPair;
import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Measured;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.LIBRARY;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

/** Performance evaluation CryptoFragment implementation for the crypto_box 
 *  and crypto_box_open functions.
 *  
 */
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

    private static final int[] COLUMNS = { R.string.column_tweetnacl, 
                                           R.string.column_tweetnaclz 
                                         };

    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoBoxFragment();
    }
    
    // CONSTRUCTOR
    
    public CryptoBoxFragment() {
        super(R.layout.fragment_box,
              new Measured(TYPE.CRYPTO_BOX,LIBRARY.TWEETNACL),
              new Measured(TYPE.CRYPTO_BOX,LIBRARY.TWEETNACLZ),
              new Measured(TYPE.CRYPTO_BOX_OPEN,LIBRARY.TWEETNACL),
              new Measured(TYPE.CRYPTO_BOX_OPEN,LIBRARY.TWEETNACLZ));
    }
    
    // *** CryptoFragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root          = super.onCreateView(inflater,container,state);
        final EditText    size          = (EditText) root.findViewById(R.id.size); 
        final EditText    loops         = (EditText) root.findViewById(R.id.loops); 
        final Button      run           = (Button) root.findViewById(R.id.run);
        final ProgressBar bar           = (ProgressBar) root.findViewById(R.id.progressbar);
        final Grid        cryptoBox     = (Grid) root.findViewById(R.id.crypto_box);
        final Grid        cryptoBoxOpen = (Grid) root.findViewById(R.id.crypto_box_open);

        // ... initialise default setup
        
        size.setText (Integer.toString(MESSAGE_SIZE));
        loops.setText(Integer.toString(LOOPS));

        // ... initialise grids
        
        cryptoBox.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
        cryptoBox.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        cryptoBox.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);

        cryptoBoxOpen.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
        cryptoBoxOpen.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        cryptoBoxOpen.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);

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
    
    /** Updates the displayed benchmark.
     * 
     * @param results 
     *            List of measured performance results.
     */
    @SuppressWarnings("incomplete-switch")
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

        int  ix   = 0;

        for (Result result: results) {
            Measured measurement = measurements[ix++];
        
            measurement.update(result.bytes,result.dt);
        }

        // ... update page
        
        if (view != null) {
            Grid cryptoBox     = (Grid) view.findViewById(R.id.crypto_box);
            Grid cryptoBoxOpen = (Grid) view.findViewById(R.id.crypto_box_open);
            
            ix = 0;
            
            for (int column=0; column<results.length; column++) {
                Measured measurement = measurements[column];
                Grid     grid        = null;
                
                switch(measurement.type) {
                    case CRYPTO_BOX:
                        grid = cryptoBox;
                        break;

                    case CRYPTO_BOX_OPEN:
                        grid = cryptoBoxOpen;
                        break;
                }
                
                if (grid != null) {
                    switch(measurement.library) {
                        case TWEETNACL:
                            grid.setValue(0,0,format(measurement.throughput));
                            grid.setValue(1,0,format(measurement.mean));
                            grid.setValue(2,0,format(measurement.minimum));
                            grid.setValue(3,0,format(measurement.maximum));
                            break;
                            
                        case TWEETNACLZ:
                            grid.setValue(0,1,format(measurement.throughput));
                            grid.setValue(1,1,format(measurement.mean));
                            grid.setValue(2,1,format(measurement.minimum));
                            grid.setValue(3,1,format(measurement.maximum));
                            break;
                    }
                }
            }
        }
        
        // ... update global measurements
        
        this.measured(measurements);
    }

    // INTERNAL
    
    private void run(int bytes,int loops,ProgressBar bar) {
        new CryptoBoxTask(this,bar,bytes,loops).execute();
    }
    
    // INNER CLASSES
    
    private static class CryptoBoxTask extends CryptoTask {
        private final int                              bytes;
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;
        private final TweetNaClZ                       tweetnaclz;

        private CryptoBoxTask(CryptoBoxFragment fragment,ProgressBar bar,int bytes,int loops) {
            super(fragment,bar);
            
            this.bytes      = bytes;
            this.loops      = loops;
            this.tweetnacl  = new TweetNaCl ();
            this.tweetnaclz = new TweetNaClZ();
        }
        
        @Override
        protected Result[] doInBackground(Void... params) {
            try {
                // ... initialise
                
                Random  random   = new Random();
                KeyPair alice    = tweetnacl.cryptoBoxKeyPair();
                KeyPair bob      = tweetnacl.cryptoBoxKeyPair();
                byte[]  message  = new byte[bytes];
                byte[]  messagez = new byte[message.length + BOX_ZEROBYTES];
                byte[]  nonce    = new byte[TweetNaCl.BOX_NONCEBYTES];
                byte[]  crypttext;
                long    start;
                long    total;
                int     progress;
                
                random.nextBytes(message);
                
                Arrays.fill     (messagez,(byte) 0x00);
                System.arraycopy(message,0,messagez,BOX_ZEROBYTES,message.length);
                
                // ... crypto_box

                start    = System.currentTimeMillis();
                total    = 0;
                progress = 0;

                for (int i=0; i<loops; i++)
                    { tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                    
                      total += message.length;
                      progress(++progress,4*loops);
                    }
                
                Result encrypt = new Result(total,System.currentTimeMillis() - start);

                // ... crypto_box/z

                start = System.currentTimeMillis();
                total = 0;

                for (int i=0; i<loops; i++)
                    { tweetnaclz.cryptoBox(messagez,nonce,bob.publicKey,alice.secretKey);
                    
                      total += message.length;
                      progress(++progress,4*loops);
                    }
                
                Result encryptz = new Result(total,System.currentTimeMillis() - start);

                // ... crypto_box_open
                
                crypttext = tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { message = tweetnacl.cryptoBoxOpen(crypttext,nonce,alice.publicKey,bob.secretKey);
                  
                      total += message.length;
                      progress(++progress,4*loops);
                    }

                Result decrypt = new Result(total,System.currentTimeMillis() - start);
                
                // ... crypto_box_open/z
                
                crypttext = tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);
                start     = System.currentTimeMillis();
                total     = 0;
                
                for (int i=0; i<loops; i++)
                    { message = tweetnaclz.cryptoBoxOpen(crypttext,nonce,alice.publicKey,bob.secretKey);
                  
                      total += message.length;
                      progress(++progress,4*loops);
                    }

                Result decryptz = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { encrypt,encryptz,decrypt,decryptz };
                
            } catch(Throwable x) {
            }

            return null;
        }
    }
}
