package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.entity.Measured;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;
import za.co.twyst.tweetnacl.benchmark.util.Util;

/** Abstract base class for crypto_xxx fragments. Defines the Owner interface
 *  used to update the summary result page.
 *  
 */
public abstract class CryptoFragment extends Fragment {
    // CONSTANTS

    private static final String TAG = CryptoFragment.class.getSimpleName();

    // INSTANCE VARIABLES

    private final int                  layout;
    private       WeakReference<Owner> owner = new WeakReference<Owner>(null);

    protected final Measured[] measurements;

    // CLASS METHODS
    
    /** Pretty formats a throughput value.
     * 
     * @param throughput 
     *            Measured throughput value in bytes/s.
     *            
     * @return Throughput formatted as a human-readable value.           
     */
    protected static String format(long throughput) {
        return String.format("%s/s",Util.format(throughput,true));
    }

    // CONSTRUCTOR
    
    protected CryptoFragment(int layout,Measured...measurements) {
        this.layout       = layout;
        this.measurements = measurements;
    }
    
    // *** Fragment ****
    
    /** Stores the reference to the 'owning' Activity.
     * 
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            this.owner = new WeakReference<Owner>((Owner) activity);
        } catch(Throwable x) {
            Log.w(TAG, "Error assigning 'owner' activity",x);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View        root  = inflater.inflate(layout,container,false);
        
//        final EditText    size  = (EditText) root.findViewById(R.id.size); 
//        final EditText    loops = (EditText) root.findViewById(R.id.loops); 
//        final Button      run   = (Button) root.findViewById(R.id.run);
//        final Grid        grid  = (Grid) root.findViewById(R.id.grid);
//        final ProgressBar bar   = (ProgressBar) root.findViewById(R.id.progressbar);
//
//        // ... initialise default setup
//        
//        size.setText (Integer.toString(MESSAGE_SIZE));
//        loops.setText(Integer.toString(LOOPS));
//
//        // ... initialise grid
//        
//        grid.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
//        grid.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
//        grid.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);
//        
//        // ... attach handlers
//        
//        run.setOnClickListener(new OnClickListener()
//                                   { @Override
//                                     public void onClick(View view)
//                                            { try
//                                                 { int _size  = Integer.parseInt(size.getText ().toString());
//                                                   int _loops = Integer.parseInt(loops.getText().toString());
//                                                   
//                                                   hideKeyboard(size,loops);
//                                                   run         (_size,_loops,bar);
//                                                 }
//                                              catch(Throwable x)
//                                                 { // TODO
//                                                 }
//                                            }
//                                   });
        
        return root;
    }

    // UTILITY METHODS
    
    /** Hides keyboard for an EditText field
     * 
     * @param fields 
     *            List of EditText fields for which to hide the keyboard.
     */
    protected void hideKeyboard(EditText... fields) { 
        try { 
            Context            context = getActivity();
            InputMethodManager imm     = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

            for (EditText field: fields) {
                  imm.hideSoftInputFromWindow(field.getWindowToken(),0);
            }
        } catch(Throwable x) { 
            Log.w(TAG,"Error hiding keyboard",x);
        }
    }

    /** Displays the 'busy' windmill and overlay.
     * 
     */
    protected void busy() {
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

    /** Updates the containing activity with the measurement.
     * 
     * @param measurements 
     *            New measurements to add to global results. Ignored if
     *            <code>null</code>.        
     */
    protected void measured(Measured... measurements) {
        Owner owner;

        if (measurements != null) {
           if ((owner = this.owner.get()) != null) {
               for (Measured measurement: measurements) {
                   owner.measured(new Benchmark(measurement));
               }
           }
        }
    }

    /** Updates the containing activity with the measurement.
     * 
     * @param measurements 
     *            List of measurements to add to global performance statistics.
     */
    protected void measured(Benchmark... measurements) {
        Owner owner;

        if (measurements != null) {
           if ((owner = this.owner.get()) != null) {
               owner.measured(measurements);
           }
        }
    }
    
    /** Updates the displayed benchmark.
     * 
     * @param results 
     *            List of measured performance results.
     */
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
        
        Grid grid;
        
        if ((view != null) && ((grid = (Grid) view.findViewById(R.id.grid)) != null)) {
            ix = 0;
            
            for (int column=0; column<results.length; column++) {
                Measured measurement = measurements[column];
                
                grid.setValue(0,column,format(measurement.throughput));
                grid.setValue(1,column,format(measurement.mean));
                grid.setValue(2,column,format(measurement.minimum));
                grid.setValue(3,column,format(measurement.maximum));
            }
        }
        
        // ... update global measurements
        
        this.measured(measurements);
    }
    
    // INNER CLASSES
    
    /** Interface definition for the 'owning' Activity to update the global performance
     *  statistics.
     *
     */
    public interface Owner {
        public void measured(Benchmark... measurments);
    }
    
    /** Container class for the results of a single run.
     * 
     */
    protected static class Result { 
        protected final long bytes;
        protected final long dt;
              
        protected Result(long bytes,long dt) {
            this.bytes = bytes;
            this.dt    = dt;
        }
    }

    /** Base class for the a crypto_xxx specific performance test. Implements the common
     *  pre-, post and progress update functionality.
     * 
     */
    protected static abstract class CryptoTask extends AsyncTask<Void,Integer,Result[]> {
        private final WeakReference<CryptoFragment> reference;
        private final WeakReference<ProgressBar>       bar;

        protected CryptoTask(CryptoFragment fragment,ProgressBar bar) {
            this.reference = new WeakReference<CryptoFragment>(fragment);
            this.bar       = new WeakReference<ProgressBar>(bar);
        }
        
        protected void progress(int progress,int max) {
            publishProgress(progress*1000/max);
        }

        // *** AsyncTask ***
        
        @Override
        protected void onPreExecute() {
            CryptoFragment fragment = this.reference.get();
            ProgressBar    bar      = this.bar.get();

            if (fragment != null) {
                fragment.busy();
            }

            if (bar != null) {
                bar.setProgress(0);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int         progress = values[0];
            ProgressBar bar      = this.bar.get();
            
            if (bar != null) {
                bar.setProgress(progress);
            }
        }

        @Override
        protected void onPostExecute(Result[] result) {
            CryptoFragment fragment = reference.get();
            
            if (fragment != null) {
                fragment.done(result);
            }
        }
    }

}
