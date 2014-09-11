package za.co.twyst.tweetnacl.benchmark.ui.widgets;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.drawable.shapes.Shape;

public class Lozenge extends Shape {
    // CONSTANTS
    
    private static final double ANGLE = 10.0;
    
    // INSTANCE VARIABLES
    
    private final boolean reversed;
    private final double  angle  = ANGLE;
    private final Paint   border = new Paint();
    
    private final PathEffect   effect;
    private final Path         path;  


    public Lozenge(boolean reversed) {
        this.reversed = reversed;

        effect = new CornerPathEffect(5);
        path   = new Path();

        border.setColor      (0xff436eb4);
        border.setStyle      (Paint.Style.STROKE);
        border.setStrokeWidth(2);
        border.setAntiAlias  (true);
        border.setDither     (true);
        border.setPathEffect (effect);
        border.setStrokeJoin (Paint.Join.ROUND);  
        border.setStrokeCap  (Paint.Cap.BUTT);  
    }

    
    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);

        float dx     = 1;
        float dy     = 1;
        float w      = width  - dx;
        float h      = height - dy;
        float offset = (float) (h * Math.sin(angle * Math.PI/180.0));

        if (reversed) {
            path.reset ();
            path.moveTo(offset,dy);
            path.lineTo(dx,h);
            path.lineTo(w-offset,h);
            path.lineTo(w,dy);
            path.lineTo(offset,dy);
            path.close ();
        } else {
            path.reset ();
            path.moveTo(dx,dy);
            path.lineTo(offset,h);
            path.lineTo(w,h);
            path.lineTo(w-offset,dy);
            path.lineTo(dx,dy);
            path.close ();
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPath(path,border);
    }

}
