package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.os.AsyncTask;
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

    // INSTANCE VARIABLES
    
    private Measured auth   = new Measured();
    private Measured verify = new Measured();
    
    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoOneTimeAuthFragment();
    }

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(R.layout.fragment_cryptobox,container,false);
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
        new RunTask(this,bar,bytes,loops).execute();
    }
    
    private void busy() {
        View view;
        View busy;
        View bar;
        
        if ((view = getView()) != null) {
            if ((busy = view.findViewById(R.id.busy)) != null) {
                busy.setVisibility(View.VISIBLE);
            }
            
            if ((bar = view.findViewById(R.id.progressbar)) != null) {
                bar.setVisibility(View.VISIBLE);
            }
        }
    }

    private void done(Result auth,Result verify) {
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
        
        this.auth.update  (auth.bytes,auth.dt);
        this.verify.update(verify.bytes,verify.dt);

        if (view != null) {
            Grid grid = (Grid) view.findViewById(R.id.grid);
            
            grid.setValue(0,0,format(this.auth.throughput));
            grid.setValue(1,0,format(this.auth.mean));
            grid.setValue(2,0,format(this.auth.minimum));
            grid.setValue(3,0,format(this.auth.maximum));
            
            grid.setValue(0,1,format(this.verify.throughput));
            grid.setValue(1,1,format(this.verify.mean));
            grid.setValue(2,1,format(this.verify.minimum));
            grid.setValue(3,1,format(this.verify.maximum));
        }
        
        // ... update global measurements
        
        this.measured(new Benchmark(TYPE.CRYPTO_ONETIMEAUTH,       format(this.auth.mean)),
                      new Benchmark(TYPE.CRYPTO_ONETIMEAUTH_VERIFY,format(this.verify.mean)));
    }
    
    // INNER CLASSES
    
    private static class RunTask extends AsyncTask<Void,Integer,Result[]> {
        private final WeakReference<CryptoOneTimeAuthFragment> reference;
        private final WeakReference<ProgressBar>       bar;
        private final int                              bytes;
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;

        private RunTask(CryptoOneTimeAuthFragment fragment,ProgressBar bar,int bytes,int loops) {
            this.reference = new WeakReference<CryptoOneTimeAuthFragment>(fragment);
            this.bar       = new WeakReference<ProgressBar>(bar);
            this.bytes     = bytes;
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }
        
        @Override
        protected void onPreExecute() {
            CryptoOneTimeAuthFragment fragment = this.reference.get();
            ProgressBar       bar      = this.bar.get();

            if (fragment != null) {
                fragment.busy();
            }

            if (bar != null) {
                bar.setProgress(0);
            }
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
                    publishProgress(++progress);
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
                    publishProgress(++progress);
                }

                Result decrypt = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { encrypt,decrypt };
                
            } catch(Throwable x) {
            }

            return null;
        }
        
        @Override
        protected void onProgressUpdate(Integer... values) {
            int         progress = values[0];
            ProgressBar bar      = this.bar.get();
            
            if (bar != null) {
                bar.setProgress(1000*progress/(2*loops));
            }
        }

        @Override
        protected void onPostExecute(Result[] result) {
            CryptoOneTimeAuthFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result[0],result[1]);
            }
        }
    }
}
