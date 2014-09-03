package za.co.twyst.tweetnacl.benchmark.entity;

import android.os.Parcel;
import android.os.Parcelable;

/** Container class for a performance measurement.
 * 
 */
public class Benchmark implements Parcelable {
    // CONSTANTS
    
    public enum TYPE { 
        UNKNOWN         (0),
        CRYPTO_BOX      (1),
        CRYPTO_BOX_OPEN (2);
        
        private final int type;
        
        private TYPE(int type) {
            this.type = type;
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
