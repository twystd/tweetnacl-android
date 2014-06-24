package za.co.twyst.tweetnacl;

public class TweetNaCl {
    // CONSTANTS

    public static final int PUBLICKEYBYTES = 32;
    public static final int SECRETKEYBYTES = 32;
    public static final int BEFORENMBYTES  = 32;
    public static final int NONCEBYTES     = 24;
    public static final int ZEROBYTES      = 32;
    public static final int BOXZEROBYTES   = 16;

    // NATIVE METHODS
    
    private native int jniCryptoBox       (byte[] ciphertext,byte[] message,   byte[] nonce,byte[] publicKey,byte[] secretKey);
    private native int jniCryptoBoxOpen   (byte[] message,   byte[] ciphertext,byte[] nonce,byte[] publicKey,byte[] secretKey);
    private native int jniCryptoBoxKeyPair(byte[] publicKey,byte[] secretKey);

    // CLASS METHODS
    
    static 
        { System.loadLibrary("tweetnacl");
        }
    
    // PUBLIC API
    
    /** Releases any resources acquired by the native library.
     * 
     */
    public void release() {
    }
    
    public byte[] cryptoBox(final byte[] message,final byte[] nonce,byte[] publicKey,byte[] secretKey) throws Exception {
        // ... validate
        
        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message' - may not be null");
        }
        
        if ((nonce == null) || (nonce.length != NONCEBYTES)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be 24 bytes");
        }

        if ((publicKey == null) || (publicKey.length != PUBLICKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be 32 bytes");
        }

        if ((secretKey == null) || (secretKey.length != SECRETKEYBYTES)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be 32 bytes");
        }
        
        // ... encrypt
        
        byte[] ciphertext = new byte[message.length];
        int    rc;

        if ((rc = jniCryptoBox(ciphertext,message,nonce,publicKey,secretKey)) != 0) {
            throw new Exception("Error encrypting message [" + Integer.toString(rc) + "]");
        }
        
        return ciphertext;
    }    
    
    public byte[] cryptoBoxOpen(final byte[] ciphertext,final byte[] nonce,byte[] publicKey,byte[] secretKey) throws Exception {
        // ... validate
        
        if (ciphertext == null) {
            throw new IllegalArgumentException("Invalid 'ciphertext' - may not be null");
        }
        
        if ((nonce == null) || (nonce.length != 24)) {
            throw new IllegalArgumentException("Invalid 'nonce' - must be 24 bytes");
        }

        if ((publicKey == null) || (publicKey.length != 32)) {
            throw new IllegalArgumentException("Invalid 'public key' - must be 32 bytes");
        }

        if ((secretKey == null) || (secretKey.length != 32)) {
            throw new IllegalArgumentException("Invalid 'secret key' - must be 32 bytes");
        }
        
        // ... encrypt
        
        byte[] message = new byte[ciphertext.length];
        int    rc;
        
        if ((rc = jniCryptoBoxOpen(message,ciphertext,nonce,publicKey,secretKey)) != 0) {
            throw new Exception("Error decrypting message [" + Integer.toString(rc) + "]");
        }
        
        return message;
    }
    
    public KeyPair cryptoBoxKeyPair() {
        // ... validate
        
        // ... get key pair
        
        byte[] publicKey = new byte[PUBLICKEYBYTES];
        byte[] secretKey = new byte[SECRETKEYBYTES];
        
        jniCryptoBoxKeyPair(publicKey,secretKey);
        
        return new KeyPair(publicKey,secretKey);
    }

    // INNER CLASSES
    
    public static final class KeyPair {
        public final byte[] publicKey = new byte[PUBLICKEYBYTES];
        public final byte[] secretKey = new byte[SECRETKEYBYTES];
        
        private KeyPair(byte[] publicKey,byte[] secretKey) {
            System.arraycopy(publicKey,0,this.publicKey,0,PUBLICKEYBYTES);
            System.arraycopy(secretKey,0,this.secretKey,0,SECRETKEYBYTES);
        }
    }
}
