package za.co.twyst.tweetnacl.test;

import java.util.Arrays;
import java.util.Random;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.exceptions.DecryptException;

public class TestCryptoBox extends TweetNaClTest 
       { // CONSTANTS
	
         @SuppressWarnings("unused")
	     private static final String TAG = "TweetNaCl";
         
         private static final byte[] CIPHERTEXT =  { (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	                                         (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	 	 	                                 (byte) 0xf3,(byte) 0xff,(byte) 0xc7,(byte) 0x70,(byte) 0x3f,(byte) 0x94,(byte) 0x00,(byte) 0xe5,
        	  	                                     (byte) 0x2a,(byte) 0x7d,(byte) 0xfb,(byte) 0x4b,(byte) 0x3d,(byte) 0x33,(byte) 0x05,(byte) 0xd9,
        	  	                                     (byte) 0x8e,(byte) 0x99,(byte) 0x3b,(byte) 0x9f,(byte) 0x48,(byte) 0x68,(byte) 0x12,(byte) 0x73,
        	  	                                     (byte) 0xc2,(byte) 0x96,(byte) 0x50,(byte) 0xba,(byte) 0x32,(byte) 0xfc,(byte) 0x76,(byte) 0xce,
        	  	                                     (byte) 0x48,(byte) 0x33,(byte) 0x2e,(byte) 0xa7,(byte) 0x16,(byte) 0x4d,(byte) 0x96,(byte) 0xa4,
        	  	                                     (byte) 0x47,(byte) 0x6f,(byte) 0xb8,(byte) 0xc5,(byte) 0x31,(byte) 0xa1,(byte) 0x18,(byte) 0x6a,
        	  	                                     (byte) 0xc0,(byte) 0xdf,(byte) 0xc1,(byte) 0x7c,(byte) 0x98,(byte) 0xdc,(byte) 0xe8,(byte) 0x7b,
        	  	                                     (byte) 0x4d,(byte) 0xa7,(byte) 0xf0,(byte) 0x11,(byte) 0xec,(byte) 0x48,(byte) 0xc9,(byte) 0x72,
        	  	                                     (byte) 0x71,(byte) 0xd2,(byte) 0xc2,(byte) 0x0f,(byte) 0x9b,(byte) 0x92,(byte) 0x8f,(byte) 0xe2,
        	  	                                     (byte) 0x27,(byte) 0x0d,(byte) 0x6f,(byte) 0xb8,(byte) 0x63,(byte) 0xd5,(byte) 0x17,(byte) 0x38,
        	  	                                     (byte) 0xb4,(byte) 0x8e,(byte) 0xee,(byte) 0xe3,(byte) 0x14,(byte) 0xa7,(byte) 0xcc,(byte) 0x8a,
        	  	                                     (byte) 0xb9,(byte) 0x32,(byte) 0x16,(byte) 0x45,(byte) 0x48,(byte) 0xe5,(byte) 0x26,(byte) 0xae,
        	  	                                     (byte) 0x90,(byte) 0x22,(byte) 0x43,(byte) 0x68,(byte) 0x51,(byte) 0x7a,(byte) 0xcf,(byte) 0xea,
        	  	                                     (byte) 0xbd,(byte) 0x6b,(byte) 0xb3,(byte) 0x73,(byte) 0x2b,(byte) 0xc0,(byte) 0xe9,(byte) 0xda,
        	  	                                     (byte) 0x99,(byte) 0x83,(byte) 0x2b,(byte) 0x61,(byte) 0xca,(byte) 0x01,(byte) 0xb6,(byte) 0xde,
        	  	                                     (byte) 0x56,(byte) 0x24,(byte) 0x4a,(byte) 0x9e,(byte) 0x88,(byte) 0xd5,(byte) 0xf9,(byte) 0xb3,
        	  	                                     (byte) 0x79,(byte) 0x73,(byte) 0xf6,(byte) 0x22,(byte) 0xa4,(byte) 0x3d,(byte) 0x14,(byte) 0xa6,
        	  	                                     (byte) 0x59,(byte) 0x9b,(byte) 0x1f,(byte) 0x65,(byte) 0x4c,(byte) 0xb4,(byte) 0x5a,(byte) 0x74,
        	  	                                     (byte) 0xe3,(byte) 0x55,(byte) 0xa5
                                                   };

         private static final byte[] MESSAGE = { (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	                                     (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	                                     (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	                                     (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00,
        	                                     (byte) 0xbe,(byte) 0x07,(byte) 0x5f,(byte) 0xc5,(byte) 0x3c,(byte) 0x81,(byte) 0xf2,(byte) 0xd5,
        	                                     (byte) 0xcf,(byte) 0x14,(byte) 0x13,(byte) 0x16,(byte) 0xeb,(byte) 0xeb,(byte) 0x0c,(byte) 0x7b,
        	                                     (byte) 0x52,(byte) 0x28,(byte) 0xc5,(byte) 0x2a,(byte) 0x4c,(byte) 0x62,(byte) 0xcb,(byte) 0xd4,
        	                                     (byte) 0x4b,(byte) 0x66,(byte) 0x84,(byte) 0x9b,(byte) 0x64,(byte) 0x24,(byte) 0x4f,(byte) 0xfc,
        	                                     (byte) 0xe5,(byte) 0xec,(byte) 0xba,(byte) 0xaf,(byte) 0x33,(byte) 0xbd,(byte) 0x75,(byte) 0x1a,
        	                                     (byte) 0x1a,(byte) 0xc7,(byte) 0x28,(byte) 0xd4,(byte) 0x5e,(byte) 0x6c,(byte) 0x61,(byte) 0x29,
        	                                     (byte) 0x6c,(byte) 0xdc,(byte) 0x3c,(byte) 0x01,(byte) 0x23,(byte) 0x35,(byte) 0x61,(byte) 0xf4,
        	                                     (byte) 0x1d,(byte) 0xb6,(byte) 0x6c,(byte) 0xce,(byte) 0x31,(byte) 0x4a,(byte) 0xdb,(byte) 0x31,
        	                                     (byte) 0x0e,(byte) 0x3b,(byte) 0xe8,(byte) 0x25,(byte) 0x0c,(byte) 0x46,(byte) 0xf0,(byte) 0x6d,
        	                                     (byte) 0xce,(byte) 0xea,(byte) 0x3a,(byte) 0x7f,(byte) 0xa1,(byte) 0x34,(byte) 0x80,(byte) 0x57,
        	                                     (byte) 0xe2,(byte) 0xf6,(byte) 0x55,(byte) 0x6a,(byte) 0xd6,(byte) 0xb1,(byte) 0x31,(byte) 0x8a,
        	                                     (byte) 0x02,(byte) 0x4a,(byte) 0x83,(byte) 0x8f,(byte) 0x21,(byte) 0xaf,(byte) 0x1f,(byte) 0xde,
        	                                     (byte) 0x04,(byte) 0x89,(byte) 0x77,(byte) 0xeb,(byte) 0x48,(byte) 0xf5,(byte) 0x9f,(byte) 0xfd,
        	                                     (byte) 0x49,(byte) 0x24,(byte) 0xca,(byte) 0x1c,(byte) 0x60,(byte) 0x90,(byte) 0x2e,(byte) 0x52,
        	                                     (byte) 0xf0,(byte) 0xa0,(byte) 0x89,(byte) 0xbc,(byte) 0x76,(byte) 0x89,(byte) 0x70,(byte) 0x40,
        	                                     (byte) 0xe0,(byte) 0x82,(byte) 0xf9,(byte) 0x37,(byte) 0x76,(byte) 0x38,(byte) 0x48,(byte) 0x64,
        	                                     (byte) 0x5e,(byte) 0x07,(byte) 0x05
                                               };
         
         private static final byte[] NONCE = { (byte) 0x69,(byte) 0x69,(byte) 0x6e,(byte) 0xe9,(byte) 0x55,(byte) 0xb6,(byte) 0x2b,(byte) 0x73,
        	                                   (byte) 0xcd,(byte) 0x62,(byte) 0xbd,(byte) 0xa8,(byte) 0x75,(byte) 0xfc,(byte) 0x73,(byte) 0xd6,
        	                                   (byte) 0x82,(byte) 0x19,(byte) 0xe0,(byte) 0x03,(byte) 0x6b,(byte) 0x7a,(byte) 0x0b,(byte) 0x37
         	                             	};

         private static final byte[] ALICEPK = { (byte) 0x85,(byte) 0x20,(byte) 0xf0,(byte) 0x09,(byte) 0x89,(byte) 0x30,(byte) 0xa7,(byte) 0x54,
        			                             (byte) 0x74,(byte) 0x8b,(byte) 0x7d,(byte) 0xdc,(byte) 0xb4,(byte) 0x3e,(byte) 0xf7,(byte) 0x5a,
        			                             (byte) 0x0d,(byte) 0xbf,(byte) 0x3a,(byte) 0x0d,(byte) 0x26,(byte) 0x38,(byte) 0x1a,(byte) 0xf4,
        			                             (byte) 0xeb,(byte) 0xa4,(byte) 0xa9,(byte) 0x8e,(byte) 0xaa,(byte) 0x9b,(byte) 0x4e,(byte) 0x6a,
         	                                   };
                
         private static final byte[] ALICESK = { (byte) 0x77,(byte) 0x07,(byte) 0x6d,(byte) 0x0a,(byte) 0x73,(byte) 0x18,(byte) 0xa5,(byte) 0x7d,
        	                                     (byte) 0x3c,(byte) 0x16,(byte) 0xc1,(byte) 0x72,(byte) 0x51,(byte) 0xb2,(byte) 0x66,(byte) 0x45,
        	                                     (byte) 0xdf,(byte) 0x4c,(byte) 0x2f,(byte) 0x87,(byte) 0xeb,(byte) 0xc0,(byte) 0x99,(byte) 0x2a,
        	                                     (byte) 0xb1,(byte) 0x77,(byte) 0xfb,(byte) 0xa5,(byte) 0x1d,(byte) 0xb9,(byte) 0x2c,(byte) 0x2a
                                               };
         
         private static final byte[] BOBPK = { (byte) 0xde,(byte) 0x9e,(byte) 0xdb,(byte) 0x7d,(byte) 0x7b,(byte) 0x7d,(byte) 0xc1,(byte) 0xb4,
        			                           (byte) 0xd3,(byte) 0x5b,(byte) 0x61,(byte) 0xc2,(byte) 0xec,(byte) 0xe4,(byte) 0x35,(byte) 0x37,
        			                           (byte) 0x3f,(byte) 0x83,(byte) 0x43,(byte) 0xc8,(byte) 0x5b,(byte) 0x78,(byte) 0x67,(byte) 0x4d,
        			                           (byte) 0xad,(byte) 0xfc,(byte) 0x7e,(byte) 0x14,(byte) 0x6f,(byte) 0x88,(byte) 0x2b,(byte) 0x4f,
                                             };
         
         private static final byte[] BOBSK = { (byte) 0x5d,(byte) 0xab,(byte) 0x08,(byte) 0x7e,(byte) 0x62,(byte) 0x4a,(byte) 0x8a,(byte) 0x4b,
        								       (byte) 0x79,(byte) 0xe1,(byte) 0x7f,(byte) 0x8b,(byte) 0x83,(byte) 0x80,(byte) 0x0e,(byte) 0xe6,
        								       (byte) 0x6f,(byte) 0x3b,(byte) 0xb1,(byte) 0x29,(byte) 0x26,(byte) 0x18,(byte) 0xb6,(byte) 0xfd,
        								       (byte) 0x1c,(byte) 0x2f,(byte) 0x8b,(byte) 0x27,(byte) 0xff,(byte) 0x88,(byte) 0xe0,(byte) 0xeb,
        			                         };

         private static final int    ROUNDS = 10; // TODO change to 10000 a la box7.c and box8.c
         private static final byte[] KEY    = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64, 
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
         public void testCryptoBox() throws Exception
                { byte[] ciphertext = tweetnacl.cryptoBox(MESSAGE,NONCE,BOBPK,ALICESK);
                
                  for (int i=16; i<CIPHERTEXT.length; i++)
                      { assertEquals("Invalid byte " + i,(int) (CIPHERTEXT[i] & 0x00ff),(int) (ciphertext[i] & 0x00ff));
                      }
                }

         /** crypto_box_open (adapted from tests/box2.c)
          * 
          */
         public void testCryptoBoxOpen() throws Exception
                { byte[] message = tweetnacl.cryptoBoxOpen(CIPHERTEXT,NONCE,ALICEPK,BOBSK);
                
                  for (int i=32; i<MESSAGE.length; i++)
                      { assertEquals("Invalid byte " + i,(int) (MESSAGE[i] & 0x00ff),(int) (message[i] & 0x00ff));
                      }
                }

         /** crypto_box_beforenm
          * 
          */
         public void testCryptoBoxBeforeNM() throws Exception
                { byte[] key = tweetnacl.cryptoBoxBeforeNM(BOBPK,ALICESK);

                  assertTrue("Invalid message key",Arrays.equals(KEY,key));
                }

         /** crypto_box_afternm
          * 
          */
         public void testCryptoBoxAfterNM() throws Exception
                { byte[] key        = tweetnacl.cryptoBoxBeforeNM(BOBPK,ALICESK);
                  byte[] ciphertext = tweetnacl.cryptoBoxAfterNM (MESSAGE,NONCE,key);
                
                  for (int i=16; i<CIPHERTEXT.length; i++)
                      { assertEquals("Invalid byte " + i,(int) (CIPHERTEXT[i] & 0x00ff),(int) (ciphertext[i] & 0x00ff));
                      }
                }

         /** crypto_box_open_afternm
          * 
          */
         public void testCryptoBoxOpenAfterNM() throws Exception
                { byte[] key     = tweetnacl.cryptoBoxBeforeNM   (ALICEPK,BOBSK);
                  byte[] message = tweetnacl.cryptoBoxOpenAfterNM(CIPHERTEXT,NONCE,key);
                
                  for (int i=32; i<MESSAGE.length; i++)
                      { assertEquals("Invalid byte " + i,(int) (MESSAGE[i] & 0x00ff),(int) (message[i] & 0x00ff));
                      }
                }
         
         /** Loop test (adapted from tests/box7.c).
          * 
          */
         public void testBox7() throws Exception
                { Random random = new Random();

                  for (int mlen=0; mlen<ROUNDS; ++mlen) 
                      { TweetNaCl.KeyPair alice   = tweetnacl.cryptoBoxKeyPair();
                        TweetNaCl.KeyPair bob     = tweetnacl.cryptoBoxKeyPair();
                        byte[]            message = new byte[mlen + TweetNaCl.ZEROBYTES];
                        byte[]            nonce   = new byte[TweetNaCl.NONCEBYTES];
                        byte[]            ciphertext;
                        byte[]            plaintext;
                      
                        random.nextBytes(nonce);
                        random.nextBytes(message);
                        Arrays.fill     (message,0,TweetNaCl.ZEROBYTES,(byte) 0);
                        
                        ciphertext = tweetnacl.cryptoBox    (message,nonce,bob.publicKey,alice.secretKey);
                        plaintext  = tweetnacl.cryptoBoxOpen(ciphertext,nonce,alice.publicKey,bob.secretKey); 
                    
                        assertTrue("Bad decryption",Arrays.equals(message,plaintext));
                      }
                }
         
         /** Corrupted ciphertext test (adapted from tests/box8.c)
           * 
           */
         public void testBox8() throws Exception
                { Random random = new Random();

                  for (int mlen=0; mlen<ROUNDS; ++mlen) 
                      { TweetNaCl.KeyPair alice   = tweetnacl.cryptoBoxKeyPair();
                        TweetNaCl.KeyPair bob     = tweetnacl.cryptoBoxKeyPair();
                        byte[]            message = new byte[mlen + TweetNaCl.ZEROBYTES];
                        byte[]            nonce   = new byte[TweetNaCl.NONCEBYTES];
                        byte[]            ciphertext;
                        byte[]            plaintext;
                        int               caught = 0;
                   
                        random.nextBytes(nonce);
                        random.nextBytes(message);
                        Arrays.fill     (message,0,TweetNaCl.ZEROBYTES,(byte) 0);
       
                        ciphertext = tweetnacl.cryptoBox(message,nonce,bob.publicKey,alice.secretKey);

                        while (caught < 10)
                              { int ix = random.nextInt(ciphertext.length);
                                byte b = (byte) (random.nextInt() % 0x00ff);
                        	    
                                try
                                   { ciphertext[ix] = b;
                                     plaintext      = tweetnacl.cryptoBoxOpen(ciphertext,nonce,alice.publicKey,bob.secretKey); 

                                     if (!Arrays.equals(message,plaintext))
                                        { fail("Forgery !!!");
                                        }
                                   }
                                catch(DecryptException x)
                                   { ++caught;
                                   }
                              }
                      }
                }
       }

