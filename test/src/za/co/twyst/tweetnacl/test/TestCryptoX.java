package za.co.twyst.tweetnacl.test;

import java.security.MessageDigest;
import java.util.Arrays;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] HASH = { (byte) 0x66, (byte) 0x2b, (byte) 0x9d, (byte) 0x0e, 
//                (byte) 0x34, (byte) 0x63, (byte) 0x02, (byte) 0x91, 
//                (byte) 0x56, (byte) 0x06, (byte) 0x9b, (byte) 0x12, 
//                (byte) 0xf9, (byte) 0x18, (byte) 0x69, (byte) 0x1a, 
//                (byte) 0x98, (byte) 0xf7, (byte) 0xdf, (byte) 0xb2, 
//                (byte) 0xca, (byte) 0x03, (byte) 0x93, (byte) 0xc9, 
//                (byte) 0x6b, (byte) 0xbf, (byte) 0xc6, (byte) 0xb1, 
//                (byte) 0xfb, (byte) 0xd6, (byte) 0x30, (byte) 0xa2 };
//
//final byte[] nonce = { (byte) 0x82, (byte) 0x19, (byte) 0xe0, (byte) 0x03, 
//                 (byte) 0x6b, (byte) 0x7a, (byte) 0x0b, (byte) 0x37 };
//
//final byte[] key = { (byte) 0xdc, (byte) 0x90, (byte) 0x8d, (byte) 0xda, 
//               (byte) 0x0b, (byte) 0x93, (byte) 0x44, (byte) 0xa9, 
//               (byte) 0x53, (byte) 0x62, (byte) 0x9b, (byte) 0x73, 
//               (byte) 0x38, (byte) 0x20, (byte) 0x77, (byte) 0x88, 
//               (byte) 0x80, (byte) 0xf3, (byte) 0xce, (byte) 0xb4, 
//               (byte) 0x21, (byte) 0xbb, (byte) 0x61, (byte) 0xb9, 
//               (byte) 0x1c, (byte) 0xbd, (byte) 0x4c, (byte) 0x3e, 
//               (byte) 0x66, (byte) 0x25, (byte) 0x6c, (byte) 0xe4, };
//
//byte[]        stream = tweetnacl.cryptoStreamSalsa20X(4194304, nonce, key);
//MessageDigest sha256 = MessageDigest.getInstance("SHA256");
//byte[]        hash   = sha256.digest(stream);
//
//assertTrue("Invalid crypttext stream", Arrays.equals(HASH, hash));
//
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] key   = new byte[TweetNaCl.STREAM_SALSA20_KEYBYTES];
//            byte[] nonce = new byte[TweetNaCl.STREAM_SALSA20_NONCEBYTES];
//            int    N     = 16384 + random.nextInt(16384);
//            
//            random.nextBytes(key);
//            random.nextBytes(nonce);
//            
//            byte[] p = tweetnacl.cryptoStreamSalsa20 (N,nonce,key);
//            byte[] q = tweetnacl.cryptoStreamSalsa20X(N,nonce,key);
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
//            byte[] key     = new byte[TweetNaCl.STREAM_SALSA20_KEYBYTES];
//            byte[] nonce   = new byte[TweetNaCl.STREAM_SALSA20_NONCEBYTES];
//            int    N       = 16384 + random.nextInt(16384);
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
//                p = tweetnacl.cryptoStreamSalsa20(N,nonce,key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoStreamSalsa20X(N,nonce,key);
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
