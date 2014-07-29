package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] IV = { (byte) 0x6a, (byte) 0x09, (byte) 0xe6, (byte) 0x67,
//                (byte) 0xf3, (byte) 0xbc, (byte) 0xc9, (byte) 0x08,
//                (byte) 0xbb, (byte) 0x67, (byte) 0xae, (byte) 0x85,
//                (byte) 0x84, (byte) 0xca, (byte) 0xa7, (byte) 0x3b,
//                (byte) 0x3c, (byte) 0x6e, (byte) 0xf3, (byte) 0x72,
//                (byte) 0xfe, (byte) 0x94, (byte) 0xf8, (byte) 0x2b,
//                (byte) 0xa5, (byte) 0x4f, (byte) 0xf5, (byte) 0x3a,
//                (byte) 0x5f, (byte) 0x1d, (byte) 0x36, (byte) 0xf1,
//                (byte) 0x51, (byte) 0x0e, (byte) 0x52, (byte) 0x7f,
//                (byte) 0xad, (byte) 0xe6, (byte) 0x82, (byte) 0xd1,
//                (byte) 0x9b, (byte) 0x05, (byte) 0x68, (byte) 0x8c,
//                (byte) 0x2b, (byte) 0x3e, (byte) 0x6c, (byte) 0x1f,
//                (byte) 0x1f, (byte) 0x83, (byte) 0xd9, (byte) 0xab,
//                (byte) 0xfb, (byte) 0x41, (byte) 0xbd, (byte) 0x6b,
//                (byte) 0x5b, (byte) 0xe0, (byte) 0xcd, (byte) 0x19,
//                (byte) 0x13, (byte) 0x7e, (byte) 0x21, (byte) 0x79 };
//
//        final byte[] m = { (byte) 0x74, (byte) 0x65, (byte) 0x73, (byte) 0x74,
//                (byte) 0x69, (byte) 0x6e, (byte) 0x67, (byte) 0x0a,
//                (byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40 };
//
//        final byte[] ref = { (byte) 0x24, (byte) 0xf9, (byte) 0x50,
//                (byte) 0xaa, (byte) 0xc7, (byte) 0xb9, (byte) 0xea,
//                (byte) 0x9b, (byte) 0x3c, (byte) 0xb7, (byte) 0x28,
//                (byte) 0x22, (byte) 0x8a, (byte) 0x0c, (byte) 0x82,
//                (byte) 0xb6, (byte) 0x7c, (byte) 0x39, (byte) 0xe9,
//                (byte) 0x6b, (byte) 0x4b, (byte) 0x34, (byte) 0x47,
//                (byte) 0x98, (byte) 0x87, (byte) 0x0d, (byte) 0x5d,
//                (byte) 0xae, (byte) 0xe9, (byte) 0x3e, (byte) 0x3a,
//                (byte) 0xe5, (byte) 0x93, (byte) 0x1b, (byte) 0xaa,
//                (byte) 0xe8, (byte) 0xc7, (byte) 0xca, (byte) 0xcf,
//                (byte) 0xea, (byte) 0x4b, (byte) 0x62, (byte) 0x94,
//                (byte) 0x52, (byte) 0xc3, (byte) 0x80, (byte) 0x26,
//                (byte) 0xa8, (byte) 0x1d, (byte) 0x13, (byte) 0x8b,
//                (byte) 0xc7, (byte) 0xaa, (byte) 0xd1, (byte) 0xaf,
//                (byte) 0x3e, (byte) 0xf7, (byte) 0xbf, (byte) 0xd5,
//                (byte) 0xec, (byte) 0x64, (byte) 0x6d, (byte) 0x6c, (byte) 0x28 };
//
//        assertTrue("Invalid hash",Arrays.equals(ref, tweetnacl.cryptoHashBlocks2(IV, m)));
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] iv      = new byte[TweetNaCl.HASHBLOCKS_STATEBYTES];
//            byte[] message = new byte[TweetNaCl.HASHBLOCKS_BLOCKBYTES * random.nextInt(256)];
//            
//            random.nextBytes(iv);
//            random.nextBytes(message);
//
//            byte[] p = tweetnacl.cryptoHashBlocks (iv,message);
//            byte[] q = tweetnacl.cryptoHashBlocks2(iv,message);
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
//            byte[] iv      = new byte[TweetNaCl.HASHBLOCKS_STATEBYTES];
//            byte[] message = new byte[TweetNaCl.HASHBLOCKS_BLOCKBYTES * random.nextInt(256)];
//            
//            random.nextBytes(iv);
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
//                p = tweetnacl.cryptoHashBlocks (iv,message);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoHashBlocks2 (iv,message);
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
