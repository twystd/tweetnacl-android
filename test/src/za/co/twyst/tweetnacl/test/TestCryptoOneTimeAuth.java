package za.co.twyst.tweetnacl.test;

import java.util.Arrays;
import java.util.Random;

import za.co.twyst.tweetnacl.TweetNaCl;

public class TestCryptoOneTimeAuth extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

    /**
     * crypto_hash (adapted from tests/onetimeauth.c)
     * 
     */
    public void testCryptoOneTimeAuth() throws Exception {
        final byte[] message = { (byte) 0x8e, (byte) 0x99, (byte) 0x3b, (byte) 0x9f, 
                                 (byte) 0x48, (byte) 0x68, (byte) 0x12, (byte) 0x73,
                                 (byte) 0xc2, (byte) 0x96, (byte) 0x50, (byte) 0xba,
                                 (byte) 0x32, (byte) 0xfc, (byte) 0x76, (byte) 0xce,
                                 (byte) 0x48, (byte) 0x33, (byte) 0x2e, (byte) 0xa7,
                                 (byte) 0x16, (byte) 0x4d, (byte) 0x96, (byte) 0xa4,
                                 (byte) 0x47, (byte) 0x6f, (byte) 0xb8, (byte) 0xc5,
                                 (byte) 0x31, (byte) 0xa1, (byte) 0x18, (byte) 0x6a,
                                 (byte) 0xc0, (byte) 0xdf, (byte) 0xc1, (byte) 0x7c,
                                 (byte) 0x98, (byte) 0xdc, (byte) 0xe8, (byte) 0x7b,
                                 (byte) 0x4d, (byte) 0xa7, (byte) 0xf0, (byte) 0x11,
                                 (byte) 0xec, (byte) 0x48, (byte) 0xc9, (byte) 0x72,
                                 (byte) 0x71, (byte) 0xd2, (byte) 0xc2, (byte) 0x0f,
                                 (byte) 0x9b, (byte) 0x92, (byte) 0x8f, (byte) 0xe2,
                                 (byte) 0x27, (byte) 0x0d, (byte) 0x6f, (byte) 0xb8,
                                 (byte) 0x63, (byte) 0xd5, (byte) 0x17, (byte) 0x38,
                                 (byte) 0xb4, (byte) 0x8e, (byte) 0xee, (byte) 0xe3,
                                 (byte) 0x14, (byte) 0xa7, (byte) 0xcc, (byte) 0x8a,
                                 (byte) 0xb9, (byte) 0x32, (byte) 0x16, (byte) 0x45,
                                 (byte) 0x48, (byte) 0xe5, (byte) 0x26, (byte) 0xae,
                                 (byte) 0x90, (byte) 0x22, (byte) 0x43, (byte) 0x68,
                                 (byte) 0x51, (byte) 0x7a, (byte) 0xcf, (byte) 0xea,
                                 (byte) 0xbd, (byte) 0x6b, (byte) 0xb3, (byte) 0x73,
                                 (byte) 0x2b, (byte) 0xc0, (byte) 0xe9, (byte) 0xda,
                                 (byte) 0x99, (byte) 0x83, (byte) 0x2b, (byte) 0x61,
                                 (byte) 0xca, (byte) 0x01, (byte) 0xb6, (byte) 0xde,
                                 (byte) 0x56, (byte) 0x24, (byte) 0x4a, (byte) 0x9e,
                                 (byte) 0x88, (byte) 0xd5, (byte) 0xf9, (byte) 0xb3,
                                 (byte) 0x79, (byte) 0x73, (byte) 0xf6, (byte) 0x22,
                                 (byte) 0xa4, (byte) 0x3d, (byte) 0x14, (byte) 0xa6,
                                 (byte) 0x59, (byte) 0x9b, (byte) 0x1f, (byte) 0x65,
                                 (byte) 0x4c, (byte) 0xb4, (byte) 0x5a, (byte) 0x74,
                                 (byte) 0xe3, (byte) 0x55, (byte) 0xa5 };

        final byte[] key = { (byte) 0xee, (byte) 0xa6, (byte) 0xa7, (byte) 0x25, 
                             (byte) 0x1c, (byte) 0x1e, (byte) 0x72, (byte) 0x91,
                             (byte) 0x6d, (byte) 0x11, (byte) 0xc2, (byte) 0xcb,
                             (byte) 0x21, (byte) 0x4d, (byte) 0x3c, (byte) 0x25,
                             (byte) 0x25, (byte) 0x39, (byte) 0x12, (byte) 0x1d,
                             (byte) 0x8e, (byte) 0x23, (byte) 0x4e, (byte) 0x65,
                             (byte) 0x2d, (byte) 0x65, (byte) 0x1f, (byte) 0xa4,
                             (byte) 0xc8, (byte) 0xcf, (byte) 0xf8, (byte) 0x80 };

        final byte[] ref = { (byte) 0xf3, (byte) 0xff, (byte) 0xc7, (byte) 0x70, 
                             (byte) 0x3f, (byte) 0x94, (byte) 0x00, (byte) 0xe5,
                             (byte) 0x2a, (byte) 0x7d, (byte) 0xfb, (byte) 0x4b,
                             (byte) 0x3d, (byte) 0x33, (byte) 0x05, (byte) 0xd9 };

        assertTrue("Invalid signature",Arrays.equals(ref,tweetnacl.cryptoOneTimeAuth(message,key)));
    }

    /**
     * crypto_hash (adapted from tests/onetimeauth2.c)
     * 
     */
    public void testCryptoOneTimeAuthVerify() throws Exception {
        final byte[] auth = { (byte) 0xf3, (byte) 0xff, (byte) 0xc7, (byte) 0x70,
                              (byte) 0x3f, (byte) 0x94, (byte) 0x00, (byte) 0xe5,
                              (byte) 0x2a, (byte) 0x7d, (byte) 0xfb, (byte) 0x4b,
                              (byte) 0x3d, (byte) 0x33, (byte) 0x05, (byte) 0xd9 };

        final byte[] message = { (byte) 0x8e, (byte) 0x99, (byte) 0x3b, (byte) 0x9f, 
                                 (byte) 0x48, (byte) 0x68, (byte) 0x12, (byte) 0x73,
                                 (byte) 0xc2, (byte) 0x96, (byte) 0x50, (byte) 0xba,
                                 (byte) 0x32, (byte) 0xfc, (byte) 0x76, (byte) 0xce,
                                 (byte) 0x48, (byte) 0x33, (byte) 0x2e, (byte) 0xa7,
                                 (byte) 0x16, (byte) 0x4d, (byte) 0x96, (byte) 0xa4,
                                 (byte) 0x47, (byte) 0x6f, (byte) 0xb8, (byte) 0xc5,
                                 (byte) 0x31, (byte) 0xa1, (byte) 0x18, (byte) 0x6a,
                                 (byte) 0xc0, (byte) 0xdf, (byte) 0xc1, (byte) 0x7c,
                                 (byte) 0x98, (byte) 0xdc, (byte) 0xe8, (byte) 0x7b,
                                 (byte) 0x4d, (byte) 0xa7, (byte) 0xf0, (byte) 0x11,
                                 (byte) 0xec, (byte) 0x48, (byte) 0xc9, (byte) 0x72,
                                 (byte) 0x71, (byte) 0xd2, (byte) 0xc2, (byte) 0x0f,
                                 (byte) 0x9b, (byte) 0x92, (byte) 0x8f, (byte) 0xe2,
                                 (byte) 0x27, (byte) 0x0d, (byte) 0x6f, (byte) 0xb8,
                                 (byte) 0x63, (byte) 0xd5, (byte) 0x17, (byte) 0x38,
                                 (byte) 0xb4, (byte) 0x8e, (byte) 0xee, (byte) 0xe3, 
                                 (byte) 0x14, (byte) 0xa7, (byte) 0xcc, (byte) 0x8a,
                                 (byte) 0xb9, (byte) 0x32, (byte) 0x16, (byte) 0x45,
                                 (byte) 0x48, (byte) 0xe5, (byte) 0x26, (byte) 0xae,
                                 (byte) 0x90, (byte) 0x22, (byte) 0x43, (byte) 0x68,
                                 (byte) 0x51, (byte) 0x7a, (byte) 0xcf, (byte) 0xea,
                                 (byte) 0xbd, (byte) 0x6b, (byte) 0xb3, (byte) 0x73,
                                 (byte) 0x2b, (byte) 0xc0, (byte) 0xe9, (byte) 0xda,
                                 (byte) 0x99, (byte) 0x83, (byte) 0x2b, (byte) 0x61,
                                 (byte) 0xca, (byte) 0x01, (byte) 0xb6, (byte) 0xde,
                                 (byte) 0x56, (byte) 0x24, (byte) 0x4a, (byte) 0x9e,
                                 (byte) 0x88, (byte) 0xd5, (byte) 0xf9, (byte) 0xb3,
                                 (byte) 0x79, (byte) 0x73, (byte) 0xf6, (byte) 0x22,
                                 (byte) 0xa4, (byte) 0x3d, (byte) 0x14, (byte) 0xa6,
                                 (byte) 0x59, (byte) 0x9b, (byte) 0x1f, (byte) 0x65,
                                 (byte) 0x4c, (byte) 0xb4, (byte) 0x5a, (byte) 0x74,
                                 (byte) 0xe3, (byte) 0x55, (byte) 0xa5 };

        final byte[] key = { (byte) 0xee, (byte) 0xa6, (byte) 0xa7, (byte) 0x25, 
                             (byte) 0x1c, (byte) 0x1e, (byte) 0x72, (byte) 0x91,
                             (byte) 0x6d, (byte) 0x11, (byte) 0xc2, (byte) 0xcb,
                             (byte) 0x21, (byte) 0x4d, (byte) 0x3c, (byte) 0x25,
                             (byte) 0x25, (byte) 0x39, (byte) 0x12, (byte) 0x1d,
                             (byte) 0x8e, (byte) 0x23, (byte) 0x4e, (byte) 0x65,
                             (byte) 0x2d, (byte) 0x65, (byte) 0x1f, (byte) 0xa4,
                             (byte) 0xc8, (byte) 0xcf, (byte) 0xf8, (byte) 0x80 };

        assertTrue("Verify failed", tweetnacl.cryptoOneTimeAuthVerify(auth, message, key));
    }

    /**
     * crypto_hash (adapted from tests/onetimeauth7.c)
     * 
     */
    public void testCryptoOneTimeAuth7() throws Exception {
        Random random = new Random();

        for (int clen = 0; clen < ROUNDS; ++clen) {
            byte[] key     = new byte[TweetNaCl.ONETIMEAUTH_KEYBYTES];
            byte[] message = new byte[clen];
            byte[] auth;

            random.nextBytes(key);
            random.nextBytes(message);

            auth = tweetnacl.cryptoOneTimeAuth(message, key);

            assertNotNull(auth);
            assertTrue(auth.length == TweetNaCl.ONETIMEAUTH_BYTES);

            if (clen > 0) {
                int    mx = random.nextInt(clen);
                int    ax = random.nextInt(TweetNaCl.ONETIMEAUTH_BYTES);
                byte[] m  = message.clone();
                byte[] a  = auth.clone();

                m[mx] = (byte) (message[mx] + 1 + random.nextInt(255));
                a[ax] = (byte) (auth[ax]    + 1 + random.nextInt(255));

                assertFalse("Verified forgery with altered message",tweetnacl.cryptoOneTimeAuthVerify(auth, m, key));
                assertFalse("Verified forgery with altered authentication",tweetnacl.cryptoOneTimeAuthVerify(a, message, key));
                assertFalse("Verified forgery with altered message and authentication",tweetnacl.cryptoOneTimeAuthVerify(a, m, key));
            }
        }
    }
}
