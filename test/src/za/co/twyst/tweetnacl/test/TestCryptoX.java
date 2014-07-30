package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] stream = { (byte) 0xee, (byte) 0xa6, (byte) 0xa7, (byte) 0x25, 
//                (byte) 0x1c, (byte) 0x1e, (byte) 0x72, (byte) 0x91, 
//                (byte) 0x6d, (byte) 0x11, (byte) 0xc2, (byte) 0xcb, 
//                (byte) 0x21, (byte) 0x4d, (byte) 0x3c, (byte) 0x25, 
//                (byte) 0x25, (byte) 0x39, (byte) 0x12, (byte) 0x1d, 
//                (byte) 0x8e, (byte) 0x23, (byte) 0x4e, (byte) 0x65, 
//                (byte) 0x2d, (byte) 0x65, (byte) 0x1f, (byte) 0xa4, 
//                (byte) 0xc8, (byte) 0xcf, (byte) 0xf8, (byte) 0x80 };
//
//final byte[] nonce = { (byte) 0x69, (byte) 0x69, (byte) 0x6e, (byte) 0xe9, 
//               (byte) 0x55, (byte) 0xb6, (byte) 0x2b, (byte) 0x73, 
//               (byte) 0xcd, (byte) 0x62, (byte) 0xbd, (byte) 0xa8, 
//               (byte) 0x75, (byte) 0xfc, (byte) 0x73, (byte) 0xd6, 
//               (byte) 0x82, (byte) 0x19, (byte) 0xe0, (byte) 0x03, 
//               (byte) 0x6b, (byte) 0x7a, (byte) 0x0b, (byte) 0x37 };
//               
//
//final byte[] key = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64, 
//             (byte) 0x73, (byte) 0xe9, (byte) 0x85, (byte) 0xd4, 
//             (byte) 0x62, (byte) 0xcd, (byte) 0x51, (byte) 0x19, 
//             (byte) 0x7a, (byte) 0x9a, (byte) 0x46, (byte) 0xc7, 
//             (byte) 0x60, (byte) 0x09, (byte) 0x54, (byte) 0x9e, 
//             (byte) 0xac, (byte) 0x64, (byte) 0x74, (byte) 0xf2, 
//             (byte) 0x06, (byte) 0xc4, (byte) 0xee, (byte) 0x08, 
//             (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89 };
//
//assertTrue("Invalid crypttext stream", Arrays.equals(stream, tweetnacl.cryptoStream2(32, nonce, key)));
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] key     = new byte[TweetNaCl.STREAM_KEYBYTES];
//            byte[] nonce   = new byte[TweetNaCl.STREAM_NONCEBYTES];
//            int    N       = random.nextInt(16384);
//            
//            random.nextBytes(key);
//            random.nextBytes(nonce);
//            
//            byte[] p = tweetnacl.cryptoStream (N,nonce,key);
//            byte[] q = tweetnacl.cryptoStream2(N,nonce,key);
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
//            byte[] key     = new byte[TweetNaCl.STREAM_KEYBYTES];
//            byte[] nonce   = new byte[TweetNaCl.STREAM_NONCEBYTES];
//            int    N       = random.nextInt(32768);
//
//            random.nextBytes(key);
//            random.nextBytes(nonce);
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
//                p = tweetnacl.cryptoStream(N,nonce,key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoStream2(N,nonce,key);
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
