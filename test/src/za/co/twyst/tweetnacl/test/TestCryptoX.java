package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] message = { (byte) 0x8e, (byte) 0x99, (byte) 0x3b, (byte) 0x9f, 
//                (byte) 0x48, (byte) 0x68, (byte) 0x12, (byte) 0x73,
//                (byte) 0xc2, (byte) 0x96, (byte) 0x50, (byte) 0xba,
//                (byte) 0x32, (byte) 0xfc, (byte) 0x76, (byte) 0xce,
//                (byte) 0x48, (byte) 0x33, (byte) 0x2e, (byte) 0xa7,
//                (byte) 0x16, (byte) 0x4d, (byte) 0x96, (byte) 0xa4,
//                (byte) 0x47, (byte) 0x6f, (byte) 0xb8, (byte) 0xc5,
//                (byte) 0x31, (byte) 0xa1, (byte) 0x18, (byte) 0x6a,
//                (byte) 0xc0, (byte) 0xdf, (byte) 0xc1, (byte) 0x7c,
//                (byte) 0x98, (byte) 0xdc, (byte) 0xe8, (byte) 0x7b,
//                (byte) 0x4d, (byte) 0xa7, (byte) 0xf0, (byte) 0x11,
//                (byte) 0xec, (byte) 0x48, (byte) 0xc9, (byte) 0x72,
//                (byte) 0x71, (byte) 0xd2, (byte) 0xc2, (byte) 0x0f,
//                (byte) 0x9b, (byte) 0x92, (byte) 0x8f, (byte) 0xe2,
//                (byte) 0x27, (byte) 0x0d, (byte) 0x6f, (byte) 0xb8,
//                (byte) 0x63, (byte) 0xd5, (byte) 0x17, (byte) 0x38,
//                (byte) 0xb4, (byte) 0x8e, (byte) 0xee, (byte) 0xe3,
//                (byte) 0x14, (byte) 0xa7, (byte) 0xcc, (byte) 0x8a,
//                (byte) 0xb9, (byte) 0x32, (byte) 0x16, (byte) 0x45,
//                (byte) 0x48, (byte) 0xe5, (byte) 0x26, (byte) 0xae,
//                (byte) 0x90, (byte) 0x22, (byte) 0x43, (byte) 0x68,
//                (byte) 0x51, (byte) 0x7a, (byte) 0xcf, (byte) 0xea,
//                (byte) 0xbd, (byte) 0x6b, (byte) 0xb3, (byte) 0x73,
//                (byte) 0x2b, (byte) 0xc0, (byte) 0xe9, (byte) 0xda,
//                (byte) 0x99, (byte) 0x83, (byte) 0x2b, (byte) 0x61,
//                (byte) 0xca, (byte) 0x01, (byte) 0xb6, (byte) 0xde,
//                (byte) 0x56, (byte) 0x24, (byte) 0x4a, (byte) 0x9e,
//                (byte) 0x88, (byte) 0xd5, (byte) 0xf9, (byte) 0xb3,
//                (byte) 0x79, (byte) 0x73, (byte) 0xf6, (byte) 0x22,
//                (byte) 0xa4, (byte) 0x3d, (byte) 0x14, (byte) 0xa6,
//                (byte) 0x59, (byte) 0x9b, (byte) 0x1f, (byte) 0x65,
//                (byte) 0x4c, (byte) 0xb4, (byte) 0x5a, (byte) 0x74,
//                (byte) 0xe3, (byte) 0x55, (byte) 0xa5 };
//
//final byte[] key = { (byte) 0xee, (byte) 0xa6, (byte) 0xa7, (byte) 0x25, 
//            (byte) 0x1c, (byte) 0x1e, (byte) 0x72, (byte) 0x91,
//            (byte) 0x6d, (byte) 0x11, (byte) 0xc2, (byte) 0xcb,
//            (byte) 0x21, (byte) 0x4d, (byte) 0x3c, (byte) 0x25,
//            (byte) 0x25, (byte) 0x39, (byte) 0x12, (byte) 0x1d,
//            (byte) 0x8e, (byte) 0x23, (byte) 0x4e, (byte) 0x65,
//            (byte) 0x2d, (byte) 0x65, (byte) 0x1f, (byte) 0xa4,
//            (byte) 0xc8, (byte) 0xcf, (byte) 0xf8, (byte) 0x80 };
//
//final byte[] ref = { (byte) 0xf3, (byte) 0xff, (byte) 0xc7, (byte) 0x70, 
//            (byte) 0x3f, (byte) 0x94, (byte) 0x00, (byte) 0xe5,
//            (byte) 0x2a, (byte) 0x7d, (byte) 0xfb, (byte) 0x4b,
//            (byte) 0x3d, (byte) 0x33, (byte) 0x05, (byte) 0xd9 };
//
//assertTrue("Invalid signature",Arrays.equals(ref,tweetnacl.cryptoOneTimeAuth2(message,key)));
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] key     = new byte[TweetNaCl.ONETIMEAUTH_KEYBYTES];
//            byte[] message = new byte[random.nextInt(16384)];
//            
//            random.nextBytes(key);
//            random.nextBytes(message);
//
//            byte[] p = tweetnacl.cryptoOneTimeAuth (message,key);
//            byte[] q = tweetnacl.cryptoOneTimeAuth2(message,key);
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
//            byte[] key     = new byte[TweetNaCl.ONETIMEAUTH_KEYBYTES];
//            byte[] message = new byte[random.nextInt(16384)];
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
//                p = tweetnacl.cryptoOneTimeAuth (message,key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoOneTimeAuth2(message,key);
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
