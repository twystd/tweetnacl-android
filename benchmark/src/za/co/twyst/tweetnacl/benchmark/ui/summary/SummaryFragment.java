package za.co.twyst.tweetnacl.benchmark.ui.summary;

import java.lang.ref.WeakReference;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Grid;

import static za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;

public class SummaryFragment extends Fragment {
    // CONSTANTS
    
    private static final String TAG              = SummaryFragment.class.getSimpleName();
    private static final String TAG_MEASUREMENTS = "measurements";
    
    private static final int[] ROWS    = { R.string.label_crypto_box,
                                           R.string.label_crypto_box_open,
                                           R.string.label_crypto_core_hsalsa20,
                                           R.string.label_crypto_core_salsa20,
                                           R.string.label_crypto_hash,
                                           R.string.label_crypto_hashblocks,
                                           R.string.label_crypto_onetimeauth,
                                           R.string.label_crypto_onetimeauth_verify,
                                           R.string.label_crypto_scalarmultbase,
                                           R.string.label_crypto_scalarmult,
                                           R.string.label_crypto_secretbox,
                                           R.string.label_crypto_secretbox_open,
                                           R.string.label_crypto_stream,
                                           R.string.label_crypto_sign,
                                           R.string.label_crypto_verify
                                         };
    
    private static final int[] COLUMNS = { R.string.tweetnacl, 
                                           // R.string.tweetnaclz 
                                         };

    private static final TYPE[] ROW = { TYPE.CRYPTO_BOX,
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
                                        TYPE.CRYPTO_SECRETBOX_OPEN
                                      };

    // INSTANCE VARIABLES
    
    private WeakReference<Owner> owner = new WeakReference<Owner>(null);

    // CLASS METHODS
    
    /** Factory constructor for SummaryFragment that ensures correct fragment
     *  initialisation.
     *  
     * @return Initialised SummaryFragment or <code>null</code>.
     */
    public static Fragment newFragment(Collection<Benchmark> measurements) {
        if (measurements != null) {
            Fragment fragment = new SummaryFragment();
            Bundle   bundle   = new Bundle();
            
            bundle.putParcelableArray(TAG_MEASUREMENTS,measurements.toArray(new Benchmark[0]));
            fragment.setArguments    (bundle);
            
            return fragment;
        }
        
        return null;
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
        Bundle      bundle       = getArguments();
        Benchmark[] measurements = (Benchmark[]) bundle.getParcelableArray(TAG_MEASUREMENTS);
        
        View root = inflater.inflate(R.layout.fragment_summary,container,false);
        Grid grid = (Grid) root.findViewById(R.id.grid);
        
        // ... initialise grid
        
        grid.setRowLabels   (ROWS,   inflater,R.layout.label,R.id.textview);
        grid.setColumnLabels(COLUMNS,inflater,R.layout.value,R.id.textview);
        grid.setValues      (ROWS.length,COLUMNS.length,inflater,R.layout.value,R.id.textview);
        
        // ... displays measured values
        
        for(Benchmark measurement: measurements) {
            for (int row=0; row<ROW.length; row++) {
                if (measurement.type == ROW[row]) {
                    grid.setValue(row,0,measurement.value);
                }
            }
        }
        
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
                for (int row=0; row<ROW.length; row++) {
                    if (measurement.type == ROW[row]) {
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
