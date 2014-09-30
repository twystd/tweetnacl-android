package za.co.twyst.tweetnacl.benchmark.ui.summary;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

public class SummaryFragment extends Fragment {
    // CONSTANTS
    
    private static final String TAG = SummaryFragment.class.getSimpleName();
    
    private static final int[] COLUMNS = { R.string.tweetnacl, 
                                         };

    private static final TYPE[] ROWS = { TYPE.CRYPTO_BOX,
                                         TYPE.CRYPTO_BOX_OPEN,
                                         TYPE.CRYPTO_CORE_HSALSA20,
                                         TYPE.CRYPTO_CORE_SALSA20,
                                         TYPE.CRYPTO_HASH,
                                         TYPE.CRYPTO_HASHBLOCKS,
                                         TYPE.CRYPTO_ONETIMEAUTH,
                                         TYPE.CRYPTO_ONETIMEAUTH_VERIFY,
                                         TYPE.CRYPTO_SCALARMULT_BASE,
                                         TYPE.CRYPTO_SCALARMULT,
                                         TYPE.CRYPTO_SECRETBOX,
                                         TYPE.CRYPTO_SECRETBOX_OPEN,
                                         TYPE.CRYPTO_STREAM_XOR,
                                         TYPE.CRYPTO_STREAM_SALSA20_XOR,
                                         TYPE.CRYPTO_SIGN,
                                         TYPE.CRYPTO_SIGN_OPEN,
                                         TYPE.CRYPTO_VERIFY16,
                                         TYPE.CRYPTO_VERIFY32
                                       };

    // INSTANCE VARIABLES
    
    private WeakReference<Owner> owner = new WeakReference<Owner>(null);

    // CLASS METHODS
    
    /** Factory constructor for SummaryFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised SummaryFragment or <code>null</code>.
     */
    public static Fragment newFragment() {
        Fragment fragment = new SummaryFragment();
        Bundle   bundle   = new Bundle();
            
        fragment.setArguments(bundle);
        
        return fragment;
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
        View root = inflater.inflate(R.layout.fragment_summary,container,false);
        Grid grid = (Grid) root.findViewById(R.id.grid);
        
        // ... initialise grid
        
        int[] labels = new int[TYPE.values().length - 1];
        int   ix     = 0;
            
        for (TYPE type: TYPE.values()) {
             if (type != TYPE.UNKNOWN) {
                    labels[ix++] = type.label;
                }
            }
            
        grid.setRowLabels   (labels, inflater,R.layout.label,R.id.textview);
        grid.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        grid.setValues      (TYPE.values().length,COLUMNS.length,inflater,R.layout.value,R.id.textview);
        
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        Owner owner = this.owner.get();

        if (owner != null) {
            Benchmark[] measurements = owner.benchmarks();
            View        root         = getView();
            Grid        grid         = (Grid) root.findViewById(R.id.grid);
            
            for(Benchmark measurement: measurements) {
                for (int row=0; row<ROWS.length; row++) {
                    if (measurement.type.row == ROWS[row].row) {
                        grid.setValue(row,0,measurement.value);
                    }
                }
            }
        }
    }
    
    // INNER CLASSES

    public interface Owner {
        public Benchmark[] benchmarks();
    }
}
