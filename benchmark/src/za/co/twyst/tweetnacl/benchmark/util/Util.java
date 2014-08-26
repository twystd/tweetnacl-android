package za.co.twyst.tweetnacl.benchmark.util;

import java.util.Locale;

public class Util {
    // Ref. https://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
    public static String format(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        
        if (bytes < unit) { 
        	return bytes + " B";
        }
        
        int    exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        
        return String.format(Locale.getDefault(),"%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}

