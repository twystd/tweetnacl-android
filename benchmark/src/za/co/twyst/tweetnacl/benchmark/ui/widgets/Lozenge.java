package za.co.twyst.tweetnacl.benchmark.ui.widgets;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.drawable.shapes.Shape;

public class Lozenge extends Shape {
    // CONSTANTS

    private static final int BLACK      = 0xff222222;    
    private static final int WHITE      = 0xffffffff;    
    private static final int GREY       = 0xff808080;    
    private static final int LIGHT_GREY = 0xffcccccc;    
    private static final int DARK_GRAY  = 0xff666666;    

    private static final int VICHYSSOISE = 0xffefffcd;    
    private static final int HEX         = 0xffdce9be;    
    private static final int SHADOW_DIRT = 0xff555152;    
    private static final int EVENING_CLOUD = 0xff2e2633;
    private static final int HELIOTROPE    = 0xff99173c;

    private static final int NEJRO        = 0xff060604;    
    private static final int ACORN        = 0xff915523;    
    private static final int SKY_LAYER    = 0xffcf7340;    
    private static final int LIGHT_ORANGE = 0xffeead77;    
    private static final int TAMARA       = 0xffffdba9;    

    private static final float  STROKEWIDTH = 1.0f;
    private static final double ANGLE       = 10.0;
    private static final int    FILL        = BLACK; // LIGHT_ORANGE;
    private static final int    BORDER      = BLACK; // SKY_LAYER;
    
    // INSTANCE VARIABLES
    
    private final boolean reversed;
    private final double  angle  = ANGLE;
    private final Paint   border = new Paint();
    private final Paint   fill   = new Paint();
    
    private final PathEffect   effect;
    private final Path         path;  


    public Lozenge(boolean reversed) {
        this.reversed = reversed;

        effect = new CornerPathEffect(5);
        path   = new Path();

        fill.setColor      (FILL);
        fill.setStyle      (Paint.Style.FILL);
        fill.setAntiAlias  (true);
        fill.setDither     (true);
        fill.setPathEffect (effect);
        fill.setStrokeJoin (Paint.Join.ROUND);  
        fill.setStrokeCap  (Paint.Cap.ROUND);  

        border.setColor      (BORDER);
        border.setStyle      (Paint.Style.STROKE);
        border.setStrokeWidth(STROKEWIDTH);
        border.setAntiAlias  (true);
        border.setDither     (true);
//      border.setPathEffect (effect);
        border.setStrokeJoin (Paint.Join.ROUND);  
        border.setStrokeCap  (Paint.Cap.ROUND);  
    }

    
    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);

        float dx     = STROKEWIDTH/2.0f;
        float dy     = STROKEWIDTH/2.0f;
        float x      = dx;
        float y      = dy;
        float w      = width  - dx;
        float h      = height - dy;
        float offset = (float) (h * Math.sin(angle * Math.PI/180.0));

        if (reversed) {
            path.reset ();
            path.moveTo(offset,y);
            path.lineTo(x,h);
            path.lineTo(w-offset,h);
            path.lineTo(w,y);
            path.close ();
        } else {
            path.reset ();
            path.moveTo(x,y);
            path.lineTo(offset,h);
            path.lineTo(w,h);
            path.lineTo(w-offset,y);
            path.close ();
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPath(path,fill);
        canvas.drawPath(path,border);
    }

}
