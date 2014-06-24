package za.co.twyst.tweetnacl;

public class TweetNaCl {

    // JNI
    
    // CLASS METHODS
    
    static 
        { System.loadLibrary("tweetnacl");
        }
    
    // NATIVE METHODS
    
    private native long jniCryptoBox    (byte[] ciphertext,byte[] message,   byte[] nonce,byte[] publicKey,byte[] secretKey);
    private native long jniCryptoBoxOpen(byte[] message,   byte[] ciphertext,byte[] nonce,byte[] publicKey,byte[] secretKey);

    // PUBLIC API
    
    /** Releases any resources acquired by the native library.
     * 
     */
    public void release() {
    }
    
    public byte[] cryptoBox(final byte[] message,final byte[] nonce,byte[] publicKey,byte[] secretKey) {
        // ... validate
        
        if (message == null) {
            throw new IllegalArgumentException("Invalid 'message' - may not be null");
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
        
        byte[] ciphertext = new byte[message.length];
        
        jniCryptoBox(ciphertext,message,nonce,publicKey,secretKey);
        
        return ciphertext;
    }
    
    public byte[] cryptoBoxOpen(final byte[] ciphertext,final byte[] nonce,byte[] publicKey,byte[] secretKey) {
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
        
        jniCryptoBoxOpen(message,ciphertext,nonce,publicKey,secretKey);
        
        return message;
    }
}
