package za.co.twyst.tweetnacl.ui.main;

import za.co.twyst.tweetnacl.R;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
		
	                 setContentView(R.layout.activity_mainx);
	                 
	                 MainMenuFragment fragment = (MainMenuFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

	                 fragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
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
