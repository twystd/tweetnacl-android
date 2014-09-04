package za.co.twyst.tweetnacl.benchmark.ui.main;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import za.co.twyst.tweetnacl.benchmark.R;

public class MainMenuFragment extends Fragment {
    // CONSTANTS
    
    private static final String TAG = MainMenuFragment.class.getSimpleName();
    
    // INSTANCE VARIABLES
    
    private WeakReference<Owner> owner = new WeakReference<Owner>(null);

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
        final View        root    = inflater.inflate(R.layout.fragment_menu,container,false);
        final ListView    list    = (ListView) root.findViewById(R.id.list);
        final MenuAdapter adapter = new MenuAdapter(this);
        
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                Log.i("TRACE","onItemClick");
                
                MENUITEM item    = (MENUITEM) adapter.getItem(position);
                Owner    handler = owner.get();
                
                if (handler != null) {
                    handler.clicked(item);
                }
            }
        });
        
        return root;
    }
    
    
    // INNER CLASSES
    
    public interface Owner {
        public void clicked(MENUITEM item);
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
