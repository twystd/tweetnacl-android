package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import za.co.twyst.tweetnacl.benchmark.R;

public class MainActivity extends ActionBarActivity 
       { // CONSTANTS
    
         @SuppressWarnings("unused")
         private static final String TAG = MainActivity.class.getSimpleName();
         
         // INSTANCE VARIABLES
         
//       private TweetNaCl tweetNaCl = new TweetNaCl();
         private DrawerLayout          drawer;
         private ActionBarDrawerToggle toggle;
    
	     // *** ActionBarActivity ***

	     @Override
	     protected void onCreate(Bundle state) 
	               { super.onCreate(state);
		
	                 setContentView(R.layout.activity_mainx);
	                 
                     final Fragment         summary = new SummaryFragment ();
                     final MainMenuFragment menu    = new MainMenuFragment();
                     final ActionBar        bar     = getSupportActionBar();

                     drawer = (DrawerLayout) findViewById(R.id.container);
                     toggle = new ActionBarDrawerToggle(this,
                                                        drawer,
                                                        R.drawable.ic_drawer,
                                                        R.string.navigation_drawer_open,
                                                        R.string.navigation_drawer_close) 
                                  { @Override
                                    public void onDrawerClosed(View view) 
                                           { super.onDrawerClosed(view);
                                              
//                                           getActionBar().setTitle(mTitle);
                                             supportInvalidateOptionsMenu(); 
                                           }

                                    @Override
                                    public void onDrawerOpened(View view)
                                           { super.onDrawerOpened(view);
                                              
//                                           getActionBar().setTitle(mDrawerTitle);
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
	     protected void onPostCreate(Bundle state) 
	               { super.onPostCreate(state);

	                 toggle.syncState();
	               }
	     
	     @Override
	     public void onConfigurationChanged(Configuration config)
	            { super.onConfigurationChanged(config);
	         
	              toggle.onConfigurationChanged(config);
	            }

	     @Override
	     public boolean onOptionsItemSelected(MenuItem item)
	            { if (toggle.onOptionsItemSelected(item)) 
	                 { return true;
	                 }

	              return super.onOptionsItemSelected(item);
	            }
	     
	     // EVENT HANDLERS
	     
	     public void onCryptoBox(View view) {
	     }
         
         public void onCryptoCore(View view) {
         }
         
         public void onCryptoHash(View view) {
         }
         
         public void onCryptoOneTimeAuth(View view) {
         }
         
         public void onCryptoScalarMult(View view) {
         }
         
         public void onCryptoSecretBox(View view) {
         }
         
         public void onCryptoStream(View view) {
         }
         
         public void onCryptoSign(View view) {
         }
         
         public void onCryptoVerify(View view) {
         }
}
