package za.co.twyst.tweetnacl.test;

import android.util.Log;

public class TestOther extends TweetNaClTest 
       { // CONSTANTS
	
	     private static final String TAG = "TweetNaCl";
         
         // UNIT TESTS

         /** 'Eyeball' test for the <code>randombytes</code> implementation.
           *  <p>
           *  <code>randombytes</code> uses <code>/dev/urandom</code> so this just 
           *  a quick check to make sure it vaguely works i.e. doesn't crash.
           */
         public void testRandomBytes() throws Exception
                { fail("Crashes horribly if run after box8 test");
                  // SOMETHING TO DO WITH LOADING/UNLOADING tweetnacl MAYBE ?
                
        	      byte[] bytes  = new byte[256];
                
                  tweetnacl.randomBytes(bytes);
                    
                  Log.i(TAG,"randombytes :" + tohex(bytes));
                }
         
       }
