package za.co.twyst.tweetnacl.test;

import java.util.Arrays;
import java.util.Random;

public class TestCryptoVerify extends TweetNaClTest 
       { // CONSTANTS
	
         // TEST VARIABLES
    
         private Random random = new Random();
         
         // SETUP/TEARDOWN

         // UNIT TESTS

         /** crypto_verify_16 (adapted from nacl/crypto_verify/try.c)
          * 
          */
         public void testCryptoVerify16() throws Exception
                { byte[] x = new byte[16];
                  byte[] y = new byte[16];

                  for (int i=0; i<ROUNDS; i++)
                      { random.nextBytes(x);
                        random.nextBytes(y);
                  
                        assertEquals("Invalid crypto_verify_16 result",Arrays.equals(x,y),tweetnacl.cryptoVerify16(x,y));

                        y = x.clone();

                        assertTrue("Invalid crypto_verify_16 result",tweetnacl.cryptoVerify16(x,y));

                        for (int j=0;j<16; j++)
                            { int ix = random.nextInt(16);
                              byte b = (byte) random.nextInt(256);
                            
                              y[ix] = b;

                              assertEquals("Invalid crypto_verify_16 result",Arrays.equals(x,y),tweetnacl.cryptoVerify16(x,y));
                            }
                      }
                }

         /** crypto_verify_32 (adapted from nacl/crypto_verify/try.c)
          * 
          */
         public void testCryptoVerify32() throws Exception
                { byte[] x = new byte[32];
                  byte[] y = new byte[32];

                  for (int i=0; i<ROUNDS; i++)
                      { random.nextBytes(x);
                        random.nextBytes(y);
         
                        assertEquals("Invalid crypto_verify_32 result",Arrays.equals(x,y),tweetnacl.cryptoVerify32(x,y));

                        y = x.clone();

                        assertTrue("Invalid crypto_verify_32 result",tweetnacl.cryptoVerify32(x,y));

                        for (int j=0;j<16; j++)
                            { int ix = random.nextInt(32);
                              byte b = (byte) random.nextInt(256);
                   
                              y[ix] = b;

                              assertEquals("Invalid crypto_verify_32 result",Arrays.equals(x,y),tweetnacl.cryptoVerify32(x,y));
                            }
                      }
                }
       }

