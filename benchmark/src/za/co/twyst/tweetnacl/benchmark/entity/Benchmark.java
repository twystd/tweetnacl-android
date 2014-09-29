package za.co.twyst.tweetnacl.benchmark.entity;

import android.os.Parcel;
import android.os.Parcelable;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.util.Util;

/** Container class for a performance measurement.
 * 
 */
public class Benchmark implements Parcelable {
    // CONSTANTS
    
    public enum TYPE { 
        UNKNOWN                  (0, R.string.label_unknown),
        CRYPTO_BOX               (1, R.string.label_crypto_box),
        CRYPTO_BOX_OPEN          (2, R.string.label_crypto_box_open),
        CRYPTO_CORE_HSALSA20     (3, R.string.label_crypto_core_hsalsa20),
        CRYPTO_CORE_SALSA20      (4, R.string.label_crypto_core_salsa20),
        CRYPTO_HASH              (5, R.string.label_crypto_hash),
        CRYPTO_HASHBLOCKS        (6, R.string.label_crypto_hashblocks),
        CRYPTO_ONETIMEAUTH       (7, R.string.label_crypto_onetimeauth),
        CRYPTO_ONETIMEAUTH_VERIFY(8, R.string.label_crypto_onetimeauth_verify),
        CRYPTO_SCALARMULT_BASE   (9, R.string.label_crypto_scalarmult),
        CRYPTO_SCALARMULT        (10,R.string.label_crypto_scalarmultbase),
        CRYPTO_SECRETBOX         (11,R.string.label_crypto_secretbox),
        CRYPTO_SECRETBOX_OPEN    (12,R.string.label_crypto_secretbox_open),
        CRYPTO_STREAM_XOR        (13,R.string.label_crypto_stream_xor),
        CRYPTO_STREAM_SALSA20_XOR(14,R.string.label_crypto_stream_salsa20_xor),
        CRYPTO_SIGN              (15,R.string.label_crypto_sign),
        CRYPTO_SIGN_OPEN         (16,R.string.label_crypto_sign_open);
        
        private final int type;
        public  final int label;
        
        private TYPE(int type,int label) {
            this.type  = type;
            this.label = label;
        }
        
        private static TYPE parse(int type) {
            for (TYPE item: values()) {
                if (item.type == type) {
                    return item;
                }
            }
            
            return UNKNOWN;
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

    // *** Parcelable ***
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel,int flags) {
        parcel.writeInt   (type.type);
        parcel.writeString(value);
    }
    
    public static final Parcelable.Creator<Benchmark> CREATOR = new Parcelable.Creator<Benchmark>() {
        @Override
        public Benchmark createFromParcel(Parcel parcel) { 
            int    type  = parcel.readInt();
            String value = parcel.readString();
            
            return new Benchmark(TYPE.parse(type),value);
        }

        @Override
        public Benchmark[] newArray(int size) {
            return new Benchmark[size];
        }
    };
}
