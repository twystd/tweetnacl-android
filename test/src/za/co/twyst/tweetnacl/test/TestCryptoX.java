package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

    public void testX() throws Exception {
        final byte[] key = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d,
                (byte) 0x5b, (byte) 0xa4, (byte) 0xce, (byte) 0x2d,
                (byte) 0xe1, (byte) 0x72, (byte) 0x8e, (byte) 0x3b,
                (byte) 0xf4, (byte) 0x80, (byte) 0x35, (byte) 0x0f,
                (byte) 0x25, (byte) 0xe0, (byte) 0x7e, (byte) 0x21,
                (byte) 0xc9, (byte) 0x47, (byte) 0xd1, (byte) 0x9e,
                (byte) 0x33, (byte) 0x76, (byte) 0xf0, (byte) 0x9b,
                (byte) 0x3c, (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42 };

        final byte[] constant = { (byte) 0x65, (byte) 0x78, (byte) 0x70,
                (byte) 0x61, (byte) 0x6e, (byte) 0x64, (byte) 0x20,
                (byte) 0x33, (byte) 0x32, (byte) 0x2d, (byte) 0x62,
                (byte) 0x79, (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6b };

        final byte[] in = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };

        final byte[] ref = { (byte) 0x1b, (byte) 0x27, (byte) 0x55,
                (byte) 0x64, (byte) 0x73, (byte) 0xe9, (byte) 0x85,
                (byte) 0xd4, (byte) 0x62, (byte) 0xcd, (byte) 0x51,
                (byte) 0x19, (byte) 0x7a, (byte) 0x9a, (byte) 0x46,
                (byte) 0xc7, (byte) 0x60, (byte) 0x09, (byte) 0x54,
                (byte) 0x9e, (byte) 0xac, (byte) 0x64, (byte) 0x74,
                (byte) 0xf2, (byte) 0x06, (byte) 0xc4, (byte) 0xee,
                (byte) 0x08, (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89 };

        assertTrue(
                "Invalid out",
                Arrays.equals(ref,
                        tweetnacl.cryptoCoreHSalsa20(in, key, constant)));
    }

//    public void testY() throws Exception {
//        for (int i=0; i<1000; i++) {
//            KeyPair alice  = tweetnacl.cryptoBoxKeyPair();
//            KeyPair bob    = tweetnacl.cryptoBoxKeyPair();
//            byte[] nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
//            byte[] message = new byte[32 + random.nextInt(16384)];
//            
//            random.nextBytes(nonce);
//            random.nextBytes(message);
//
//            Arrays.fill(message,0,32,(byte) 0);
//
//            byte[] crypttext = tweetnacl.cryptoBox            (message,nonce,bob.publicKey,alice.secretKey);
//            byte[] key       = tweetnacl.cryptoBoxBeforeNM    (alice.publicKey,bob.secretKey);
//            byte[] p         = tweetnacl.cryptoBoxOpenAfterNM (crypttext,nonce,key);
//            byte[] q         = tweetnacl.cryptoBoxOpenAfterNM2(crypttext,nonce,key);
//
//            assertTrue("OOOOPS !",Arrays.equals(p,q));
//        }
//    }

//    public void testZ() throws Exception {
//        long DT  = 0;
//        long DT2 = 0;
//        
//        for (int i=0; i<64; i++) {
//            KeyPair alice  = tweetnacl.cryptoBoxKeyPair();
//            KeyPair bob    = tweetnacl.cryptoBoxKeyPair();
//            byte[] nonce   = new byte[TweetNaCl.BOX_NONCEBYTES];
//            byte[] message = new byte[32 + random.nextInt(16384)];
//            
//            random.nextBytes(nonce);
//            random.nextBytes(message);
//
//            Arrays.fill(message,0,32,(byte) 0);
//
//            byte[] crypttext = tweetnacl.cryptoBox            (message,nonce,bob.publicKey,alice.secretKey);
//            byte[] key       = tweetnacl.cryptoBoxBeforeNM    (alice.publicKey,bob.secretKey);
//            byte[] p       = new byte[0];
//            byte[] q       = new byte[0];
//            long   start;
//            long   dt;
//            long   dt2;
//            
//            start = System.currentTimeMillis();
//            
//            for (int j=0; j<1024; j++) {
//                p = tweetnacl.cryptoBoxOpenAfterNM (crypttext,nonce,key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoBoxOpenAfterNM2(crypttext,nonce,key);
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
