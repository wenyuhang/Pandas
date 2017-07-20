package com.example.pandas.homes.pandaculture;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class CircleImageView extends android.support.v7.widget.AppCompatImageView{

    public static final int POINT=1;

    public static final int CIRCLE=2;

    public void setCircleModel(int circleModel) {
        this.circleModel = circleModel;
    }

    private int circleModel;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap=null;
        int width=getWidth();
        int height=getHeight();
        BitmapDrawable drawable= (BitmapDrawable) getDrawable();

        bitmap= drawable.getBitmap();
        bitmap=zoomBitmap(bitmap,getWidth(),getHeight());
        Bitmap circleBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas circleCanvas=new Canvas(circleBitmap);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setAntiAlias(true);
        int raduis=0;

        if (width<height)
            raduis=width/2;
        else
            raduis=height/2;

        if (circleModel==POINT)
        raduis=raduis/2;

        circleCanvas.drawCircle(width/2,height/2,raduis,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        circleCanvas.drawBitmap(bitmap,0,0,paint);

        canvas.drawBitmap(circleBitmap,0,0,null);
    }

    /**
     * 用于对bitmap进行缩放
     * @param bitmap 原来未缩放的bitmap
     * @param width 目标宽度
     * @param height  目标高度
     * @return 缩放后的bitmap
     */


    public Bitmap zoomBitmap(Bitmap bitmap,int width,int height){

        int w=bitmap.getWidth();

        int h=bitmap.getHeight();

        Matrix matrix=new Matrix();

        float scaleWidth=(float) width/w;

        float scaleHeight=(float) height/h;

        matrix.postScale(scaleWidth,scaleHeight);

        return Bitmap.createBitmap(bitmap,0,0,w,h,matrix,true);
    }


}
