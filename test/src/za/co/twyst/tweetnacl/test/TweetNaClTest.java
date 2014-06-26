package za.co.twyst.tweetnacl.test;

import android.test.AndroidTestCase;

import za.co.twyst.tweetnacl.TweetNaCl;

public abstract class TweetNaClTest extends AndroidTestCase 
       { // CONSTANTS
	
         protected static final String TAG = "TweetNaCl";
    
         private static final char[] HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };

         // TEST VARIABLES
    
         protected TweetNaCl tweetnacl;

         // SETUP/TEARDOWN

         @Override
         protected void setUp() throws Exception 
                   { tweetnacl = new TweetNaCl();
                   }

         @Override
         protected void tearDown() throws Exception 
                   { tweetnacl.release();
                   }

         // UTILITY FUNCTIONS
         
         /** Converts a byte array to a hexadecimal string.
           * 
           */
         protected static String tohex(byte[] bytes)
                   { char[] string = new char[bytes.length*2];
                     int    ix     = 0;
                
                     for (byte x: bytes)
                         { string[ix++] = HEX[(x >> 4) & 0x0f];
                           string[ix++] = HEX[(x >> 0) & 0x0f];
                         }
                    
                     return new String(string);
                   }
       }
