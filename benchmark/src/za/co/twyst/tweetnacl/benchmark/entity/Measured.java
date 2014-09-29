package za.co.twyst.tweetnacl.benchmark.entity;

import za.co.twyst.tweetnacl.benchmark.entity.Benchmark.TYPE;

/** Container class for the results of 'run'.
 * 
 */
// TODO merge into Benchmark
public class Measured {
    // INSTANCE VARIABLES
    
    public final TYPE type;
    public long mean;
    public long throughput;
    
    public long minimum = Long.MAX_VALUE;
    public long maximum = Long.MIN_VALUE;
    public long bytes   = 0;
    public long dt      = 0;
    
    // CONSTRUCTOR
    
    public Measured(TYPE type) {
        this.type = type;
    }
    
    // INSTANCE METHODS
    
    public void update(long bytes,long dt) {
        if (dt > 0) {
            this.bytes     += bytes;
            this.dt        += dt;
            this.throughput = 1000 * bytes/dt;
            this.mean       = 1000 * this.bytes/this.dt;
            this.minimum    = Math.min(minimum,throughput);
            this.maximum    = Math.max(maximum,throughput);
        }
    }
}