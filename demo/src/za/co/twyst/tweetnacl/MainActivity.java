package za.co.twyst.tweetnacl;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity 
       { // CONSTANTS
    
         @SuppressWarnings("unused")
         private static final String TAG = MainActivity.class.getSimpleName();
         
         // INSTANCE VARIABLES
         
         private TweetNaCl tweetNaCl = new TweetNaCl();
    
	     // *** ActionBarActivity ***

	     @Override
	     protected void onCreate(Bundle state) 
	               { super.onCreate(state);
		
	                 setContentView(R.layout.activity_main);

	                 if (state == null) 
	                    { getSupportFragmentManager().beginTransaction()
	                                                 .add(R.id.container, new PlaceholderFragment())
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

	     // HANDLERS
         
         public void onCryptoBox(View view) 
                { tweetNaCl.cryptoBox();
                }
	     
	     public void onCryptoBoxKeyPair(View view) 
	            { tweetNaCl.crytoBoxKeyPair();
	            }
	     
	     // INNER CLASSES

	     public static class PlaceholderFragment extends Fragment 
	            { public PlaceholderFragment() 
	                     {
	                     }

	              @Override
	              public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle state) 
	                     { return inflater.inflate(R.layout.fragment_main,container,false);
	                     }
	            }
       }
