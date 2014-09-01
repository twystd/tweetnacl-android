package za.co.twyst.tweetnacl.benchmark.ui.cryptobox;

import android.support.v4.app.Fragment;

/** Abstract base class for crypto_xxx fragments. Defines the Owner interface
 *  used to update the summary result page.
 *  
 */
public abstract class CryptoFragment extends Fragment {
    // CONSTANTS

    @SuppressWarnings("unused")
    private static final String TAG = CryptoFragment.class.getSimpleName();
    
    // INSTANCE VARIABLES
    
    // INNER CLASSES
    
    public interface Owner {
        
    }
}
