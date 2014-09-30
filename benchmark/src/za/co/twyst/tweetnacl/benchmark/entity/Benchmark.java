package za.co.twyst.tweetnacl.benchmark.entity;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.util.Util;

/** Container class for a performance measurement.
 * 
 */
public class Benchmark {
    // CONSTANTS
    
    public enum TYPE { 
        UNKNOWN                 (-1, R.string.label_unknown),
        CRYPTO_BOX               (0, R.string.label_crypto_box),
        CRYPTO_BOX_OPEN          (1, R.string.label_crypto_box_open),
        CRYPTO_CORE_HSALSA20     (2, R.string.label_crypto_core_hsalsa20),
        CRYPTO_CORE_SALSA20      (3, R.string.label_crypto_core_salsa20),
        CRYPTO_HASH              (4, R.string.label_crypto_hash),
        CRYPTO_HASHBLOCKS        (5, R.string.label_crypto_hashblocks),
        CRYPTO_ONETIMEAUTH       (6, R.string.label_crypto_onetimeauth),
        CRYPTO_ONETIMEAUTH_VERIFY(7, R.string.label_crypto_onetimeauth_verify),
        CRYPTO_SCALARMULT_BASE   (8, R.string.label_crypto_scalarmult),
        CRYPTO_SCALARMULT        (9, R.string.label_crypto_scalarmultbase),
        CRYPTO_SECRETBOX         (10,R.string.label_crypto_secretbox),
        CRYPTO_SECRETBOX_OPEN    (11,R.string.label_crypto_secretbox_open),
        CRYPTO_STREAM_XOR        (12,R.string.label_crypto_stream_xor),
        CRYPTO_STREAM_SALSA20_XOR(13,R.string.label_crypto_stream_salsa20_xor),
        CRYPTO_SIGN              (14,R.string.label_crypto_sign),
        CRYPTO_SIGN_OPEN         (15,R.string.label_crypto_sign_open),
        CRYPTO_VERIFY16          (16,R.string.label_crypto_verify16),
        CRYPTO_VERIFY32          (17,R.string.label_crypto_verify32);
        
        public  final int row;
        public  final int label;
        
        private TYPE(int row,int label) {
            this.row   = row;
            this.label = label;
        }
    };
    
    // INSTANCE VARIABLES
    
    public final TYPE   type;
    public final String value;
    
    // CLASS METHODS
    
    /** Pretty formats a throughput value.
     * 
     */
    private static String format(long throughput) {
        return String.format("%s/s",Util.format(throughput,true));
    }
    
    // CONSTRUCTOR
    
    /** Initialises the measurement values.
     * 
     */
    public Benchmark(TYPE type,String value) {
        // ... validate
        
        if (type == null) {
            throw new IllegalArgumentException("Invalid measurement type");
        }
        
        // ... initialise
        
        this.type  = type;
        this.value = value == null ? "" : value.trim();
    }
    
    /** Initialises the measurement values.
     * 
     */
    public Benchmark(Measured measurement) {
        // ... validate
        
        if (measurement == null) {
            throw new IllegalArgumentException("Invalid measurement");
        }
        
        // ... initialise
        
        this.type  = measurement.type;
        this.value = format(measurement.mean);
    }
}
