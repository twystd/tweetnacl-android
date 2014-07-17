package za.co.twyst.tweetnacl.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import android.content.Context;

import za.co.twyst.tweetnacl.TweetNaCl;
import za.co.twyst.tweetnacl.exceptions.VerifyException;

public class TestCryptoSign extends TweetNaClTest 
       { // CONSTANTS
	
         // TEST VARIABLES
    
         // SETUP/TEARDOWN

         // UNIT TESTS

         /** crypto_sign_keypair
          * 
          */
         public void testCryptoSignKeyPair() throws Exception
                { TweetNaCl.KeyPair keypair = tweetnacl.cryptoSignKeyPair();

                  assertNotNull("Invalid signing key pair",keypair);
                }

         /** crypto_sign (adapted from http://ed25519.cr.yp.to/python/sign.py).
          * 
          */
         public void testCryptoSign() throws Exception
                { final byte[] key = { (byte) 0x1a,(byte) 0xcd,(byte) 0xbb,(byte) 0x79,(byte) 0x3b,(byte) 0x03,(byte) 0x84,(byte) 0x93,
                                       (byte) 0x46,(byte) 0x27,(byte) 0x47,(byte) 0x0d,(byte) 0x79,(byte) 0x5c,(byte) 0x3d,(byte) 0x1d,
                                       (byte) 0xd4,(byte) 0xd7,(byte) 0x9c,(byte) 0xea,(byte) 0x59,(byte) 0xef,(byte) 0x98,(byte) 0x3f,
                                       (byte) 0x29,(byte) 0x5b,(byte) 0x9b,(byte) 0x59,(byte) 0x17,(byte) 0x9c,(byte) 0xbb,(byte) 0x28,
                                       (byte) 0x3f,(byte) 0x60,(byte) 0xc7,(byte) 0x54,(byte) 0x1a,(byte) 0xfa,(byte) 0x76,(byte) 0xc0,
                                       (byte) 0x19,(byte) 0xcf,(byte) 0x5a,(byte) 0xa8,(byte) 0x2d,(byte) 0xcd,(byte) 0xb0,(byte) 0x88,
                                       (byte) 0xed,(byte) 0x9e,(byte) 0x4e,(byte) 0xd9,(byte) 0x78,(byte) 0x05,(byte) 0x14,(byte) 0xae,
                                       (byte) 0xfb,(byte) 0x37,(byte) 0x9d,(byte) 0xab,(byte) 0xc8,(byte) 0x44,(byte) 0xf3,(byte) 0x1a,
                                     };

                  final byte[] message = { (byte) 0x7c,(byte) 0xf3,(byte) 0x4f,(byte) 0x75,(byte) 0xc3,(byte) 0xda,(byte) 0xc9,(byte) 0xa8,
                                           (byte) 0x04,(byte) 0xd0,(byte) 0xfc,(byte) 0xd0,(byte) 0x9e,(byte) 0xba,(byte) 0x9b,(byte) 0x29,
                                           (byte) 0xc9,(byte) 0x48,(byte) 0x4e,(byte) 0x8a,(byte) 0x01,(byte) 0x8f,(byte) 0xa9,(byte) 0xe0,
                                           (byte) 0x73,(byte) 0x04,(byte) 0x2d,(byte) 0xf8,(byte) 0x8e,(byte) 0x3c,(byte) 0x56,
                          
                                         };
                  
                  final byte[] signed = { (byte) 0xbe,(byte) 0x71,(byte) 0xef,(byte) 0x48,(byte) 0x06,(byte) 0xcb,(byte) 0x04,(byte) 0x1d,
                                          (byte) 0x88,(byte) 0x5e,(byte) 0xff,(byte) 0xd9,(byte) 0xe6,(byte) 0xb0,(byte) 0xfb,(byte) 0xb7,
                                          (byte) 0x3d,(byte) 0x65,(byte) 0xd7,(byte) 0xcd,(byte) 0xec,(byte) 0x47,(byte) 0xa8,(byte) 0x9c,
                                          (byte) 0x8a,(byte) 0x99,(byte) 0x48,(byte) 0x92,(byte) 0xf4,(byte) 0xe5,(byte) 0x5a,(byte) 0x56,
                                          (byte) 0x8c,(byte) 0x4c,(byte) 0xc7,(byte) 0x8d,(byte) 0x61,(byte) 0xf9,(byte) 0x01,(byte) 0xe8,
                                          (byte) 0x0d,(byte) 0xbb,(byte) 0x62,(byte) 0x8b,(byte) 0x86,(byte) 0xa2,(byte) 0x3c,(byte) 0xcd,
                                          (byte) 0x59,(byte) 0x4e,(byte) 0x71,(byte) 0x2b,(byte) 0x57,(byte) 0xfa,(byte) 0x94,(byte) 0xc2,
                                          (byte) 0xd6,(byte) 0x7e,(byte) 0xc2,(byte) 0x66,(byte) 0x34,(byte) 0x87,(byte) 0x85,(byte) 0x07,
                                          (byte) 0x7c,(byte) 0xf3,(byte) 0x4f,(byte) 0x75,(byte) 0xc3,(byte) 0xda,(byte) 0xc9,(byte) 0xa8,
                                          (byte) 0x04,(byte) 0xd0,(byte) 0xfc,(byte) 0xd0,(byte) 0x9e,(byte) 0xba,(byte) 0x9b,(byte) 0x29,
                                          (byte) 0xc9,(byte) 0x48,(byte) 0x4e,(byte) 0x8a,(byte) 0x01,(byte) 0x8f,(byte) 0xa9,(byte) 0xe0,
                                          (byte) 0x73,(byte) 0x04,(byte) 0x2d,(byte) 0xf8,(byte) 0x8e,(byte) 0x3c,(byte) 0x56,
                                        };
                  
                  assertTrue("Invalid signature",Arrays.equals(signed,tweetnacl.cryptoSign(message,key)));
                }

         /** crypto_sign_open (adapted from http://ed25519.cr.yp.to/python/sign.py).
          * 
          */
         public void testCryptoSignOpen() throws Exception
                { final byte[] key = { (byte) 0x3f,(byte) 0x60,(byte) 0xc7,(byte) 0x54,(byte) 0x1a,(byte) 0xfa,(byte) 0x76,(byte) 0xc0,
                                       (byte) 0x19,(byte) 0xcf,(byte) 0x5a,(byte) 0xa8,(byte) 0x2d,(byte) 0xcd,(byte) 0xb0,(byte) 0x88,
                                       (byte) 0xed,(byte) 0x9e,(byte) 0x4e,(byte) 0xd9,(byte) 0x78,(byte) 0x05,(byte) 0x14,(byte) 0xae,
                                       (byte) 0xfb,(byte) 0x37,(byte) 0x9d,(byte) 0xab,(byte) 0xc8,(byte) 0x44,(byte) 0xf3,(byte) 0x1a,
                                     };

                  final byte[] message = { (byte) 0x7c,(byte) 0xf3,(byte) 0x4f,(byte) 0x75,(byte) 0xc3,(byte) 0xda,(byte) 0xc9,(byte) 0xa8,
                                           (byte) 0x04,(byte) 0xd0,(byte) 0xfc,(byte) 0xd0,(byte) 0x9e,(byte) 0xba,(byte) 0x9b,(byte) 0x29,
                                           (byte) 0xc9,(byte) 0x48,(byte) 0x4e,(byte) 0x8a,(byte) 0x01,(byte) 0x8f,(byte) 0xa9,(byte) 0xe0,
                                           (byte) 0x73,(byte) 0x04,(byte) 0x2d,(byte) 0xf8,(byte) 0x8e,(byte) 0x3c,(byte) 0x56
                                         };
                  
                  final byte[] signed = { (byte) 0xbe,(byte) 0x71,(byte) 0xef,(byte) 0x48,(byte) 0x06,(byte) 0xcb,(byte) 0x04,(byte) 0x1d,
                                          (byte) 0x88,(byte) 0x5e,(byte) 0xff,(byte) 0xd9,(byte) 0xe6,(byte) 0xb0,(byte) 0xfb,(byte) 0xb7,
                                          (byte) 0x3d,(byte) 0x65,(byte) 0xd7,(byte) 0xcd,(byte) 0xec,(byte) 0x47,(byte) 0xa8,(byte) 0x9c,
                                          (byte) 0x8a,(byte) 0x99,(byte) 0x48,(byte) 0x92,(byte) 0xf4,(byte) 0xe5,(byte) 0x5a,(byte) 0x56,
                                          (byte) 0x8c,(byte) 0x4c,(byte) 0xc7,(byte) 0x8d,(byte) 0x61,(byte) 0xf9,(byte) 0x01,(byte) 0xe8,
                                          (byte) 0x0d,(byte) 0xbb,(byte) 0x62,(byte) 0x8b,(byte) 0x86,(byte) 0xa2,(byte) 0x3c,(byte) 0xcd,
                                          (byte) 0x59,(byte) 0x4e,(byte) 0x71,(byte) 0x2b,(byte) 0x57,(byte) 0xfa,(byte) 0x94,(byte) 0xc2,
                                          (byte) 0xd6,(byte) 0x7e,(byte) 0xc2,(byte) 0x66,(byte) 0x34,(byte) 0x87,(byte) 0x85,(byte) 0x07,
                                          (byte) 0x7c,(byte) 0xf3,(byte) 0x4f,(byte) 0x75,(byte) 0xc3,(byte) 0xda,(byte) 0xc9,(byte) 0xa8,
                                          (byte) 0x04,(byte) 0xd0,(byte) 0xfc,(byte) 0xd0,(byte) 0x9e,(byte) 0xba,(byte) 0x9b,(byte) 0x29,
                                          (byte) 0xc9,(byte) 0x48,(byte) 0x4e,(byte) 0x8a,(byte) 0x01,(byte) 0x8f,(byte) 0xa9,(byte) 0xe0,
                                          (byte) 0x73,(byte) 0x04,(byte) 0x2d,(byte) 0xf8,(byte) 0x8e,(byte) 0x3c,(byte) 0x56,
                                        };

                  byte[] out = tweetnacl.cryptoSignOpen(signed,key);
                  
                  android.util.Log.i(TAG,"MESSAGE: " + tohex(out));
                  
                  assertTrue("Invalid message",Arrays.equals(message,tweetnacl.cryptoSignOpen(signed,key)));
                }
         
         /** crypto_sign/crypto_sign_open (adapted from http://ed25519.cr.yp.to/python/sign.py).
          * 
          */
         public void testED25519() throws Exception 
                { Context        context = getContext();
                  BufferedReader reader  = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(za.co.twyst.tweetnacl.R.raw.sign)));
                  String         line;
               
                  try
                     { while ((line = reader.readLine()) != null)
                             { String[] tokens = line.split(":");
                        
                               assertEquals("Invalid line",4,tokens.length);
                               
                               byte[] sk = fromhex(tokens[0]);
                               byte[] pk = fromhex(tokens[1]);
                               byte[] m  = fromhex(tokens[2]);
                               byte[] sm = fromhex(tokens[3]);
                               
                               assertTrue("Invalid signed message",Arrays.equals(sm,tweetnacl.cryptoSign    (m,sk)));
                               assertTrue("Invalid message",       Arrays.equals(m, tweetnacl.cryptoSignOpen(sm,pk)));
                               
                               byte[] forgedm  = forge  (m);
                               byte[] forgedsm = forgesm(sm);

                               assertFalse("Valid forgery",Arrays.equals(sm,tweetnacl.cryptoSign(forgedm,sk)));
                               
                               try
                                  { assertFalse("Valid forgery",Arrays.equals(m, tweetnacl.cryptoSignOpen(forgedsm,pk)));
                                  }
                               catch(VerifyException x)
                                  { continue;
                                  }
                               
                               fail("Valid forgery");
                             }
                     }
                  finally 
                     { reader.close();
                     }
                }
         
         // UTILITY FUNCTIONS
         
         private static byte[] forge(byte[] m)
                 { if (m.length == 0)
                      { return "x".getBytes();
                      }
                 
                   byte[] fm = m.clone();
                   int    ix = fm.length - 1;
                   
                   fm[ix] = (byte) ((fm[ix] & 0x00ff) + 1);
                   
                   return fm;
                 }
         
         private static byte[] forgesm(byte[] sm)
                 { byte[] fm = new byte[sm.length == TweetNaCl.SIGN_BYTES ? 1             : sm.length - TweetNaCl.SIGN_BYTES];
                   int    ix = fm.length - 1;
                 
                   if (sm.length == TweetNaCl.SIGN_BYTES)
                      { System.arraycopy("x".getBytes(),0,fm,0,fm.length);
                      }
                      else
                      { System.arraycopy(sm,TweetNaCl.SIGN_BYTES,fm,0,fm.length);

                        fm[ix] = (byte) ((fm[ix] & 0x00ff) + 1);
                      }

                   byte[] fsm = new byte[TweetNaCl.SIGN_BYTES + fm.length];
                   
                   System.arraycopy(sm,0,fsm,0,                   TweetNaCl.SIGN_BYTES);
                   System.arraycopy(fm,0,fsm,TweetNaCl.SIGN_BYTES,fm.length);
                   
                   return fsm;
                 }
       }

