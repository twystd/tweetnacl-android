package za.co.twyst.tweetnacl.benchmark.ui.cryptobox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.benchmark.R;

public class CryptoBoxFragment extends Fragment {
    // CONSTANTS

    // CLASS METHODS

    /** Factory constructor for CryptoBoxFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised CryptoBoxFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new CryptoBoxFragment();
    }

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View root = inflater.inflate(R.layout.fragment_cryptobox,container,false);
        
        return root;
    }
}
