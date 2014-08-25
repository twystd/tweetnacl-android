package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.benchmark.R;

public class MainMenuFragment extends Fragment {
    // CONSTANTS
    
    @SuppressWarnings("unused")
    private static final String TAG = MainMenuFragment.class.getSimpleName();
    
    // CLASS METHODS
    
    /** Factory constructor for MainMenuFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised MainMenuFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        return new MainMenuFragment();
    }

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View root = inflater.inflate(R.layout.fragment_menu,container,false);
        
        return root;
    }
    
//    private void selectItem(int position) {
//        mCurrentSelectedPosition = position;
//        if (mDrawerListView != null) {
//            mDrawerListView.setItemChecked(position, true);
//        }
//        if (mDrawerLayout != null) {
//            mDrawerLayout.closeDrawer(mFragmentContainerView);
//        }
//        if (mCallbacks != null) {
//            mCallbacks.onNavigationDrawerItemSelected(position);
//        }
//    }

}
