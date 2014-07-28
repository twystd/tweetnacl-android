package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoCore extends TweetNaClTest { 
    // CONSTANTS

    // UNIT TESTS

    /**
     * crypto_core_hsalsa20 (adapted from tests/core1.c)
     * 
     */
    public void testCore1() throws Exception { 
        final byte[] key = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d, (byte) 0x5b, 
                             (byte) 0xa4, (byte) 0xce, (byte) 0x2d, (byte) 0xe1,
                             (byte) 0x72, (byte) 0x8e, (byte) 0x3b, (byte) 0xf4,
                             (byte) 0x80, (byte) 0x35, (byte) 0x0f, (byte) 0x25,
                             (byte) 0xe0, (byte) 0x7e, (byte) 0x21, (byte) 0xc9,
                             (byte) 0x47, (byte) 0xd1, (byte) 0x9e, (byte) 0x33,
                             (byte) 0x76, (byte) 0xf0, (byte) 0x9b, (byte) 0x3c,
                             (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42 };

        final byte[] constant = { (byte) 0x65, (byte) 0x78, (byte) 0x70, (byte) 0x61,
                                  (byte) 0x6e, (byte) 0x64, (byte) 0x20, (byte) 0x33,
                                  (byte) 0x32, (byte) 0x2d, (byte) 0x62, (byte) 0x79,
                                  (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6b };

        final byte[] in = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };

        final byte[] ref = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64,
                             (byte) 0x73, (byte) 0xe9, (byte) 0x85, (byte) 0xd4,
                             (byte) 0x62, (byte) 0xcd, (byte) 0x51, (byte) 0x19,
                             (byte) 0x7a, (byte) 0x9a, (byte) 0x46, (byte) 0xc7,
                             (byte) 0x60, (byte) 0x09, (byte) 0x54, (byte) 0x9e,
                             (byte) 0xac, (byte) 0x64, (byte) 0x74, (byte) 0xf2,
                             (byte) 0x06, (byte) 0xc4, (byte) 0xee, (byte) 0x08,
                             (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89 };

        assertTrue("Invalid out",Arrays.equals(ref,tweetnacl.cryptoCoreHSalsa20(in,key,constant)));
    }

    /**
     * crypto_core_hsalsa20 (adapted from tests/core2.c)
     * 
     */
    public void testCore2() throws Exception {
        final byte[] key = { (byte) 0x1b, (byte) 0x27, (byte) 0x55, (byte) 0x64,
                             (byte) 0x73, (byte) 0xe9, (byte) 0x85, (byte) 0xd4,
                             (byte) 0x62, (byte) 0xcd, (byte) 0x51, (byte) 0x19,
                             (byte) 0x7a, (byte) 0x9a, (byte) 0x46, (byte) 0xc7,
                             (byte) 0x60, (byte) 0x09, (byte) 0x54, (byte) 0x9e,
                             (byte) 0xac, (byte) 0x64, (byte) 0x74, (byte) 0xf2,
                             (byte) 0x06, (byte) 0xc4, (byte) 0xee, (byte) 0x08,
                             (byte) 0x44, (byte) 0xf6, (byte) 0x83, (byte) 0x89 };

        final byte[] constant = { (byte) 0x65, (byte) 0x78, (byte) 0x70, (byte) 0x61,
                                  (byte) 0x6e, (byte) 0x64, (byte) 0x20, (byte) 0x33,
                                  (byte) 0x32, (byte) 0x2d, (byte) 0x62, (byte) 0x79,
                                  (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6b };

        final byte[] in = { (byte) 0x69, (byte) 0x69, (byte) 0x6e, (byte) 0xe9,
                            (byte) 0x55, (byte) 0xb6, (byte) 0x2b, (byte) 0x73,
                            (byte) 0xcd, (byte) 0x62, (byte) 0xbd, (byte) 0xa8,
                            (byte) 0x75, (byte) 0xfc, (byte) 0x73, (byte) 0xd6 };

        final byte[] ref = { (byte) 0xdc, (byte) 0x90, (byte) 0x8d, (byte) 0xda,
                             (byte) 0x0b, (byte) 0x93, (byte) 0x44, (byte) 0xa9,
                             (byte) 0x53, (byte) 0x62, (byte) 0x9b, (byte) 0x73,
                             (byte) 0x38, (byte) 0x20, (byte) 0x77, (byte) 0x88,
                             (byte) 0x80, (byte) 0xf3, (byte) 0xce, (byte) 0xb4,
                             (byte) 0x21, (byte) 0xbb, (byte) 0x61, (byte) 0xb9,
                             (byte) 0x1c, (byte) 0xbd, (byte) 0x4c, (byte) 0x3e,
                             (byte) 0x66, (byte) 0x25, (byte) 0x6c, (byte) 0xe4 };

        assertTrue("Invalid out",Arrays.equals(ref,tweetnacl.cryptoCoreHSalsa20(in,key,constant)));
    }

    /**
     * crypto_core_salsa20 (adapted from tests/core4.c)
     * 
     */
    public void testCore4() throws Exception {
        final byte[] key = { (byte) 1,   (byte) 2,   (byte) 3,   (byte) 4, 
                             (byte) 5,   (byte) 6,   (byte) 7,   (byte) 8,
                             (byte) 9,   (byte) 10,  (byte) 11,  (byte) 12,
                             (byte) 13,  (byte) 14,  (byte) 15,  (byte) 16,
                             (byte) 201, (byte) 202, (byte) 203, (byte) 204,
                             (byte) 205, (byte) 206, (byte) 207, (byte) 208,
                             (byte) 209, (byte) 210, (byte) 211, (byte) 212,
                             (byte) 213, (byte) 214, (byte) 215, (byte) 216 };

        final byte[] constant = { (byte) 101, (byte) 120, (byte) 112, (byte) 97, 
                                  (byte) 110, (byte) 100, (byte) 32,  (byte) 51,
                                  (byte) 50,  (byte) 45,  (byte) 98,  (byte) 121,
                                  (byte) 116, (byte) 101, (byte) 32,  (byte) 107 };

        final byte[] in = { (byte) 101, (byte) 102, (byte) 103, (byte) 104,
                            (byte) 105, (byte) 106, (byte) 107, (byte) 108,
                            (byte) 109, (byte) 110, (byte) 111, (byte) 112,
                            (byte) 113, (byte) 114, (byte) 115, (byte) 116 };

        final byte[] ref = { (byte) 69,  (byte) 37,  (byte) 68,  (byte) 39,
                             (byte) 41,  (byte) 15,  (byte) 107, (byte) 193, 
                             (byte) 255, (byte) 139, (byte) 122, (byte) 6,
                             (byte) 170, (byte) 233, (byte) 217, (byte) 98,
                             (byte) 89,  (byte) 144, (byte) 182, (byte) 106,
                             (byte) 21,  (byte) 51,  (byte) 200, (byte) 65,
                             (byte) 239, (byte) 49,  (byte) 222, (byte) 34,
                             (byte) 215, (byte) 114, (byte) 40,  (byte) 126,
                             (byte) 104, (byte) 197, (byte) 7,   (byte) 225,
                             (byte) 197, (byte) 153, (byte) 31,  (byte) 2,  
                             (byte) 102, (byte) 78,  (byte) 76,  (byte) 176,
                             (byte) 84,  (byte) 245, (byte) 246, (byte) 184,
                             (byte) 177, (byte) 160, (byte) 133, (byte) 130,
                             (byte) 6,   (byte) 72,  (byte) 149, (byte) 119,
                             (byte) 192, (byte) 195, (byte) 132, (byte) 236,
                             (byte) 234, (byte) 103, (byte) 246, (byte) 74 };

        assertTrue("Invalid out",Arrays.equals(ref,tweetnacl.cryptoCoreSalsa20(in,key,constant)));
    }

    /**
     * crypto_core_hsalsa20 (adapted from tests/core5.c)
     * 
     */
    public void testCore5() throws Exception {
        final byte[] key = { (byte) 0xee, (byte) 0x30, (byte) 0x4f, (byte) 0xca,
                             (byte) 0x27, (byte) 0x00, (byte) 0x8d, (byte) 0x8c,
                             (byte) 0x12, (byte) 0x6f, (byte) 0x90, (byte) 0x02,
                             (byte) 0x79, (byte) 0x01, (byte) 0xd8, (byte) 0x0f,
                             (byte) 0x7f, (byte) 0x1d, (byte) 0x8b, (byte) 0x8d,
                             (byte) 0xc9, (byte) 0x36, (byte) 0xcf, (byte) 0x3b,
                             (byte) 0x9f, (byte) 0x81, (byte) 0x96, (byte) 0x92,
                             (byte) 0x82, (byte) 0x7e, (byte) 0x57, (byte) 0x77 };

        final byte[] constant = { 101, 120, 112, 97, 
                                  110, 100, 32,  51,
                                  50,  45,  98,  121,
                                  116, 101, 32,  107 };

        final byte[] in = { (byte) 0x81, (byte) 0x91, (byte) 0x8e, (byte) 0xf2,
                            (byte) 0xa5, (byte) 0xe0, (byte) 0xda, (byte) 0x9b,
                            (byte) 0x3e, (byte) 0x90, (byte) 0x60, (byte) 0x52,
                            (byte) 0x1e, (byte) 0x4b, (byte) 0xb3, (byte) 0x52 };

        final byte[] ref = { (byte) 0xbc, (byte) 0x1b, (byte) 0x30, (byte) 0xfc, 
                             (byte) 0x07, (byte) 0x2c, (byte) 0xc1, (byte) 0x40,
                             (byte) 0x75, (byte) 0xe4, (byte) 0xba, (byte) 0xa7,
                             (byte) 0x31, (byte) 0xb5, (byte) 0xa8, (byte) 0x45,
                             (byte) 0xea, (byte) 0x9b, (byte) 0x11, (byte) 0xe9,
                             (byte) 0xa5, (byte) 0x19, (byte) 0x1f, (byte) 0x94,
                             (byte) 0xe1, (byte) 0x8c, (byte) 0xba, (byte) 0x8f,
                             (byte) 0xd8, (byte) 0x21, (byte) 0xa7, (byte) 0xcd };

        assertTrue("Invalid out",Arrays.equals(ref,tweetnacl.cryptoCoreHSalsa20(in,key,constant)));
    }

    /**
     * crypto_core_salsa20 (adapted from tests/core6.c)
     * 
     */
    public void testCore6() throws Exception {
        final byte[] key = { (byte) 0xee, (byte) 0x30, (byte) 0x4f, (byte) 0xca, 
                             (byte) 0x27, (byte) 0x00, (byte) 0x8d, (byte) 0x8c,
                             (byte) 0x12, (byte) 0x6f, (byte) 0x90, (byte) 0x02,
                             (byte) 0x79, (byte) 0x01, (byte) 0xd8, (byte) 0x0f,
                             (byte) 0x7f, (byte) 0x1d, (byte) 0x8b, (byte) 0x8d,
                             (byte) 0xc9, (byte) 0x36, (byte) 0xcf, (byte) 0x3b,
                             (byte) 0x9f, (byte) 0x81, (byte) 0x96, (byte) 0x92,
                             (byte) 0x82, (byte) 0x7e, (byte) 0x57, (byte) 0x77 };

        final byte[] constant = { 101, 120, 112, 97, 
                                  110, 100, 32,  51,
                                  50,  45,  98,  121,
                                  116, 101, 32, 107 };

        final byte[] in = { (byte) 0x81, (byte) 0x91, (byte) 0x8e, (byte) 0xf2,
                (byte) 0xa5, (byte) 0xe0, (byte) 0xda, (byte) 0x9b,
                (byte) 0x3e, (byte) 0x90, (byte) 0x60, (byte) 0x52,
                (byte) 0x1e, (byte) 0x4b, (byte) 0xb3, (byte) 0x52 };

        final byte[] ref = { (byte) 0xbc, (byte) 0x1b, (byte) 0x30,
                (byte) 0xfc, (byte) 0x07, (byte) 0x2c, (byte) 0xc1,
                (byte) 0x40, (byte) 0x75, (byte) 0xe4, (byte) 0xba,
                (byte) 0xa7, (byte) 0x31, (byte) 0xb5, (byte) 0xa8,
                (byte) 0x45, (byte) 0xea, (byte) 0x9b, (byte) 0x11,
                (byte) 0xe9, (byte) 0xa5, (byte) 0x19, (byte) 0x1f,
                (byte) 0x94, (byte) 0xe1, (byte) 0x8c, (byte) 0xba,
                (byte) 0x8f, (byte) 0xd8, (byte) 0x21, (byte) 0xa7, (byte) 0xcd };

        byte[] out    = tweetnacl.cryptoCoreSalsa20(in, key, constant);
        byte[] bytes = new byte[ref.length];
        int    ix    = 0;
        int    borrow;
        int    xi;
        int    yi;

        for (int offset = 0; offset < 4; offset++) {
            borrow = 0;

            for (int i = 0; i < 4; i++) {
                xi          = out[i + offset * 20] & 0x00ff;
                yi          = constant[i + offset * 4] & 0x00ff;
                bytes[ix++] = (byte) ((xi - yi - borrow) & 0x00ff);
                borrow      = xi < yi + borrow ? 1 : 0;
            }
        }

        for (int offset = 0; offset < 4; offset++) {
            borrow = 0;

            for (int i = 0; i < 4; i++) {
                xi          = out[i + 24 + offset * 4] & 0x00ff;
                yi          = in[i + offset * 4] & 0x00ff;
                bytes[ix++] = (byte) ((xi - yi - borrow) & 0x00ff);
                borrow      = xi < yi + borrow ? 1 : 0;
            }
        }

        assertTrue("Invalid out", Arrays.equals(ref, bytes));
    }
}
