/* Public Domain (Unlicense)
 *
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognise copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org>
 */

package za.co.twyst.tweetnacl;

import za.co.twyst.tweetnacl.exceptions.DecryptException;
import za.co.twyst.tweetnacl.exceptions.EncryptException;
import za.co.twyst.tweetnacl.exceptions.VerifyException;

/**
 * Wrapper class for the JNI library functions that wrap the bare TweetNaCl
 * implementation.
 * 
 * @author Tony Seebregts
 * 
 * @see <a href="http://nacl.cr.yp.to">http://nacl.cr.yp.to</a>
 * 
 */
public class TweetNaCl {
    // CONSTANTS

    /**
     * crypto_box_PUBLICKEYBYTES. The number of bytes in a crypto_box public
     * key.
     */
    public static final int BOX_PUBLICKEYBYTES = 32;

    /**
     * crypto_box_SECRETKEYBYTES. The number of bytes in a crypto_box secret
     * key.
     */
    public static final int BOX_SECRETKEYBYTES = 32;

    /**
     * crypto_box_BEFORENMBYTES. The number of bytes in an initialised
     * crypto_box_beforenm byte array.
     */
    public static final int BOX_BEFORENMBYTES = 32;

    /** crypto_box_NONCEBYTES. The number of bytes for a crypto_box nonce. */
    public static final int BOX_NONCEBYTES = 24;

    /**
     * crypto_box_ZEROBYTES. The number of zero padding bytes for a crypto_box
     * message.
     */
    public static final int BOX_ZEROBYTES = 32;

    public static final int HSALSA20_OUTPUTBYTES = 32;
    public static final int HSALSA20_INPUTBYTES = 16;
    public static final int HSALSA20_KEYBYTES = 32;
    public static final int HSALSA20_CONSTBYTES = 16;

    public static final int SALSA20_OUTPUTBYTES = 64;
    public static final int SALSA20_INPUTBYTES = 16;
    public static final int SALSA20_KEYBYTES = 32;
    public static final int SALSA20_CONSTBYTES = 16;

    public static final int HASH_BYTES = 64;
    public static final int HASHBLOCKS_STATEBYTES = 64;
    public static final int HASHBLOCKS_BLOCKBYTES = 128;

    public static final int ONETIMEAUTH_BYTES = 16;
    public static final int ONETIMEAUTH_KEYBYTES = 32;

    public static final int SCALARMULT_BYTES = 32;
    public static final int SCALARMULT_SCALARBYTES = 32;

    public static final int SECRETBOX_KEYBYTES = 32;
    public static final int SECRETBOX_NONCEBYTES = 24;
    public static final int SECRETBOX_ZEROBYTES = 32;
    public static final int SECRETBOX_BOXZEROBYTES = 16;

    public static final int STREAM_KEYBYTES = 32;
    public static final int STREAM_NONCEBYTES = 24;
    public static final int STREAM_SALSA20_KEYBYTES = 32;
    public static final int STREAM_SALSA20_NONCEBYTES = 8;

    public static final int SIGN_BYTES = 64;
    public static final int SIGN_PUBLICKEYBYTES = 32;
    public static final int SIGN_SECRETKEYBYTES = 64;

    public static final int VERIFY16_BYTES = 16;
    public static final int VERIFY32_BYTES = 32;

    // NATIVE METHODS

    private native int jniRandomBytes     (byte[] bytes);
    private native int jniCryptoBoxKeyPair(byte[] publicKey, byte[] secretKey);
    private native int jniCryptoBox       (byte[] ciphertext,byte[] message,   byte[] nonce,byte[] publicKey,byte[] secretKey);
    private native int jniCryptoBoxOpen   (byte[] message,   byte[] ciphertext,byte[] nonce,byte[] publicKey,byte[] secretKey);

    private native int jniCryptoBoxBeforeNM(byte[] key, byte[] publicKey, byte[] secretKey);

    private native int jniCryptoBoxAfterNM(byte[] ciphertext, byte[] message, byte[] nonce, byte[] key);

