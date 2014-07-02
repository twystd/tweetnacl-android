package za.co.twyst.tweetnacl.test;

import android.util.Log;

public class TestOther extends TweetNaClTest 
       { // CONSTANTS
	
         // UNIT TESTS

         /** 'Eyeball' test for the <code>randombytes</code> implementation.
           *  <p>
           *  <code>randombytes</code> uses <code>/dev/urandom</code> so this just 
           *  a quick check to make sure it vaguely works i.e. doesn't crash and 
           *  the data looks vaguely random.
           */
         public void testRandomBytes() throws Exception
                { byte[] bytes  = new byte[256];
                
                  tweetnacl.randomBytes(bytes);
                    
                  Log.i(TAG,"randombytes :" + tohex(bytes));
                }
         
       }
