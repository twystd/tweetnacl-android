package za.co.twyst.tweetnacl;

public class TweetNaCl {

    // JNI
    
    // CLASS METHODS
    
    static 
        { System.loadLibrary("tweetnacl");
        }
    
    // NATIVE METHODS
    
    private native long jniCryptoBox       (byte[] ciphertext);
    private native long jniCryptoBoxKeyPair();

    // PUBLIC API
    
    /** Releases any resources acquired by the native library.
     * 
     */
    public void release() {
    }
    
    public byte[] cryptoBox() {
        byte[] ciphertext = new byte[163];
        
        jniCryptoBox(ciphertext);
        
        return ciphertext;
    }
    
    public String crytoBoxKeyPair() {
        long rc = jniCryptoBoxKeyPair();
        
        return null;
    }
}
