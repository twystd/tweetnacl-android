package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] key = { (byte) 0x1a, (byte) 0xcd, (byte) 0xbb, (byte) 0x79, 
//                (byte) 0x3b, (byte) 0x03, (byte) 0x84, (byte) 0x93, 
//                (byte) 0x46, (byte) 0x27, (byte) 0x47, (byte) 0x0d, 
//                (byte) 0x79, (byte) 0x5c, (byte) 0x3d, (byte) 0x1d, 
//                (byte) 0xd4, (byte) 0xd7, (byte) 0x9c, (byte) 0xea, 
//                (byte) 0x59, (byte) 0xef, (byte) 0x98, (byte) 0x3f, 
//                (byte) 0x29, (byte) 0x5b, (byte) 0x9b, (byte) 0x59, 
//                (byte) 0x17, (byte) 0x9c, (byte) 0xbb, (byte) 0x28, 
//                (byte) 0x3f, (byte) 0x60, (byte) 0xc7, (byte) 0x54, 
//                (byte) 0x1a, (byte) 0xfa, (byte) 0x76, (byte) 0xc0, 
//                (byte) 0x19, (byte) 0xcf, (byte) 0x5a, (byte) 0xa8, 
//                (byte) 0x2d, (byte) 0xcd, (byte) 0xb0, (byte) 0x88, 
//                (byte) 0xed, (byte) 0x9e, (byte) 0x4e, (byte) 0xd9, 
//                (byte) 0x78, (byte) 0x05, (byte) 0x14, (byte) 0xae, 
//                (byte) 0xfb, (byte) 0x37, (byte) 0x9d, (byte) 0xab, 
//                (byte) 0xc8, (byte) 0x44, (byte) 0xf3, (byte) 0x1a, };
//
//final byte[] message = { (byte) 0x7c, (byte) 0xf3, (byte) 0x4f, (byte) 0x75, 
//                    (byte) 0xc3, (byte) 0xda, (byte) 0xc9, (byte) 0xa8, 
//                    (byte) 0x04, (byte) 0xd0, (byte) 0xfc, (byte) 0xd0, 
//                    (byte) 0x9e, (byte) 0xba, (byte) 0x9b, (byte) 0x29, 
//                    (byte) 0xc9, (byte) 0x48, (byte) 0x4e, (byte) 0x8a, 
//                    (byte) 0x01, (byte) 0x8f, (byte) 0xa9, (byte) 0xe0, 
//                    (byte) 0x73, (byte) 0x04, (byte) 0x2d, (byte) 0xf8, 
//                    (byte) 0x8e, (byte) 0x3c, (byte) 0x56, };
//
//final byte[] signed = { (byte) 0xbe, (byte) 0x71, (byte) 0xef, (byte) 0x48, 
//                   (byte) 0x06, (byte) 0xcb, (byte) 0x04, (byte) 0x1d, 
//                   (byte) 0x88, (byte) 0x5e, (byte) 0xff, (byte) 0xd9, 
//                   (byte) 0xe6, (byte) 0xb0, (byte) 0xfb, (byte) 0xb7, 
//                   (byte) 0x3d, (byte) 0x65, (byte) 0xd7, (byte) 0xcd, 
//                   (byte) 0xec, (byte) 0x47, (byte) 0xa8, (byte) 0x9c, 
//                   (byte) 0x8a, (byte) 0x99, (byte) 0x48, (byte) 0x92, 
//                   (byte) 0xf4, (byte) 0xe5, (byte) 0x5a, (byte) 0x56, 
//                   (byte) 0x8c, (byte) 0x4c, (byte) 0xc7, (byte) 0x8d, 
//                   (byte) 0x61, (byte) 0xf9, (byte) 0x01, (byte) 0xe8, 
//                   (byte) 0x0d, (byte) 0xbb, (byte) 0x62, (byte) 0x8b, 
//                   (byte) 0x86, (byte) 0xa2, (byte) 0x3c, (byte) 0xcd, 
//                   (byte) 0x59, (byte) 0x4e, (byte) 0x71, (byte) 0x2b, 
//                   (byte) 0x57, (byte) 0xfa, (byte) 0x94, (byte) 0xc2, 
//                   (byte) 0xd6, (byte) 0x7e, (byte) 0xc2, (byte) 0x66, 
//                   (byte) 0x34, (byte) 0x87, (byte) 0x85, (byte) 0x07, 
//                   (byte) 0x7c, (byte) 0xf3, (byte) 0x4f, (byte) 0x75, 
//                   (byte) 0xc3, (byte) 0xda, (byte) 0xc9, (byte) 0xa8, 
//                   (byte) 0x04, (byte) 0xd0, (byte) 0xfc, (byte) 0xd0, 
//                   (byte) 0x9e, (byte) 0xba, (byte) 0x9b, (byte) 0x29, 
//                   (byte) 0xc9, (byte) 0x48, (byte) 0x4e, (byte) 0x8a, 
//                   (byte) 0x01, (byte) 0x8f, (byte) 0xa9, (byte) 0xe0, 
//                   (byte) 0x73, (byte) 0x04, (byte) 0x2d, (byte) 0xf8, 
//                   (byte) 0x8e, (byte) 0x3c, (byte) 0x56, }; 
//
//assertTrue("Invalid signature", Arrays.equals(signed, tweetnacl.cryptoSignX(message, key)));
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] key     = new byte[TweetNaCl.SIGN_SECRETKEYBYTES];
//            byte[] message = new byte[16384 + random.nextInt(16384)];
//            
//            random.nextBytes(key);
//            random.nextBytes(message);
//            
//            byte[] p = tweetnacl.cryptoSign (message,key);
//            byte[] q = tweetnacl.cryptoSignX(message,key);
//
//            assertTrue("OOOOPS !",Arrays.equals(p,q));
//        }
//    }
//
//    public void testZ() throws Exception {
//        long DT  = 0;
//        long DT2 = 0;
//        
//        for (int i=0; i<64; i++) {
//            byte[] key     = new byte[TweetNaCl.SIGN_SECRETKEYBYTES];
//            byte[] message = new byte[16384 + random.nextInt(16384)];
//
//            random.nextBytes(key);
//            random.nextBytes(message);
//
//            byte[] p = new byte[0];
//            byte[] q = new byte[0];
//            long   start;
//            long   dt;
//            long   dt2;
//            
//            start = System.currentTimeMillis();
//            
//            for (int j=0; j<1024; j++) {
//                p = tweetnacl.cryptoSign(message,key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoSignX(message,key);
//            }
//
//            dt2  = System.currentTimeMillis() - start;
//            DT  += dt;
//            DT2 += dt2;
//
//            assertTrue(Arrays.equals(p,q));            
//
//            android.util.Log.i(TAG,"DT: " + dt + "  " + dt2 + "  " + DT + "  " + DT2 + "  " + ((double) DT - (double) DT2)/(double) DT);
//        }
//
//        android.util.Log.i(TAG,"DT/FINAL: " + DT + "  " + DT2);
//    }
}
