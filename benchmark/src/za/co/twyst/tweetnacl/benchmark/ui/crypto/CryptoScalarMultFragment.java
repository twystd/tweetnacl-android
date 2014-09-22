package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.os.AsyncTask;
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
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
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
    
    // INSTANCE VARIABLES
    
    private Measured scalarmultbase = new Measured();
    private Measured scalarmult     = new Measured();
    
    // CLASS METHODS

    /** Factory constructor for CryptoScalarMultFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoScalarMultFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoScalarMultFragment();
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
        new RunTask(this,bar,loops).execute();
    }
    
    private void done(Result hsalsa20,Result salsa20) {
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
        
        this.scalarmultbase.update(hsalsa20.bytes,hsalsa20.dt);
        this.scalarmult.update    (salsa20.bytes,salsa20.dt);


        if (view != null) {
            Grid grid = (Grid) view.findViewById(R.id.grid);
            
            grid.setValue(0,0,format(this.scalarmultbase.throughput));
            grid.setValue(1,0,format(this.scalarmultbase.mean));
            grid.setValue(2,0,format(this.scalarmultbase.minimum));
            grid.setValue(3,0,format(this.scalarmultbase.maximum));
            
            grid.setValue(0,1,format(this.scalarmult.throughput));
            grid.setValue(1,1,format(this.scalarmult.mean));
            grid.setValue(2,1,format(this.scalarmult.minimum));
            grid.setValue(3,1,format(this.scalarmult.maximum));
        }
        
        // ... update global measurements
        
        this.measured(new Benchmark(TYPE.CRYPTO_SCALARMULT_BASE,format(this.scalarmultbase.mean)),
                      new Benchmark(TYPE.CRYPTO_SCALARMULT,     format(this.scalarmult.mean)));
    }
    
    // INNER CLASSES
    
    private static class RunTask extends AsyncTask<Void,Integer,Result[]> {
        private final WeakReference<CryptoScalarMultFragment> reference;
        private final WeakReference<ProgressBar>       bar;
        private final int                              loops;
        private final TweetNaCl                        tweetnacl;

        private RunTask(CryptoScalarMultFragment fragment,ProgressBar bar,int loops) {
            this.reference = new WeakReference<CryptoScalarMultFragment>(fragment);
            this.bar       = new WeakReference<ProgressBar>(bar);
            this.loops     = loops;
            this.tweetnacl = new TweetNaCl();
        }
        @Override
        protected void onPreExecute() {
            CryptoScalarMultFragment fragment = this.reference.get();
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
                      publishProgress(++progress);
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
                      publishProgress(++progress);
                    }

                Result scalarmult = new Result(total,System.currentTimeMillis() - start);

                // ... done
                
                return new Result[] { scalarmultbase,scalarmult };
                
            } catch(Throwable x) {
                Log.e(TAG,"Error running crypto_core benchmark",x);
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
            CryptoScalarMultFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result[0],result[1]);
            }
        }
    }
}
