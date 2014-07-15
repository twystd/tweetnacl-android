package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoStream extends TweetNaClTest 
       { // CONSTANTS
	
         // TEST VARIABLES
    
         // SETUP/TEARDOWN

         // UNIT TESTS

         /** crypto_stream (adapted from tests/stream3s.c)
          * 
          */
         public void testCryptoStream() throws Exception
                { final byte[] stream = { (byte) 0xee,(byte) 0xa6,(byte) 0xa7,(byte) 0x25,(byte) 0x1c,(byte) 0x1e,(byte) 0x72,(byte) 0x91,
                		                  (byte) 0x6d,(byte) 0x11,(byte) 0xc2,(byte) 0xcb,(byte) 0x21,(byte) 0x4d,(byte) 0x3c,(byte) 0x25,	 
                		                  (byte) 0x25,(byte) 0x39,(byte) 0x12,(byte) 0x1d,(byte) 0x8e,(byte) 0x23,(byte) 0x4e,(byte) 0x65,
                		                  (byte) 0x2d,(byte) 0x65,(byte) 0x1f,(byte) 0xa4,(byte) 0xc8,(byte) 0xcf,(byte) 0xf8,(byte) 0x80,
                                        };
                  
                  final byte[] nonce = { (byte) 0x69,(byte) 0x69,(byte) 0x6e,(byte) 0xe9,(byte) 0x55,(byte) 0xb6,(byte) 0x2b,(byte) 0x73,
                		  	             (byte) 0xcd,(byte) 0x62,(byte) 0xbd,(byte) 0xa8,(byte) 0x75,(byte) 0xfc,(byte) 0x73,(byte) 0xd6,
                		  	             (byte) 0x82,(byte) 0x19,(byte) 0xe0,(byte) 0x03,(byte) 0x6b,(byte) 0x7a,(byte) 0x0b,(byte) 0x37
                                       };
                  
                  final byte[] key = { (byte) 0x1b,(byte) 0x27,(byte) 0x55,(byte) 0x64,(byte) 0x73,(byte) 0xe9,(byte) 0x85,(byte) 0xd4,
                		  	           (byte) 0x62,(byte) 0xcd,(byte) 0x51,(byte) 0x19,(byte) 0x7a,(byte) 0x9a,(byte) 0x46,(byte) 0xc7,
                		  	           (byte) 0x60,(byte) 0x09,(byte) 0x54,(byte) 0x9e,(byte) 0xac,(byte) 0x64,(byte) 0x74,(byte) 0xf2,
                		  	           (byte) 0x06,(byte) 0xc4,(byte) 0xee,(byte) 0x08,(byte) 0x44,(byte) 0xf6,(byte) 0x83,(byte) 0x89
                                     };

                  assertTrue("Invalid crypttext stream",Arrays.equals(stream,tweetnacl.cryptoStream(32,nonce,key)));
                }
       }

