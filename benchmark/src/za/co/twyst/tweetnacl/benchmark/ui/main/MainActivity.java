package za.co.twyst.tweetnacl.benchmark.ui.main;

import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.entity.Benchmark;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoFragment;
import za.co.twyst.tweetnacl.benchmark.ui.summary.SummaryFragment;

public class MainActivity extends ActionBarActivity implements MainMenuFragment.Owner,
                                                               SummaryFragment.Owner,
                                                               CryptoFragment.Owner { 
    // CONSTANTS
    
    private static final String TAG = MainActivity.class.getSimpleName();
         
    // INSTANCE VARIABLES
         
    private DrawerLayout                  drawer;
    private ActionBarDrawerToggle         toggle;
    private Map<Benchmark.TYPE,Benchmark> measurements = new HashMap<Benchmark.TYPE,Benchmark>();
        
    // *** ActionBarActivity ***

    @Override
    protected void onCreate(Bundle state) { 
        super.onCreate(state);
		
        setContentView(R.layout.activity_main);
	                 
        final Fragment  summary = SummaryFragment.newFragment ();
        final Fragment  menu    = MainMenuFragment.newFragment();
        final ActionBar bar     = getSupportActionBar();

        drawer = (DrawerLayout) findViewById(R.id.container);
        toggle = new ActionBarDrawerToggle(this,
                                           drawer,
                                           R.drawable.ic_drawer,
                                           R.string.navigation_drawer_open,
                                           R.string.navigation_drawer_close) { 
            @Override
            public void onDrawerClosed(View view) { 
                super.onDrawerClosed(view);
                                              
                supportInvalidateOptionsMenu(); 
            }

            @Override
            public void onDrawerOpened(View view) { 
                super.onDrawerOpened(view);
                                              
                supportInvalidateOptionsMenu(); 
            }
        };
                                  
        drawer.setDrawerShadow  (R.drawable.drawer_shadow, GravityCompat.START);
        drawer.setDrawerListener(toggle);
        drawer.openDrawer       (GravityCompat.START);
                     
        bar.setNavigationMode         (ActionBar.NAVIGATION_MODE_STANDARD);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayHomeAsUpEnabled (true);
        bar.setHomeButtonEnabled      (true);
	                 
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.content,summary)
                                   .replace(R.id.drawer, menu)
                                   .commit();

    }
	     
    @Override
    protected void onPostCreate(Bundle state) { 
        super.onPostCreate(state);

        toggle.syncState();
    }
	     
    @Override
    public void onConfigurationChanged(Configuration config) { 
        super.onConfigurationChanged(config);
	         
        toggle.onConfigurationChanged(config);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        int N = getSupportFragmentManager().getBackStackEntryCount();
//        
//        if (N == 0) {
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                finish();
//            } else {
//                drawer.openDrawer(GravityCompat.START);
//                return;
//            }
//        }
//        
//        super.onBackPressed();
//    }   

    // EVENT HANDLERS

    // *** MainMenuFragment.Owner ***
    
    @Override
    public void clicked(MENUITEM item) {
        Fragment fragment = null;

        try {
            fragment = item.clazz.newInstance();
        } catch(Throwable x) {
            Log.wtf(TAG,"Error instantiating fragment",x);
        }

        // ... replace fragments
        
        if (fragment != null) {
            int N = getSupportFragmentManager().getBackStackEntryCount();
            
            if ((N == 0) && (item != MENUITEM.SUMMARY)) {
                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.content,fragment)
                                           .addToBackStack(null)
                                           .commit();
            } else  {
                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.content,fragment)
                                           .commit();
            }
             
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    // *** SummaryFragment.Owner ***
    
    @Override
    public Benchmark[] benchmarks() {
        return measurements.values().toArray(new Benchmark[0]);
    }

    // *** CryptoFragment.Owner ***
    
    @Override
    public void measured(Benchmark... measurments) {
        if (measurements != null) {
            for (Benchmark measurement: measurments) {
                measurements.put(measurement.type,measurement);
            }
        }
    }
}
