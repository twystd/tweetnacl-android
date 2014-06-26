package za.co.twyst.tweetnacl.test;

public class TestCryptoCore extends TweetNaClTest 
       { // CONSTANTS
	
         private static final byte[] ZERO = new byte[32];

         private static final byte[] SHARED = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d, (byte) 0x5b, 
        	                                    (byte) 0xa4, (byte) 0xce, (byte) 0x2d, (byte) 0xe1,
        	                                    (byte) 0x72, (byte) 0x8e, (byte) 0x3b, (byte) 0xf4,
        	                                    (byte) 0x80, (byte) 0x35, (byte) 0x0f, (byte) 0x25,
        	                                    (byte) 0xe0, (byte) 0x7e, (byte) 0x21, (byte) 0xc9,
        	                                    (byte) 0x47, (byte) 0xd1, (byte) 0x9e, (byte) 0x33,
        	                                    (byte) 0x76, (byte) 0xf0, (byte) 0x9b, (byte) 0x3c,
        	                                    (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42
                                              };
         
         private static final byte[] C = { (byte) 0x65, (byte) 0x78, (byte) 0x70, (byte) 0x61, 
        	                               (byte) 0x6e, (byte) 0x64, (byte) 0x20, (byte) 0x33,
        	                               (byte) 0x32, (byte) 0x2d, (byte) 0x62, (byte) 0x79,
        	                               (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6b
        	 	                         };
         
         private static final byte[] OUT = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64, 
        	                                 (byte) 0x73, (byte) 0xe9, (byte) 0x85, (byte) 0xd4,
        	                                 (byte) 0x62, (byte) 0xcd, (byte) 0x51, (byte) 0x19,
        	                                 (byte) 0x7a, (byte) 0x9a, (byte) 0x46, (byte) 0xc7,
        	                                 (byte) 0x60, (byte) 0x09, (byte) 0x54, (byte) 0x9e,
        	                                 (byte) 0xac, (byte) 0x64, (byte) 0x74, (byte) 0xf2,
        	                                 (byte) 0x06, (byte) 0xc4, (byte) 0xee, (byte) 0x08,
        	                                 (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89
        	 	                         };

         // TEST VARIABLES
    
         // SETUP/TEARDOWN

         // UNIT TESTS

         /** crypto_box (adapted from tests/box.c)
          * 
          */
         public void testCryptoCoreSalsa20() throws Exception
                { byte[] out = tweetnacl.cryptoCoreHSalsa20(ZERO,SHARED,C);
                
                  assertEquals("Invalid out",OUT,out);
                }
       }