    private native int jniCryptoBoxOpenAfterNM(byte[] ciphertext, byte[] message, byte[] nonce, byte[] key);

    private native int jniCryptoCoreHSalsa20(byte[] out, byte[] in, byte[] key, byte[] constant);

    private native int jniCryptoCoreSalsa20(byte[] out, byte[] in, byte[] key, byte[] constant);

    private native int jniCryptoHash(byte[] hash, byte[] message);

    private native int jniCryptoHashBlocks(byte[] state, byte[] message);

    private native int jniCryptoOneTimeAuth(byte[] signature, byte[] message, byte[] key);

    private native int jniCryptoOneTimeAuthVerify(byte[] signature, byte[] message, byte[] key);

    private native int jniCryptoScalarMultBase(byte[] q, byte[] n);

    private native int jniCryptoScalarMult(byte[] q, byte[] n, byte[] p);

    private native int jniCryptoSecretBox(byte[] ciphertext, byte[] plaintext, byte[] nonce, byte[] key);

    private native int jniCryptoSecretBoxOpen(byte[] plaintext, byte[] ciphertext, byte[] nonce, byte[] key);

    private native int jniCryptoStream(byte[] ciphertext, byte[] nonce, byte[] key);

    private native int jniCryptoStreamXor(byte[] ciphertext, byte[] plaintext, byte[] nonce, byte[] key);

    private native int jniCryptoStreamSalsa20(byte[] ciphertext, byte[] nonce, byte[] key);

    private native int jniCryptoStreamSalsa20Xor(byte[] ciphertext, byte[] plaintext, byte[] nonce, byte[] key);

    private native int jniCryptoSignKeyPair(byte[] publicKey, byte[] secretKey);

    private native int jniCryptoSign(byte[] signed, byte[] message, byte[] secretKey);

    private native int jniCryptoSignOpen(byte[] message, byte[] signed, byte[] publicKey);

    private native int jniCryptoVerify16(byte[] x, byte[] y);

    private native int jniCryptoVerify32(byte[] x, byte[] y);

    // CLASS METHODS

    /**
     * Loads the TweetNaCl JNI library.
     * 
     */
    static {
        System.loadLibrary("tweetnacl");
    }

    // PUBLIC API

    /**
     * Releases any resources acquired by the native library.
     * <p>
     * The current implementation does not acquire 'permanent' resources so
     * invoking release when finished with the library is optional, but
     * recommended.
     * 
     */
    public void release() {
    }

    /**
     * Wrapper function for <code>randombytes<code>.
     * <p>
     * The <code>randombytes</code> function is not part of the TweetNaCl API
     * but is exposed here for test purposes.
     * <p>
     * 
     * @param bytes
     *            Byte array to fill with random bytes.
     * 
     * @throws IllegalArgumentException
     *             Thrown if <code>bytes</code> is <code>null</code>.
     */
    public void randomBytes(final byte[] bytes) {
        // ... validate

        if (bytes == null) {
            throw new IllegalArgumentException("Invalid 'bytes' - may not be null");
        }

        // ... fill with random bytes

        jniRandomBytes(bytes);
    }

    /**
     * Wrapper function for crypto_box_keypair.
     * <p>
     * Randomly generates a secret key and a corresponding public key. It
     * guarantees that the secret key has BOX_PUBLICKEYBYTES bytes and that the
     * public key has BOX_SECRETKEYBYTES bytes
     * 
     * @return KeyPair initialised with a crypto_box public/private key pair.
     * 
     * @see <a
     *      href="http://nacl.cr.yp.to/box.html">http://nacl.cr.yp.to/box.html</a>
     */
    public KeyPair cryptoBoxKeyPair() {
        byte[] publicKey = new byte[BOX_PUBLICKEYBYTES];
        byte[] secretKey = new byte[BOX_SECRETKEYBYTES];

        jniCryptoBoxKeyPair(publicKey, secretKey);

        return new KeyPair(publicKey, secretKey);
    }

