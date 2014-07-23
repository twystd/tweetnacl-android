package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoBoxX extends TweetNaClTest {
    // CONSTANTS

    private static final byte[] CIPHERTEXT = { (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf3,
            (byte) 0xff, (byte) 0xc7, (byte) 0x70, (byte) 0x3f, (byte) 0x94,
            (byte) 0x00, (byte) 0xe5, (byte) 0x2a, (byte) 0x7d, (byte) 0xfb,
            (byte) 0x4b, (byte) 0x3d, (byte) 0x33, (byte) 0x05, (byte) 0xd9,
            (byte) 0x8e, (byte) 0x99, (byte) 0x3b, (byte) 0x9f, (byte) 0x48,
            (byte) 0x68, (byte) 0x12, (byte) 0x73, (byte) 0xc2, (byte) 0x96,
            (byte) 0x50, (byte) 0xba, (byte) 0x32, (byte) 0xfc, (byte) 0x76,
            (byte) 0xce, (byte) 0x48, (byte) 0x33, (byte) 0x2e, (byte) 0xa7,
            (byte) 0x16, (byte) 0x4d, (byte) 0x96, (byte) 0xa4, (byte) 0x47,
            (byte) 0x6f, (byte) 0xb8, (byte) 0xc5, (byte) 0x31, (byte) 0xa1,
            (byte) 0x18, (byte) 0x6a, (byte) 0xc0, (byte) 0xdf, (byte) 0xc1,
            (byte) 0x7c, (byte) 0x98, (byte) 0xdc, (byte) 0xe8, (byte) 0x7b,
            (byte) 0x4d, (byte) 0xa7, (byte) 0xf0, (byte) 0x11, (byte) 0xec,
            (byte) 0x48, (byte) 0xc9, (byte) 0x72, (byte) 0x71, (byte) 0xd2,
            (byte) 0xc2, (byte) 0x0f, (byte) 0x9b, (byte) 0x92, (byte) 0x8f,
            (byte) 0xe2, (byte) 0x27, (byte) 0x0d, (byte) 0x6f, (byte) 0xb8,
            (byte) 0x63, (byte) 0xd5, (byte) 0x17, (byte) 0x38, (byte) 0xb4,
            (byte) 0x8e, (byte) 0xee, (byte) 0xe3, (byte) 0x14, (byte) 0xa7,
            (byte) 0xcc, (byte) 0x8a, (byte) 0xb9, (byte) 0x32, (byte) 0x16,
            (byte) 0x45, (byte) 0x48, (byte) 0xe5, (byte) 0x26, (byte) 0xae,
            (byte) 0x90, (byte) 0x22, (byte) 0x43, (byte) 0x68, (byte) 0x51,
            (byte) 0x7a, (byte) 0xcf, (byte) 0xea, (byte) 0xbd, (byte) 0x6b,
            (byte) 0xb3, (byte) 0x73, (byte) 0x2b, (byte) 0xc0, (byte) 0xe9,
            (byte) 0xda, (byte) 0x99, (byte) 0x83, (byte) 0x2b, (byte) 0x61,
            (byte) 0xca, (byte) 0x01, (byte) 0xb6, (byte) 0xde, (byte) 0x56,
            (byte) 0x24, (byte) 0x4a, (byte) 0x9e, (byte) 0x88, (byte) 0xd5,
            (byte) 0xf9, (byte) 0xb3, (byte) 0x79, (byte) 0x73, (byte) 0xf6,
            (byte) 0x22, (byte) 0xa4, (byte) 0x3d, (byte) 0x14, (byte) 0xa6,
            (byte) 0x59, (byte) 0x9b, (byte) 0x1f, (byte) 0x65, (byte) 0x4c,
            (byte) 0xb4, (byte) 0x5a, (byte) 0x74, (byte) 0xe3, (byte) 0x55,
            (byte) 0xa5 };

    private static final byte[] MESSAGE = { (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0xbe, (byte) 0x07, (byte) 0x5f, (byte) 0xc5, (byte) 0x3c,
            (byte) 0x81, (byte) 0xf2, (byte) 0xd5, (byte) 0xcf, (byte) 0x14,
            (byte) 0x13, (byte) 0x16, (byte) 0xeb, (byte) 0xeb, (byte) 0x0c,
            (byte) 0x7b, (byte) 0x52, (byte) 0x28, (byte) 0xc5, (byte) 0x2a,
            (byte) 0x4c, (byte) 0x62, (byte) 0xcb, (byte) 0xd4, (byte) 0x4b,
            (byte) 0x66, (byte) 0x84, (byte) 0x9b, (byte) 0x64, (byte) 0x24,
            (byte) 0x4f, (byte) 0xfc, (byte) 0xe5, (byte) 0xec, (byte) 0xba,
            (byte) 0xaf, (byte) 0x33, (byte) 0xbd, (byte) 0x75, (byte) 0x1a,
            (byte) 0x1a, (byte) 0xc7, (byte) 0x28, (byte) 0xd4, (byte) 0x5e,
            (byte) 0x6c, (byte) 0x61, (byte) 0x29, (byte) 0x6c, (byte) 0xdc,
            (byte) 0x3c, (byte) 0x01, (byte) 0x23, (byte) 0x35, (byte) 0x61,
            (byte) 0xf4, (byte) 0x1d, (byte) 0xb6, (byte) 0x6c, (byte) 0xce,
            (byte) 0x31, (byte) 0x4a, (byte) 0xdb, (byte) 0x31, (byte) 0x0e,
            (byte) 0x3b, (byte) 0xe8, (byte) 0x25, (byte) 0x0c, (byte) 0x46,
            (byte) 0xf0, (byte) 0x6d, (byte) 0xce, (byte) 0xea, (byte) 0x3a,
            (byte) 0x7f, (byte) 0xa1, (byte) 0x34, (byte) 0x80, (byte) 0x57,
            (byte) 0xe2, (byte) 0xf6, (byte) 0x55, (byte) 0x6a, (byte) 0xd6,
            (byte) 0xb1, (byte) 0x31, (byte) 0x8a, (byte) 0x02, (byte) 0x4a,
            (byte) 0x83, (byte) 0x8f, (byte) 0x21, (byte) 0xaf, (byte) 0x1f,
            (byte) 0xde, (byte) 0x04, (byte) 0x89, (byte) 0x77, (byte) 0xeb,
            (byte) 0x48, (byte) 0xf5, (byte) 0x9f, (byte) 0xfd, (byte) 0x49,
            (byte) 0x24, (byte) 0xca, (byte) 0x1c, (byte) 0x60, (byte) 0x90,
            (byte) 0x2e, (byte) 0x52, (byte) 0xf0, (byte) 0xa0, (byte) 0x89,
            (byte) 0xbc, (byte) 0x76, (byte) 0x89, (byte) 0x70, (byte) 0x40,
            (byte) 0xe0, (byte) 0x82, (byte) 0xf9, (byte) 0x37, (byte) 0x76,
            (byte) 0x38, (byte) 0x48, (byte) 0x64, (byte) 0x5e, (byte) 0x07,
            (byte) 0x05 };

    private static final byte[] NONCE = { (byte) 0x69, (byte) 0x69,
            (byte) 0x6e, (byte) 0xe9, (byte) 0x55, (byte) 0xb6, (byte) 0x2b,
            (byte) 0x73, (byte) 0xcd, (byte) 0x62, (byte) 0xbd, (byte) 0xa8,
            (byte) 0x75, (byte) 0xfc, (byte) 0x73, (byte) 0xd6, (byte) 0x82,
            (byte) 0x19, (byte) 0xe0, (byte) 0x03, (byte) 0x6b, (byte) 0x7a,
            (byte) 0x0b, (byte) 0x37 };

    private static final byte[] SK = { (byte) 0x77, (byte) 0x07,
            (byte) 0x6d, (byte) 0x0a, (byte) 0x73, (byte) 0x18, (byte) 0xa5,
            (byte) 0x7d, (byte) 0x3c, (byte) 0x16, (byte) 0xc1, (byte) 0x72,
            (byte) 0x51, (byte) 0xb2, (byte) 0x66, (byte) 0x45, (byte) 0xdf,
            (byte) 0x4c, (byte) 0x2f, (byte) 0x87, (byte) 0xeb, (byte) 0xc0,
            (byte) 0x99, (byte) 0x2a, (byte) 0xb1, (byte) 0x77, (byte) 0xfb,
            (byte) 0xa5, (byte) 0x1d, (byte) 0xb9, (byte) 0x2c, (byte) 0x2a };

    private static final byte[] PK = { (byte) 0xde, (byte) 0x9e,
            (byte) 0xdb, (byte) 0x7d, (byte) 0x7b, (byte) 0x7d, (byte) 0xc1,
            (byte) 0xb4, (byte) 0xd3, (byte) 0x5b, (byte) 0x61, (byte) 0xc2,
            (byte) 0xec, (byte) 0xe4, (byte) 0x35, (byte) 0x37, (byte) 0x3f,
            (byte) 0x83, (byte) 0x43, (byte) 0xc8, (byte) 0x5b, (byte) 0x78,
            (byte) 0x67, (byte) 0x4d, (byte) 0xad, (byte) 0xfc, (byte) 0x7e,
            (byte) 0x14, (byte) 0x6f, (byte) 0x88, (byte) 0x2b, (byte) 0x4f, };

    // UNIT TESTS

    /**
     * crypto_box (adapted from tests/box.c)
     * 
     */
    public void testBox2() throws Exception {
        byte[] ciphertext = tweetnacl.cryptoBox2(MESSAGE, NONCE, PK, SK);

        for (int i = 16; i < CIPHERTEXT.length; i++) {
            assertEquals("Invalid byte " + i, (int) (CIPHERTEXT[i] & 0x00ff),(int) (ciphertext[i] & 0x00ff));
        }
    }

    /**
     * crypto_box (adapted from tests/box.c)
     * 
     */
    public void testBox2X() throws Exception {
        for (int i=0; i<1000; i++) {
            int    N       = 1000 + random.nextInt(10000);
            byte[] message = new byte[N];
            byte[] nonce   = new byte[24];
            
            random.nextBytes(message);
            random.nextBytes(nonce);

            assertTrue("OOOOPS !",Arrays.equals(tweetnacl.cryptoBox (message,nonce,PK,SK),
                                                tweetnacl.cryptoBox2(message,nonce,PK,SK)));
        }
    }


    /**
     * crypto_box (adapted from tests/box.c)
     * 
     */
    public void testBox2P() throws Exception {
        long DT  = 0;
        long DT2 = 0;
        
        for (int i=0; i<32; i++) {
            int    N       = 1024 + random.nextInt(16384);
            byte[] message = new byte[N];
            byte[] nonce   = new byte[24];
            byte[] c       = new byte[0];
            byte[] c2      = new byte[0];
            long   start;
            long   dt;
            long   dt2;
            
            random.nextBytes(message);
            random.nextBytes(nonce);

            start = System.currentTimeMillis();
            
            for (int j=0; j<1024; j++) {
                c = tweetnacl.cryptoBox (message,nonce,PK,SK);
            }
            
            dt    = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();

            for (int j=0; j<1024; j++) {
                c2 = tweetnacl.cryptoBox2(message,nonce,PK,SK);
            }

            dt2  = System.currentTimeMillis() - start;
            DT  += dt;
            DT2 += dt2;

            assertTrue(Arrays.equals(c,c2));            

            android.util.Log.i(TAG,"DT: " + dt + "  " + dt2 + "  " + DT + "  " + DT2 + "  " + ((double) DT - (double) DT2)/(double) DT);
        }

        android.util.Log.i(TAG,"DT/FINAL: " + DT + "  " + DT2);
    }
}