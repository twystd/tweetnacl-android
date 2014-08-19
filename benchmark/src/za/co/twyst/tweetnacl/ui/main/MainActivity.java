package za.co.twyst.tweetnacl.ui.main;

import za.co.twyst.tweetnacl.R;
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
		
	                 setContentView(R.layout.activity_main);

	                 if (state == null) 
	                    { getSupportFragmentManager().beginTransaction()
	                                                 .add(R.id.container, new MainMenuFragment())
	                                                 .commit();
	                    }
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
