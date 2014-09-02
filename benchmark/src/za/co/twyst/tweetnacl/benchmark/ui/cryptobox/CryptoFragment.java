package za.co.twyst.tweetnacl.benchmark.ui.cryptobox;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import za.co.twyst.tweetnacl.benchmark.entity.Measurement;

/** Abstract base class for crypto_xxx fragments. Defines the Owner interface
 *  used to update the summary result page.
 *  
 */
public abstract class CryptoFragment extends Fragment {
    // CONSTANTS

    private static final String TAG = CryptoFragment.class.getSimpleName();

    // INSTANCE VARIABLES
    
    private WeakReference<Owner> owner = new WeakReference<Owner>(null);
    
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
    
    /** Updates the containing activity with the measurement.
     * 
     * @param measurement 
     *            New measurement to add to global results. Ignored if
     *            <code>null</code>.        
     */
    protected void measured(Measurement... measurements) {
        Owner owner;

        if (measurements != null) {
           if ((owner = this.owner.get()) != null) {
               owner.measured(measurements);
           }
        }
    }
    
    // INNER CLASSES
    
    public interface Owner {
        public void measured(Measurement... measurments);
    }
}
