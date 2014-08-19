package za.co.twyst.tweetnacl.ui.main;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.R;

public class MainMenuFragment extends Fragment {
    // CONSTANTS
    
    private static final String STATE_SELECTED_POSITION = "main_menu_drawer_position";

    // INSTANCE VARIABLES
    
    private int     selected = 0;
    private boolean saved    = false;

    // *** Fragment ***
    
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        if (state != null) {
            selected = state.getInt(STATE_SELECTED_POSITION);
            saved    = true;
        }

        selectItem(selected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View root = inflater.inflate(R.layout.fragment_main,container,false);
        
        return root;
    }
    
    // INSTANCE METHODS
    
    public void setUp(int fid,final DrawerLayout layout) {
        View      container = getActivity().findViewById(fid);
        ActionBar bar       = getActionBar();

        layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled     (true);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),
                                                                       layout,
                                                                       R.drawable.ic_drawer,
                                                                       R.string.navigation_drawer_open,
                                                                       R.string.navigation_drawer_close) {
                                                     @Override
                                                     public void onDrawerClosed(View drawer) {
                                                         super.onDrawerClosed(drawer);
                                                         if (!isAdded()) {
                                                             return;
                                                         }

                                                         getActivity().supportInvalidateOptionsMenu(); 
                                                     }

                                                     @Override
                                                     public void onDrawerOpened(View drawer) {
                                                         super.onDrawerOpened(drawer);
                                                         if (!isAdded()) {
                                                             return;
                                                         }

                                                         getActivity().supportInvalidateOptionsMenu(); 
                                                     }
                                                 };

        // ... open drawer
                                                 
        if (!saved) {
            layout.openDrawer(container);
        }

        // ... defer code dependent on restoration of previous instance state
        
        layout.post(new Runnable() {
                        @Override
                        public void run() {
                            toggle.syncState();
                        }});

        layout.setDrawerListener(toggle);
    }


    // INTERNAL
    
    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
    
    private void selectItem(int position) {
        selected = position;
//        if (mDrawerListView != null) {
//            mDrawerListView.setItemChecked(position, true);
//        }
//        if (mDrawerLayout != null) {
//            mDrawerLayout.closeDrawer(mFragmentContainerView);
//        }
//        if (mCallbacks != null) {
//            mCallbacks.onNavigationDrawerItemSelected(position);
//        }
    }
    
}
