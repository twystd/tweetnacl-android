package za.co.twyst.tweetnacl.ui.main;

import za.co.twyst.tweetnacl.R;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity 
       { // CONSTANTS
    
         @SuppressWarnings("unused")
         private static final String TAG = MainActivity.class.getSimpleName();
         
         // INSTANCE VARIABLES
         
//       private TweetNaCl tweetNaCl = new TweetNaCl();
    
	     // *** ActionBarActivity ***

	     @Override
	     protected void onCreate(Bundle state) 
	               { super.onCreate(state);
		
	                 setContentView(R.layout.activity_main);

	                 if (state == null) 
	                    { getSupportFragmentManager().beginTransaction()
	                                                 .add(R.id.container, new MainMenuFragment())
	                                                 .commit();
	                    }
	               }

	     @Override
	     public boolean onCreateOptionsMenu(Menu menu) 
	            { getMenuInflater().inflate(R.menu.main, menu);
	             
	              return true;
	            }

	     @Override
	     public boolean onOptionsItemSelected(MenuItem item) 
	            { int id = item.getItemId();
		
	              switch(id)
	                    { case R.id.action_settings: 
	                           return true;
	                    }
		
	              return super.onOptionsItemSelected(item);
	            }
	     
	     // EVENT HANDLERS
	     
	     public void onCryptoBox(View view) {
	         
	     }
       }