    /**
     * Wrapper function for crypto_box.
     * 
     * @param message
     *            Byte array containing the message to be encrypted. May not be
     *            <code>null</code>.
     * @param nonce
     *            BOX_NONCEBYTES byte array containing the unique nonce to use
     *            when encrypting the message.
     * @param publicKey
     *            BOX_PUBLICKEYBYTES byte array containing the public key of the
     *            recipient.
     * @param secretKey
     *            BOX_SECRETKEYBYTES byte array containing the secret key of the
     *            sender.
     * 
     * @return Byte array with the encrypted message.
     * 
     * @throws EncryptException
     *             Thrown if crypto_box returns anything other than 0.
     * 
     * @see <a
     *      href="http://nacl.cr.yp.to/box.html">http://nacl.cr.yp.to/box.html</a>
     */
    public byte[] cryptoBox(final byte[] message, final byte[] nonce, byte[] publicKey, byte[] secretKey)
            throws EncryptException {
        // ... validate

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message' - may not be null");
        }

        if ((nonce == null) || (nonce.length != BOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be BOX_NONCEBYTES bytes");
        }

        if ((publicKey == null) || (publicKey.length != BOX_PUBLICKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be BOX_PUBLICKEYBYTES bytes");
        }

        if ((secretKey == null) || (secretKey.length != BOX_SECRETKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be BOX_SECRETKEYBYTES bytes");
        }

        // ... encrypt

        byte[] ciphertext = new byte[message.length];
        int    rc;

        if ((rc = jniCryptoBox(ciphertext, message, nonce, publicKey, secretKey)) != 0) {
            throw new EncryptException("Error encrypting message [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_box_open.
     * 
     * @param ciphertext
     *            Byte array containing the ciphertext to be decrypted
     * @param nonce
     *            BOX_NONCEBYTES byte array containing the unique nonce to use
     *            when encrypting the message.
     * @param publicKey
     *            BOX_PUBLICKEYBYTES byte array containing the public key of the
     *            recipient.
     * @param secretKey
     *            BOX_SECRETKEYBYTES byte array containing the secret key of the
     *            sender.
     * 
     * @return Byte array with the decrypted message.
     * 
     * @throws DecryptException
     *             Thrown if crypto_box_open returns anything other than 0.
     * 
     * @see <a
     *      href="http://nacl.cr.yp.to/box.html">http://nacl.cr.yp.to/box.html</a>
     */
    public byte[] cryptoBoxOpen(final byte[] ciphertext, final byte[] nonce, byte[] publicKey, byte[] secretKey)
            throws DecryptException {
        // ... validate

        if (ciphertext == null) {
            throw new IllegalArgumentException("Invalid 'ciphertext' - may not be null");
        }

        if ((nonce == null) || (nonce.length != BOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be 24 bytes");
        }

        if ((publicKey == null) || (publicKey.length != BOX_PUBLICKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be 32 bytes");
        }

        if ((secretKey == null) || (secretKey.length != BOX_SECRETKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be 32 bytes");
        }

        // ... decrypt

        byte[] message = new byte[ciphertext.length];
        int rc;

        if ((rc = jniCryptoBoxOpen(message, ciphertext, nonce, publicKey, secretKey)) != 0) {
            throw new DecryptException("Error decrypting message [" + Integer.toString(rc) + "]");
        }

        return message;
    }
    
    /**
     * Wrapper function for crypto_box_beforenm.
     * 
     * @param publicKey
     *            BOX_PUBLICKEYBYTES byte array containing the public key of the
     *            recipient.
     * @param secretKey
     *            BOX_SECRETKEYBYTES byte array containing the secret key of the
     *            sender.
     * 
     * @return BOX_BEFORENMBYTES byte array initialised for use with
     *         crypto_box_afternm.
     * 
     * @throws Exception
     *             Thrown if crypto_box_beforenm returns anything other than 0.
     * 
     * @see <a
     *      href="http://nacl.cr.yp.to/box.html">http://nacl.cr.yp.to/box.html</a>
     */
    public byte[] cryptoBoxBeforeNM(byte[] publicKey, byte[] secretKey) throws Exception {
        // ... validate

        if ((publicKey == null) || (publicKey.length != BOX_PUBLICKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be 32 bytes");
        }

        if ((secretKey == null) || (secretKey.length != BOX_SECRETKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be 32 bytes");
        }

        // ... encrypt

        byte[] key = new byte[BOX_BEFORENMBYTES];
        int rc;

        if ((rc = jniCryptoBoxBeforeNM(key, publicKey, secretKey)) != 0) {
            throw new Exception("Error generating message key [" + Integer.toString(rc) + "]");
        }

        return key;
    }

    /**
     * Wrapper function for crypto_box_afternm.
     * 
     * @param message
     *            Byte array containing the message to be encrypted. May not be
     *            <code>null</code>.
     * @param nonce
     *            BOX_NONCEBYTES byte array containing the unique nonce to use
     *            when encrypting the message.
     * @param key
     *            BOX_BEFORENMBYTES byte array containing the byte array
     *            initialised by crypto_box_beforenm.
     * 
     * @return Byte array with the encrypted message.
     * 
     * @throws Exception
     *             Thrown if crypto_box_afternm returns anything other than 0.
     * 
     * @see <a
     *      href="http://nacl.cr.yp.to/box.html">http://nacl.cr.yp.to/box.html</a>
     */
    public byte[] cryptoBoxAfterNM(final byte[] message, final byte[] nonce, byte[] key) throws EncryptException {
        // ... validate

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message' - may not be null");
        }

        if ((nonce == null) || (nonce.length != BOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be " + Integer.toString(BOX_NONCEBYTES)
                    + " bytes");
        }

        if ((key == null) || (key.length != BOX_BEFORENMBYTES)) {
            throw new IllegalArgumentException("Invalid 'message key' - must be " + Integer.toString(BOX_BEFORENMBYTES)
                    + " bytes");
        }

        // ... encrypt

        byte[] ciphertext = new byte[message.length];
        int rc;

        if ((rc = jniCryptoBoxAfterNM(ciphertext, message, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting message [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_box_open_afternm.
     * 
     * @param ciphertext
     * @param nonce
     * @param key
     * 
     * @return plaintext
     * 
     * @throws Exception
     */
    public byte[] cryptoBoxOpenAfterNM(final byte[] ciphertext, final byte[] nonce, byte[] key) throws DecryptException {
        // ... validate

        if (ciphertext == null) {
            throw new IllegalArgumentException("Invalid 'ciphertext' - may not be null");
        }

        if ((nonce == null) || (nonce.length != BOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be " + Integer.toString(BOX_NONCEBYTES)
                    + " bytes");
        }

        if ((key == null) || (key.length != BOX_BEFORENMBYTES)) {
            throw new IllegalArgumentException("Invalid 'message key' - must be " + Integer.toString(BOX_BEFORENMBYTES)
                    + " bytes");
        }

        // ... encrypt

        byte[] message = new byte[ciphertext.length];
        int rc;

        if ((rc = jniCryptoBoxOpenAfterNM(message, ciphertext, nonce, key)) != 0) {
            throw new DecryptException("Error decrypting message [" + Integer.toString(rc) + "]");
        }

        return message;
    }

    /**
     * Wrapper function for crypto_core_hsalsa20.
     * 
     * @param in
     * @param key
     * @param constant
     * 
     * @return out
     * 
     * @throws Exception
     */
    public byte[] cryptoCoreHSalsa20(final byte[] in, final byte[] key, byte[] constant) throws EncryptException {
        // ... validate

        if ((in == null) || (in.length != HSALSA20_INPUTBYTES)) {
            throw new IllegalArgumentException("Invalid 'in' - must be " + Integer.toString(HSALSA20_INPUTBYTES)
                    + " bytes");
        }

        if ((key == null) || (key.length != HSALSA20_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - must be " + Integer.toString(HSALSA20_KEYBYTES)
                    + " bytes");
        }

        if ((constant == null) || (constant.length != HSALSA20_CONSTBYTES)) {
            throw new IllegalArgumentException("Invalid 'constant' - must be " + Integer.toString(HSALSA20_CONSTBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] out = new byte[HSALSA20_OUTPUTBYTES];
        int rc;

        if ((rc = jniCryptoCoreHSalsa20(out, in, key, constant)) != 0) {
            throw new EncryptException("Error encrypting message [" + Integer.toString(rc) + "]");
        }

        return out;
    }

    /**
     * Wrapper function for crypto_core_salsa20.
     * 
     * @param in
     * @param key
     * @param constant
     * 
     * @return out
     * 
     * @throws Exception
     */
    public byte[] cryptoCoreSalsa20(final byte[] in, final byte[] key, byte[] constant) throws EncryptException {
        // ... validate

        if ((in == null) || (in.length != SALSA20_INPUTBYTES)) {
            throw new IllegalArgumentException("Invalid 'in' - must be " + Integer.toString(SALSA20_INPUTBYTES)
                    + " bytes");
        }

        if ((key == null) || (key.length != SALSA20_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - must be " + Integer.toString(SALSA20_KEYBYTES)
                    + " bytes");
        }

        if ((constant == null) || (constant.length != SALSA20_CONSTBYTES)) {
            throw new IllegalArgumentException("Invalid 'constant' - must be " + Integer.toString(SALSA20_CONSTBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] out = new byte[SALSA20_OUTPUTBYTES];
        int rc;

        if ((rc = jniCryptoCoreSalsa20(out, in, key, constant)) != 0) {
            throw new EncryptException("Error encrypting message [" + Integer.toString(rc) + "]");
        }

        return out;
    }

    /**
     * Wrapper function for crypto_hash.
     * 
     * @param message
     * @return hash
     * 
     * @throws Exception
     */
    public byte[] cryptoHash(final byte[] message) throws EncryptException {
        // ... validate

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message'");
        }

        // ... invoke

        byte[] hash = new byte[HASH_BYTES];
        int rc;

        if ((rc = jniCryptoHash(hash, message)) != 0) {
            throw new EncryptException("Error calculating message hash [" + Integer.toString(rc) + "]");
        }

        return hash;
    }

    /**
     * Wrapper function for crypto_hashblocks.
     * 
     * @param message
     * @return hash
     * 
     * @throws Exception
     */
    public byte[] cryptoHashBlocks(final byte[] x, final byte[] m) throws EncryptException {
        // ... validate

        if ((x == null) || (x.length != HASHBLOCKS_STATEBYTES)) {
            throw new IllegalArgumentException("Invalid 'x' - must be " + Integer.toString(HASHBLOCKS_BLOCKBYTES)
                    + " bytes");
        }

        if ((m == null) || ((m.length % HASHBLOCKS_BLOCKBYTES) != 0)) {
            throw new IllegalArgumentException("Invalid 'm' - must be a multiple of "
                    + Integer.toString(HASHBLOCKS_BLOCKBYTES) + " bytes");
        }

        // ... invoke

        byte[] h = x.clone();

        jniCryptoHashBlocks(h, m);

        return h;
    }

    /**
     * Wrapper function for crypto_onetimeauth.
     * 
     * @param message
     * @param key
     * 
     * @return hash
     * 
     * @throws Exception
     */
    public byte[] cryptoOneTimeAuth(final byte[] message, final byte[] key) throws EncryptException {
        // ... validate

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message'");
        }

        if ((key == null) || (key.length != ONETIMEAUTH_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be "
                    + Integer.toString(ONETIMEAUTH_KEYBYTES) + " bytes");
        }

        // ... invoke

        byte[] authorisation = new byte[ONETIMEAUTH_BYTES];

        jniCryptoOneTimeAuth(authorisation, message, key);

        return authorisation;
    }

    /**
     * Wrapper function for crypto_onetimeauth_verify.
     * 
     * @param authorisation
     * @param message
     * @param key
     * @return verified
     * 
     * @throws Exception
     */
    public boolean cryptoOneTimeAuthVerify(final byte[] authorisation, final byte[] message, final byte[] key)
            throws EncryptException {
        // ... validate

        if ((authorisation == null) || (authorisation.length != ONETIMEAUTH_BYTES)) {
            throw new IllegalArgumentException("Invalid 'authorisation' - length must be "
                    + Integer.toString(ONETIMEAUTH_KEYBYTES) + " bytes");
        }

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message'");
        }

        if ((key == null) || (key.length != ONETIMEAUTH_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be "
                    + Integer.toString(ONETIMEAUTH_KEYBYTES) + " bytes");
        }

        // ... invoke

        return jniCryptoOneTimeAuthVerify(authorisation, message, key) == 0;
    }

    /**
     * Wrapper function for crypto_scalarmult_base.
     * 
     * @param n
     * @return q
     * 
     * @throws Exception
     */
    public byte[] cryptoScalarMultBase(final byte[] n) throws EncryptException {
        // ... validate

        if ((n == null) || (n.length != SCALARMULT_SCALARBYTES)) {
            throw new IllegalArgumentException("Invalid 'n' - length must be "
                    + Integer.toString(SCALARMULT_SCALARBYTES) + " bytes");
        }

        // ... invoke

        byte[] q = new byte[SCALARMULT_BYTES];
        int rc;

        if ((rc = jniCryptoScalarMultBase(q, n)) != 0) {
            throw new EncryptException("Error calculating scalarmult_base [" + Integer.toString(rc) + "]");
        }

        return q;
    }

    /**
     * Wrapper function for crypto_scalarmult.
     * 
     * @param n
     * @param p
     * @return q
     * 
     * @throws Exception
     */
    public byte[] cryptoScalarMult(final byte[] n, final byte[] p) throws EncryptException {
        // ... validate

        if ((n == null) || (n.length != SCALARMULT_SCALARBYTES)) {
            throw new IllegalArgumentException("Invalid 'n' - length must be "
                    + Integer.toString(SCALARMULT_SCALARBYTES) + " bytes");
        }

        if ((p == null) || (p.length != SCALARMULT_BYTES)) {
            throw new IllegalArgumentException("Invalid 'p' - length must be " + Integer.toString(SCALARMULT_BYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] q = new byte[SCALARMULT_BYTES];
        int rc;

        if ((rc = jniCryptoScalarMult(q, n, p)) != 0) {
            throw new EncryptException("Error calculating scalarmult [" + Integer.toString(rc) + "]");
        }

        return q;
    }

    /**
     * Wrapper function for crypto_secretbox.
     * 
     * @param plaintext
     * @param key
     * @param nonce
     * @return ciphertext
     * 
     * @throws Exception
     */
    public byte[] cryptoSecretBox(final byte[] plaintext, final byte[] nonce, final byte[] key) throws EncryptException {
        // ... validate

        if (plaintext == null) {
            throw new IllegalArgumentException("Invalid 'plaintext'");
        }

        if ((nonce == null) || (nonce.length != SECRETBOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(SECRETBOX_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != SECRETBOX_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be " + Integer.toString(SECRETBOX_KEYBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] ciphertext = new byte[plaintext.length];
        int rc;

        if ((rc = jniCryptoSecretBox(ciphertext, plaintext, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting message [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_secretbox_open.
     * 
     * @param ciphertext
     * @param key
     * @param nonce
     * @return plaintext
     * 
     * @throws Exception
     */
    public byte[] cryptoSecretBoxOpen(final byte[] crypttext, final byte[] nonce, final byte[] key)
            throws DecryptException {
        // ... validate

        if (crypttext == null) {
            throw new IllegalArgumentException("Invalid 'crypttext'");
        }

        if ((nonce == null) || (nonce.length != SECRETBOX_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(SECRETBOX_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != SECRETBOX_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be " + Integer.toString(SECRETBOX_KEYBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] plaintext = new byte[crypttext.length];
        int rc;

        if ((rc = jniCryptoSecretBoxOpen(plaintext, crypttext, nonce, key)) != 0) {
            throw new DecryptException("Error decrypting message [" + Integer.toString(rc) + "]");
        }

        return plaintext;
    }

    /**
     * Wrapper function for crypto_stream.
     * 
     * @param length
     * @param nonce
     * @param key
     * @return ciphertext
     * 
     * @throws Exception
     */
    public byte[] cryptoStream(final int length, final byte[] nonce, final byte[] key) throws EncryptException {
        // ... validate

        if (length < 0) {
            throw new IllegalArgumentException("Invalid 'length' - may not be negative");
        }

        if ((nonce == null) || (nonce.length != STREAM_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(STREAM_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != STREAM_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be " + Integer.toString(STREAM_KEYBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] ciphertext = new byte[length];
        int rc;

        if ((rc = jniCryptoStream(ciphertext, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting plaintext [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_stream_xor.
     * 
     * @param plaintext
     * @param nonce
     * @param key
     * 
     * @return ciphertext
     * 
     * @throws Exception
     */
    public byte[] cryptoStreamXor(final byte[] plaintext, final byte[] nonce, final byte[] key) throws EncryptException {
        // ... validate

        if (plaintext == null) {
            throw new IllegalArgumentException("Invalid 'crypttext'");
        }

        if ((nonce == null) || (nonce.length != STREAM_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(STREAM_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != STREAM_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be " + Integer.toString(STREAM_KEYBYTES)
                    + " bytes");
        }

        // ... invoke

        byte[] ciphertext = new byte[plaintext.length];
        int rc;

        if ((rc = jniCryptoStreamXor(ciphertext, plaintext, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting plaintext [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_stream_salsa20.
     * 
     * @param length
     * @param nonce
     * @param key
     * @return ciphertext
     * 
     * @throws Exception
     */
    public byte[] cryptoStreamSalsa20(final int length, final byte[] nonce, final byte[] key) throws EncryptException {
        // ... validate

        if (length < 0) {
            throw new IllegalArgumentException("Invalid 'length' - may not be negative");
        }

        if ((nonce == null) || (nonce.length != STREAM_SALSA20_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(STREAM_SALSA20_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != STREAM_SALSA20_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be "
                    + Integer.toString(STREAM_SALSA20_KEYBYTES) + " bytes");
        }

        // ... invoke

        byte[] ciphertext = new byte[length];
        int rc;

        if ((rc = jniCryptoStreamSalsa20(ciphertext, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting plaintext [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_stream_salsa20_xor.
     * 
     * @param plaintext
     * @param nonce
     * @param key
     * 
     * @return ciphertext
     * 
     * @throws Exception
     */
    public byte[] cryptoStreamSalsa20Xor(final byte[] plaintext, final byte[] nonce, final byte[] key)
            throws EncryptException {
        // ... validate

        if (plaintext == null) {
            throw new IllegalArgumentException("Invalid 'crypttext'");
        }

        if ((nonce == null) || (nonce.length != STREAM_SALSA20_NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - length must be "
                    + Integer.toString(STREAM_SALSA20_NONCEBYTES) + " bytes");
        }

        if ((key == null) || (key.length != STREAM_SALSA20_KEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'key' - length must be "
                    + Integer.toString(STREAM_SALSA20_KEYBYTES) + " bytes");
        }

        // ... invoke

        byte[] ciphertext = new byte[plaintext.length];
        int rc;

        if ((rc = jniCryptoStreamSalsa20Xor(ciphertext, plaintext, nonce, key)) != 0) {
            throw new EncryptException("Error encrypting plaintext [" + Integer.toString(rc) + "]");
        }

        return ciphertext;
    }

    /**
     * Wrapper function for crypto_sign_keypair.
     * 
     * @return keypair
     * 
     * @throws Exception
     */
    public KeyPair cryptoSignKeyPair() throws Exception {
        byte[] publicKey = new byte[SIGN_PUBLICKEYBYTES];
        byte[] secretKey = new byte[SIGN_SECRETKEYBYTES];
        int rc;

        if ((rc = jniCryptoSignKeyPair(publicKey, secretKey)) != 0) {
            throw new Exception("Error generating signing keypair [" + Integer.toString(rc) + "]");
        }

        return new KeyPair(publicKey, secretKey);
    }

    /**
     * Wrapper function for crypto_sign.
     * 
     * @param message
     * @param secretKey
     * 
     * @return signed
     * 
     * @throws Exception
     */
    public byte[] cryptoSign(final byte[] message, byte[] secretKey) throws Exception {
        // ... validate

        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message' - may not be null");
        }

        if ((secretKey == null) || (secretKey.length != SIGN_SECRETKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be " + SIGN_SECRETKEYBYTES + " bytes");
        }

        // ... sign

        byte[] signed = new byte[message.length + SIGN_BYTES];
        int rc;

        if ((rc = jniCryptoSign(signed, message, secretKey)) != 0) {
            throw new EncryptException("Error signing message [" + Integer.toString(rc) + "]");
        }

        return signed;
    }

    /**
     * Wrapper function for crypto_sign_open.
     * 
     * @param signed
     * @param publicKey
     * 
     * @return message
     * 
     * @throws VerifyException
     */
    public byte[] cryptoSignOpen(final byte[] signed, byte[] publicKey) throws VerifyException {
        // ... validate

        if (signed == null) {
            throw new IllegalArgumentException("Invalid 'signed message' - may not be null");
        }

        if ((publicKey == null) || (publicKey.length != SIGN_PUBLICKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be " + SIGN_PUBLICKEYBYTES + " bytes");
        }

        // ... sign

        byte[] message = new byte[signed.length - SIGN_BYTES];
        int rc;

        if ((rc = jniCryptoSignOpen(message, signed, publicKey)) < 0) {
            throw new VerifyException("Error verifying message signature[" + Integer.toString(rc) + "]");
        }

        return message;
    }

    /**
     * Wrapper function for crypto_verify_16.
     * 
     * @param x
     * @param y
     * 
     * @return boolean
     * 
     * @throws VerifyException
     */
    public boolean cryptoVerify16(final byte[] x, byte[] y) throws VerifyException {
        // ... validate

        if ((x == null) || (x.length != VERIFY16_BYTES)) {
            throw new IllegalArgumentException("Invalid 'x' - must be " + VERIFY16_BYTES + " bytes");
        }

        if ((y == null) || (y.length != VERIFY16_BYTES)) {
            throw new IllegalArgumentException("Invalid 'y' - must be " + VERIFY16_BYTES + " bytes");
        }

        // ... verify

        switch (jniCryptoVerify16(x, y)) {
        case 0:
            return true;

        case -1:
            return false;

        default:
            throw new VerifyException("Invalid result from crypto_verify_16");
        }
    }

    /**
     * Wrapper function for crypto_verify_32.
     * 
     * @param x
     * @param y
     * 
     * @return boolean
     * 
     * @throws VerifyException
     */
    public boolean cryptoVerify32(final byte[] x, byte[] y) throws VerifyException {
        // ... validate

        if ((x == null) || (x.length != VERIFY32_BYTES)) {
            throw new IllegalArgumentException("Invalid 'x' - must be " + VERIFY32_BYTES + " bytes");
        }

        if ((y == null) || (y.length != VERIFY32_BYTES)) {
            throw new IllegalArgumentException("Invalid 'y' - must be " + VERIFY32_BYTES + " bytes");
        }

        // ... verify

        switch (jniCryptoVerify32(x, y)) {
        case 0:
            return true;

        case -1:
            return false;

        default:
            throw new VerifyException("Invalid result from crypto_verify_32");
        }
    }

    // INNER CLASSES

    public static final class KeyPair {
        public final byte[] publicKey;
        public final byte[] secretKey;

        private KeyPair(byte[] publicKey, byte[] secretKey) {
            this.publicKey = publicKey.clone();
            this.secretKey = secretKey.clone();
        }
    }
}
