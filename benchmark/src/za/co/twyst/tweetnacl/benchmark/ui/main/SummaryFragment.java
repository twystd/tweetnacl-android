package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class SummaryFragment extends Fragment {
    // CONSTANTS
    
    private static final int[] ROWS    = { R.string.crypto_box,
                                           R.string.crypto_core,
                                           R.string.crypto_hash,
                                           R.string.crypto_onetimeauth,
                                           R.string.crypto_scalarmult,
                                           R.string.crypto_secretbox,
                                           R.string.crypto_stream,
                                           R.string.crypto_sign,
                                           R.string.crypto_verify
                                         };
    
    private static final int[] COLUMNS = { R.string.tweetnacl, R.string.tweetnaclz };


    // *** Fragment ***
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View root = inflater.inflate(R.layout.fragment_summary,container,false);
        Grid grid = (Grid) root.findViewById(R.id.grid);
        
        // ... initialise grid
        
        grid.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
        grid.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        grid.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);

        
        return root;
    }

}
