package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        final byte[] x = "testing\n".getBytes();
//        final byte[] ref = { (byte) 0x24, (byte) 0xf9, (byte) 0x50, (byte) 0xaa, 
//                             (byte) 0xc7, (byte) 0xb9, (byte) 0xea, (byte) 0x9b,
//                             (byte) 0x3c, (byte) 0xb7, (byte) 0x28, (byte) 0x22,
//                             (byte) 0x8a, (byte) 0x0c, (byte) 0x82, (byte) 0xb6,
//                             (byte) 0x7c, (byte) 0x39, (byte) 0xe9, (byte) 0x6b,
//                             (byte) 0x4b, (byte) 0x34, (byte) 0x47, (byte) 0x98,
//                             (byte) 0x87, (byte) 0x0d, (byte) 0x5d, (byte) 0xae,
//                             (byte) 0xe9, (byte) 0x3e, (byte) 0x3a, (byte) 0xe5,
//                             (byte) 0x93, (byte) 0x1b, (byte) 0xaa, (byte) 0xe8,
//                             (byte) 0xc7, (byte) 0xca, (byte) 0xcf, (byte) 0xea,
//                             (byte) 0x4b, (byte) 0x62, (byte) 0x94, (byte) 0x52, 
//                             (byte) 0xc3, (byte) 0x80, (byte) 0x26, (byte) 0xa8,
//                             (byte) 0x1d, (byte) 0x13, (byte) 0x8b, (byte) 0xc7,
//                             (byte) 0xaa, (byte) 0xd1, (byte) 0xaf, (byte) 0x3e,
//                             (byte) 0xf7, (byte) 0xbf, (byte) 0xd5, (byte) 0xec,
//                             (byte) 0x64, (byte) 0x6d, (byte) 0x6c, (byte) 0x28 };
//
//        assertTrue("Invalid hash", Arrays.equals(ref, tweetnacl.cryptoHash2(x)));
//    }

//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            byte[] message = new byte[32 + random.nextInt(16384)];
//            
//            random.nextBytes(message);
//
//            byte[] p = tweetnacl.cryptoHash (message);
//            byte[] q = tweetnacl.cryptoHash2(message);
//
//            assertTrue("OOOOPS !",Arrays.equals(p,q));
//        }
//    }

//    public void testZ() throws Exception {
//        long DT  = 0;
//        long DT2 = 0;
//        
//        for (int i=0; i<64; i++) {
//            byte[] message = new byte[32 + random.nextInt(16384)];
//            
//            random.nextBytes(message);
//
//            Arrays.fill(message,0,32,(byte) 0);
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
//                p = tweetnacl.cryptoHash(message);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoHash2(message);
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
