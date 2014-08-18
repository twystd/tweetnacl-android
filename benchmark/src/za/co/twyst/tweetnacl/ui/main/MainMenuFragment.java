package za.co.twyst.tweetnacl.ui.main;

import za.co.twyst.tweetnacl.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainMenuFragment extends Fragment {

    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View root = inflater.inflate(R.layout.fragment_main,container,false);
        
        return root;
    }

    
}
