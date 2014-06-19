package za.co.twyst.tweetnacl;

public class TweetNaCl {

    // JNI
    
    // CLASS METHODS
    
    static 
        { System.loadLibrary("tweetnacl");
        }
    
    // NATIVE METHODS
    
    private native long jniCryptoBoxKeyPair();

    // PUBLIC API
    
    public String crytoBoxKeyPair() {
        long rc = jniCryptoBoxKeyPair();
        
        return null;
    }
}
