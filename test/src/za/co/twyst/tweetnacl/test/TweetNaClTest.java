package za.co.twyst.tweetnacl.test;

import java.util.Random;

import android.test.AndroidTestCase;

import za.co.twyst.tweetnacl.TweetNaCl;

public abstract class TweetNaClTest extends AndroidTestCase {
    // CONSTANTS

    protected static final String TAG    = "TweetNaCl";
    protected static final int    ROUNDS = 100; // TODO change to 10000 when done
    private static final char[]   HEX    = { '0', '1', '2', '3', '4', '5', '6', '7',
                                             '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' 
                                           };

    // TEST VARIABLES

    protected TweetNaCl tweetnacl;
    protected Random    random;

    // SETUP/TEARDOWN

    @Override
    protected void setUp() throws Exception {
        tweetnacl = new TweetNaCl();
        random    = new Random();
    }

    @Override
    protected void tearDown() throws Exception {
        tweetnacl.release();
    }

    // UTILITY FUNCTIONS

    /**
     * Converts a byte array to a hexadecimal string.
     * 
     */
    protected static String tohex(byte[] bytes) {
        char[] string = new char[bytes.length * 2];
        int ix = 0;

        for (byte x : bytes) {
            string[ix++] = HEX[(x >> 4) & 0x0f];
            string[ix++] = HEX[(x >> 0) & 0x0f];
        }

        return new String(string);
    }

    /**
     * Converts a hexadecimal string to a byte array. This function
     * intentionally crashes with an ArrayIndexOutOfRange if the string length
     * isn't a multiple of 2.
     * <p>
     * And yes, using a Map lookup isn't efficient.
     */
    protected static byte[] fromhex(String string) {
        char[] chars = string.toCharArray();
        byte[] bytes = new byte[chars.length / 2];
        int i = 0;
        int ix = 0;

        while (i < chars.length) {
            int msb = Character.digit(chars[i++], 16);
            int lsb = Character.digit(chars[i++], 16);

            bytes[ix++] = (byte) (((msb << 4) & 0x00f0) | (lsb & 0x000f));
        }

        return bytes;
    }
}
