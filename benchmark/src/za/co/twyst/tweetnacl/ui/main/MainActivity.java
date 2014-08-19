package za.co.twyst.tweetnacl.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import za.co.twyst.tweetnacl.R;

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
		
	                 setContentView(R.layout.activity_mainx);
	                 
	                 MainMenuFragment menu    = (MainMenuFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
	                 Fragment         summary = new SummaryFragment();

	                 menu.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
	                 
	                 getSupportFragmentManager().beginTransaction()
	                                            .replace(R.id.container,summary)
	                                            .commit();

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
