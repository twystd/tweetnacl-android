package za.co.twyst.tweetnacl.test;

public class TestCryptoX extends TweetNaClTest {
    // CONSTANTS

    // UNIT TESTS

//    public void testX() throws Exception {
//        byte[] key     = tweetnacl.cryptoBoxBeforeNM(ALICEPK, BOBSK);
//        byte[] message = tweetnacl.cryptoBoxOpenAfterNMX(CIPHERTEXT, NONCE, key);
//
//        assertTrue("OOOPS",Arrays.equals(MESSAGE,message));
//    }
//
//    public void testY() throws Exception {
//        for (int i=0; i<1024; i++) {
//            KeyPair alice    = tweetnacl.cryptoBoxKeyPair();
//            KeyPair bob      = tweetnacl.cryptoBoxKeyPair();
//            byte[]  key      = tweetnacl.cryptoBoxBeforeNM(bob.publicKey,alice.secretKey);
//            byte[]  nonce    = new byte[TweetNaCl.BOX_NONCEBYTES];
//            byte[]  message  = new byte[random.nextInt(16384)];
//
//            random.nextBytes(nonce);
//            random.nextBytes(message);
//            
//            byte[] ciphertext = tweetnacl.cryptoBoxAfterNM (message,nonce, key);
//            byte[] ciphertextz = new byte[ciphertext.length + TweetNaCl.BOX_BOXZEROBYTES];
//            
//            Arrays.fill(ciphertextz,(byte) 0x00);
//            System.arraycopy(ciphertext,0,ciphertextz,TweetNaCl.BOX_BOXZEROBYTES,ciphertext.length);
//            
//            byte[] p = tweetnacl.cryptoBoxOpenAfterNM (ciphertextz,nonce, key);
//            byte[] q = tweetnacl.cryptoBoxOpenAfterNMX(ciphertext, nonce, key);
//
//            assertTrue("OOOOPS !",Arrays.equals(Arrays.copyOfRange(p,TweetNaCl.BOX_ZEROBYTES,p.length),q));
//        }
//    }
//
//    public void testZ() throws Exception {
//        long DT  = 0;
//        long DT2 = 0;
//        
//        for (int i=0; i<32; i++) {
//            KeyPair alice    = tweetnacl.cryptoBoxKeyPair();
//            KeyPair bob      = tweetnacl.cryptoBoxKeyPair();
//            byte[]  key      = tweetnacl.cryptoBoxBeforeNM(bob.publicKey,alice.secretKey);
//            byte[]  nonce    = new byte[TweetNaCl.BOX_NONCEBYTES];
//            byte[]  message  = new byte[random.nextInt(16384)];
//
//            random.nextBytes(nonce);
//            random.nextBytes(message);
//            
//            byte[] ciphertext = tweetnacl.cryptoBoxAfterNM (message,nonce, key);
//            byte[] ciphertextz = new byte[ciphertext.length + TweetNaCl.BOX_BOXZEROBYTES];
//            
//            Arrays.fill(ciphertextz,(byte) 0x00);
//            System.arraycopy(ciphertext,0,ciphertextz,TweetNaCl.BOX_BOXZEROBYTES,ciphertext.length);
//
//            byte[] p      = new byte[0];
//            byte[] q      = new byte[0];
//            long   start;
//            long   dt;
//            long   dt2;
//            
//            start = System.currentTimeMillis();
//            
//            for (int j=0; j<1024; j++) {
//                p = tweetnacl.cryptoBoxOpenAfterNM (ciphertextz,nonce, key);
//            }
//            
//            dt    = System.currentTimeMillis() - start;
//            start = System.currentTimeMillis();
//
//            for (int j=0; j<1024; j++) {
//                q = tweetnacl.cryptoBoxOpenAfterNMX(ciphertext, nonce, key);
//            }
//
//            dt2  = System.currentTimeMillis() - start;
//            DT  += dt;
//            DT2 += dt2;
//
//            assertTrue("OOOOPS !",Arrays.equals(Arrays.copyOfRange(p,TweetNaCl.BOX_ZEROBYTES,p.length),q));
//
//            android.util.Log.i(TAG,i + " DT: " + dt + "  " + dt2 + "  " + DT + "  " + DT2 + "  " + ((double) DT - (double) DT2)/(double) DT);
//        }
//
//        android.util.Log.i(TAG,"DT/FINAL: " + DT + "  " + DT2);
//    }
}
