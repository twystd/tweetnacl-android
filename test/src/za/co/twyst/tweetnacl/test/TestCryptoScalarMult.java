package za.co.twyst.tweetnacl.test;

import java.util.Arrays;

public class TestCryptoScalarMult extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

    /**
     * crypto_scalarmult_base (adapted from tests/scalarmult.c)
     * 
     */
    public void testCryptoScalarMultBase() throws Exception {
        final byte[] n = { (byte) 0x77, (byte) 0x07, (byte) 0x6d, (byte) 0x0a,
                           (byte) 0x73, (byte) 0x18, (byte) 0xa5, (byte) 0x7d,
                           (byte) 0x3c, (byte) 0x16, (byte) 0xc1, (byte) 0x72,
                           (byte) 0x51, (byte) 0xb2, (byte) 0x66, (byte) 0x45,
                           (byte) 0xdf, (byte) 0x4c, (byte) 0x2f, (byte) 0x87,
                           (byte) 0xeb, (byte) 0xc0, (byte) 0x99, (byte) 0x2a,
                           (byte) 0xb1, (byte) 0x77, (byte) 0xfb, (byte) 0xa5,
                           (byte) 0x1d, (byte) 0xb9, (byte) 0x2c, (byte) 0x2a };

        final byte[] ref = { (byte) 0x85, (byte) 0x20, (byte) 0xf0, (byte) 0x09,
                             (byte) 0x89, (byte) 0x30, (byte) 0xa7, (byte) 0x54,
                             (byte) 0x74, (byte) 0x8b, (byte) 0x7d, (byte) 0xdc,
                             (byte) 0xb4, (byte) 0x3e, (byte) 0xf7, (byte) 0x5a,
                             (byte) 0x0d, (byte) 0xbf, (byte) 0x3a, (byte) 0x0d,
                             (byte) 0x26, (byte) 0x38, (byte) 0x1a, (byte) 0xf4,
                             (byte) 0xeb, (byte) 0xa4, (byte) 0xa9, (byte) 0x8e,
                             (byte) 0xaa, (byte) 0x9b, (byte) 0x4e, (byte) 0x6a, };

        assertTrue("Invalid scalarmult_base result", Arrays.equals(ref, tweetnacl.cryptoScalarMultBase(n)));
    }

    /**
     * crypto_scalarmult_base (adapted from tests/scalarmult2.c)
     * 
     */
    public void testCryptoScalarMultBase2() throws Exception {
        final byte[] n = { (byte) 0x5d, (byte) 0xab, (byte) 0x08, (byte) 0x7e,
                           (byte) 0x62, (byte) 0x4a, (byte) 0x8a, (byte) 0x4b,
                           (byte) 0x79, (byte) 0xe1, (byte) 0x7f, (byte) 0x8b,
                           (byte) 0x83, (byte) 0x80, (byte) 0x0e, (byte) 0xe6,
                           (byte) 0x6f, (byte) 0x3b, (byte) 0xb1, (byte) 0x29,
                           (byte) 0x26, (byte) 0x18, (byte) 0xb6, (byte) 0xfd,
                           (byte) 0x1c, (byte) 0x2f, (byte) 0x8b, (byte) 0x27,
                           (byte) 0xff, (byte) 0x88, (byte) 0xe0, (byte) 0xeb };

        final byte[] ref = { (byte) 0xde, (byte) 0x9e, (byte) 0xdb, (byte) 0x7d,
                             (byte) 0x7b, (byte) 0x7d, (byte) 0xc1, (byte) 0xb4,
                             (byte) 0xd3, (byte) 0x5b, (byte) 0x61, (byte) 0xc2,
                             (byte) 0xec, (byte) 0xe4, (byte) 0x35, (byte) 0x37,
                             (byte) 0x3f, (byte) 0x83, (byte) 0x43, (byte) 0xc8,
                             (byte) 0x5b, (byte) 0x78, (byte) 0x67, (byte) 0x4d,
                             (byte) 0xad, (byte) 0xfc, (byte) 0x7e, (byte) 0x14,
                             (byte) 0x6f, (byte) 0x88, (byte) 0x2b, (byte) 0x4f };

        assertTrue("Invalid scalarmult_base result", Arrays.equals(ref, tweetnacl.cryptoScalarMultBase(n)));
    }

    /**
     * crypto_scalarmult (adapted from tests/scalarmult5.c)
     * 
     */
    public void testCryptoScalarMult5() throws Exception {
        final byte[] n = { (byte) 0x77, (byte) 0x07, (byte) 0x6d, (byte) 0x0a,
                           (byte) 0x73, (byte) 0x18, (byte) 0xa5, (byte) 0x7d,
                           (byte) 0x3c, (byte) 0x16, (byte) 0xc1, (byte) 0x72,
                           (byte) 0x51, (byte) 0xb2, (byte) 0x66, (byte) 0x45,
                           (byte) 0xdf, (byte) 0x4c, (byte) 0x2f, (byte) 0x87,
                           (byte) 0xeb, (byte) 0xc0, (byte) 0x99, (byte) 0x2a,
                           (byte) 0xb1, (byte) 0x77, (byte) 0xfb, (byte) 0xa5,
                           (byte) 0x1d, (byte) 0xb9, (byte) 0x2c, (byte) 0x2a };

        final byte[] p = { (byte) 0xde, (byte) 0x9e, (byte) 0xdb, (byte) 0x7d,
                           (byte) 0x7b, (byte) 0x7d, (byte) 0xc1, (byte) 0xb4,
                           (byte) 0xd3, (byte) 0x5b, (byte) 0x61, (byte) 0xc2,
                           (byte) 0xec, (byte) 0xe4, (byte) 0x35, (byte) 0x37,
                           (byte) 0x3f, (byte) 0x83, (byte) 0x43, (byte) 0xc8,
                           (byte) 0x5b, (byte) 0x78, (byte) 0x67, (byte) 0x4d,
                           (byte) 0xad, (byte) 0xfc, (byte) 0x7e, (byte) 0x14,
                           (byte) 0x6f, (byte) 0x88, (byte) 0x2b, (byte) 0x4f };

        final byte[] ref = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d, (byte) 0x5b,
                             (byte) 0xa4, (byte) 0xce, (byte) 0x2d, (byte) 0xe1,
                             (byte) 0x72, (byte) 0x8e, (byte) 0x3b, (byte) 0xf4,
                             (byte) 0x80, (byte) 0x35, (byte) 0x0f, (byte) 0x25,
                             (byte) 0xe0, (byte) 0x7e, (byte) 0x21, (byte) 0xc9,
                             (byte) 0x47, (byte) 0xd1, (byte) 0x9e, (byte) 0x33,
                             (byte) 0x76, (byte) 0xf0, (byte) 0x9b, (byte) 0x3c,
                             (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42 };

        assertTrue("Invalid scalarmult result", Arrays.equals(ref, tweetnacl.cryptoScalarMult(n, p)));
    }

    /**
     * crypto_scalarmult (adapted from tests/scalarmult6.c)
     * 
     */
    public void testCryptoScalarMult6() throws Exception {
        final byte[] n = { (byte) 0x5d, (byte) 0xab, (byte) 0x08, (byte) 0x7e,
                           (byte) 0x62, (byte) 0x4a, (byte) 0x8a, (byte) 0x4b,
                           (byte) 0x79, (byte) 0xe1, (byte) 0x7f, (byte) 0x8b,
                           (byte) 0x83, (byte) 0x80, (byte) 0x0e, (byte) 0xe6,
                           (byte) 0x6f, (byte) 0x3b, (byte) 0xb1, (byte) 0x29,
                           (byte) 0x26, (byte) 0x18, (byte) 0xb6, (byte) 0xfd,
                           (byte) 0x1c, (byte) 0x2f, (byte) 0x8b, (byte) 0x27,
                           (byte) 0xff, (byte) 0x88, (byte) 0xe0, (byte) 0xeb };

        final byte[] p = { (byte) 0x85, (byte) 0x20, (byte) 0xf0, (byte) 0x09,
                           (byte) 0x89, (byte) 0x30, (byte) 0xa7, (byte) 0x54,
                           (byte) 0x74, (byte) 0x8b, (byte) 0x7d, (byte) 0xdc,
                           (byte) 0xb4, (byte) 0x3e, (byte) 0xf7, (byte) 0x5a,
                           (byte) 0x0d, (byte) 0xbf, (byte) 0x3a, (byte) 0x0d,
                           (byte) 0x26, (byte) 0x38, (byte) 0x1a, (byte) 0xf4,
                           (byte) 0xeb, (byte) 0xa4, (byte) 0xa9, (byte) 0x8e,
                           (byte) 0xaa, (byte) 0x9b, (byte) 0x4e, (byte) 0x6a };

        final byte[] ref = { (byte) 0x4a, (byte) 0x5d, (byte) 0x9d, (byte) 0x5b, 
                             (byte) 0xa4, (byte) 0xce, (byte) 0x2d, (byte) 0xe1,
                             (byte) 0x72, (byte) 0x8e, (byte) 0x3b, (byte) 0xf4,
                             (byte) 0x80, (byte) 0x35, (byte) 0x0f, (byte) 0x25,
                             (byte) 0xe0, (byte) 0x7e, (byte) 0x21, (byte) 0xc9,
                             (byte) 0x47, (byte) 0xd1, (byte) 0x9e, (byte) 0x33,
                             (byte) 0x76, (byte) 0xf0, (byte) 0x9b, (byte) 0x3c,
                             (byte) 0x1e, (byte) 0x16, (byte) 0x17, (byte) 0x42 };

        assertTrue("Invalid scalarmult result", Arrays.equals(ref, tweetnacl.cryptoScalarMult(n, p)));
    }
}
