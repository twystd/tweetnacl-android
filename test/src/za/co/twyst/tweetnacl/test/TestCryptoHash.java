package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoHash extends TweetNaClTest 
       { // CONSTANTS
	
         // TEST VARIABLES
    
         // SETUP/TEARDOWN

         // UNIT TESTS

         /** crypto_hash (adapted from tests/hash.c)
          * 
          */
         public void testHash() throws Exception
                { final byte[] x   = "testing\n".getBytes();
                  final byte[] ref = { (byte) 0x24, (byte) 0xf9, (byte) 0x50, (byte) 0xaa, (byte) 0xc7, (byte) 0xb9, (byte) 0xea, (byte) 0x9b,
                		               (byte) 0x3c, (byte) 0xb7, (byte) 0x28, (byte) 0x22, (byte) 0x8a, (byte) 0x0c, (byte) 0x82, (byte) 0xb6,
                		               (byte) 0x7c, (byte) 0x39, (byte) 0xe9, (byte) 0x6b, (byte) 0x4b, (byte) 0x34, (byte) 0x47, (byte) 0x98,
                		               (byte) 0x87, (byte) 0x0d, (byte) 0x5d, (byte) 0xae, (byte) 0xe9, (byte) 0x3e, (byte) 0x3a, (byte) 0xe5,
                		               (byte) 0x93, (byte) 0x1b, (byte) 0xaa, (byte) 0xe8, (byte) 0xc7, (byte) 0xca, (byte) 0xcf, (byte) 0xea,
                		               (byte) 0x4b, (byte) 0x62, (byte) 0x94, (byte) 0x52, (byte) 0xc3, (byte) 0x80, (byte) 0x26, (byte) 0xa8,
                		               (byte) 0x1d, (byte) 0x13, (byte) 0x8b, (byte) 0xc7, (byte) 0xaa, (byte) 0xd1, (byte) 0xaf, (byte) 0x3e,
                		               (byte) 0xf7, (byte) 0xbf, (byte) 0xd5, (byte) 0xec, (byte) 0x64, (byte) 0x6d, (byte) 0x6c, (byte) 0x28
                                     };

                  assertTrue("Invalid hash",Arrays.equals(ref,tweetnacl.cryptoHash(x)));
                }

         /** crypto_hashblocks
          * 
          */
         public void testCryptoHashBlocks() throws Exception
                { final byte[] ref = { (byte) 0x24, (byte) 0xf9, (byte) 0x50, (byte) 0xaa, (byte) 0xc7, (byte) 0xb9, (byte) 0xea, (byte) 0x9b,
                		               (byte) 0x3c, (byte) 0xb7, (byte) 0x28, (byte) 0x22, (byte) 0x8a, (byte) 0x0c, (byte) 0x82, (byte) 0xb6,
                		               (byte) 0x7c, (byte) 0x39, (byte) 0xe9, (byte) 0x6b, (byte) 0x4b, (byte) 0x34, (byte) 0x47, (byte) 0x98,
                		               (byte) 0x87, (byte) 0x0d, (byte) 0x5d, (byte) 0xae, (byte) 0xe9, (byte) 0x3e, (byte) 0x3a, (byte) 0xe5,
                		               (byte) 0x93, (byte) 0x1b, (byte) 0xaa, (byte) 0xe8, (byte) 0xc7, (byte) 0xca, (byte) 0xcf, (byte) 0xea,
                		               (byte) 0x4b, (byte) 0x62, (byte) 0x94, (byte) 0x52, (byte) 0xc3, (byte) 0x80, (byte) 0x26, (byte) 0xa8,
                		               (byte) 0x1d, (byte) 0x13, (byte) 0x8b, (byte) 0xc7, (byte) 0xaa, (byte) 0xd1, (byte) 0xaf, (byte) 0x3e,
                		               (byte) 0xf7, (byte) 0xbf, (byte) 0xd5, (byte) 0xec, (byte) 0x64, (byte) 0x6d, (byte) 0x6c, (byte) 0x28
                                     };

        	      final byte[] x = "testing\n".getBytes();

        	      assertTrue("Invalid hashblocks",Arrays.equals(ref,tweetnacl.cryptoHashBlocks(x)));
                }
       }

