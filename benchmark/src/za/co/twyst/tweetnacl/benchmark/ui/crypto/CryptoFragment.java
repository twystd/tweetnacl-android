package za.co.twyst.tweetnacl.benchmark.ui.crypto;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.util.Util;

/** Abstract base class for crypto_xxx fragments. Defines the Owner interface
 *  used to update the summary result page.
 *  
 */
public abstract class CryptoFragment extends Fragment {
    // CONSTANTS

    private static final String TAG = CryptoFragment.class.getSimpleName();

    // INSTANCE VARIABLES
    
    private WeakReference<Owner> owner = new WeakReference<Owner>(null);
    
    // CLASS METHODS
    
    /** Pretty formats a throughput value.
     * 
     */
    protected static String format(long throughput) {
        return String.format("%s/s",Util.format(throughput,true));
    }

    // *** Fragment ****
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            this.owner = new WeakReference<Owner>((Owner) activity);
        } catch(Throwable x) {
            Log.w(TAG, "Error assigning 'owner' activity",x);
        }
    }
    
    // UTILITY METHODS
    
    /** Hides keyboard for an EditText field
     * 
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
     * @param measurement 
     *            New measurement to add to global results. Ignored if
     *            <code>null</code>.        
     */
    protected void measured(Benchmark... measurements) {
        Owner owner;

        if (measurements != null) {
           if ((owner = this.owner.get()) != null) {
               owner.measured(measurements);
           }
        }
    }
    
    // INNER CLASSES
    
    public interface Owner {
        public void measured(Benchmark... measurments);
    }
    
    protected static class Result { 
        protected final long bytes;
        protected final long dt;
              
        protected Result(long bytes,long dt) {
            this.bytes = bytes;
            this.dt    = dt;
        }
    }

    protected static class Measured { 
        public long mean;
        public long throughput;
        
        protected long minimum = Long.MAX_VALUE;
        protected long maximum = Long.MIN_VALUE;
        protected long bytes   = 0;
        protected long dt      = 0;
        
        protected void update(long bytes,long dt) {
            if (dt > 0) {
                this.bytes     += bytes;
                this.dt        += dt;
                this.throughput = 1000 * bytes/dt;
                this.mean       = 1000 * this.bytes/this.dt;
                this.minimum    = Math.min(minimum,throughput);
                this.maximum    = Math.max(maximum,throughput);
            }
        }
    }


}
