package com.example.wangyuchao.icook.utilityClass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.wangyuchao.icook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYuchao on 2017/6/6.
 */

public class LineCharView extends View {

    private int xori;//圆点x坐标
    private int yori;//圆点y坐标
    private int xinit;//第一个点x坐标
    private int minXinit;//在移动时，第一个点允许最小的x坐标
    private int maxXinit;//在移动时，第一个点允许允许最大的x坐标
    private int xylinecolor;//xy坐标轴颜色
    private int xylinewidth;//xy坐标轴大小
    private int xytextcolor;//xy坐标轴文字颜色
    private int xytextsize;//xy坐标轴文字大小
    private int linecolor;//折线的颜色
    private int interval;//坐标间的间隔
    private int bgColor;//背景颜色
    private List<String> x_coords;//x坐标点的值
    private List<String> x_coord_values;//每个点状态值
    private List<String> x_coord_values1;//每个点状态值


    private int width;//控件宽度
    private int heigth;//控件高度
    private int imageWidth;//表情的宽度
    private float textwidth;//y轴文字的宽度
    float startX=0;//滑动时候，上一次手指的x坐标

    public LineCharView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray= context.obtainStyledAttributes(attrs, R.styleable.LineChar);
        xylinecolor=typedArray.getColor(R.styleable.LineChar_xylinecolor, Color.GRAY);
        xylinewidth=typedArray.getInt(R.styleable.LineChar_xylinewidth, 5);
        xytextcolor=typedArray.getColor(R.styleable.LineChar_xytextcolor, Color.BLACK);
        xytextsize=typedArray.getLayoutDimension(R.styleable.LineChar_xytextsize, 20);
        linecolor=typedArray.getColor(R.styleable.LineChar_linecolor, Color.GRAY);
        interval=typedArray.getLayoutDimension(R.styleable.LineChar_interval, 100);
        bgColor=typedArray.getColor(R.styleable.LineChar_bgcolor, Color.WHITE);
        typedArray.recycle();
        x_coords=new ArrayList<String>();
        x_coord_values=new ArrayList<String>();
    }

    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        if(changed){
            width=getWidth();
            heigth=getHeight();
            Paint paint=new Paint();
            paint.setTextSize(xytextsize);
            textwidth= paint.measureText("A");
            xori=(int) (textwidth+6+2*xylinewidth);//6 为与y轴的间隔
            yori=heigth-xytextsize-2*xylinewidth-3;//3为x轴的间隔
            xinit=interval/2+xori;
            imageWidth= BitmapFactory.decodeResource(getResources(), R.drawable.linecharview_point).getWidth();
            minXinit=width-xori-x_coords.size()*interval;
            maxXinit=xinit;
            setBackgroundColor(bgColor);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    //画X轴坐标点，折线，表情
    private void drawX (Canvas canvas) {
        this.x_coords.add("6/1");
        this.x_coords.add("6/2");
        this.x_coords.add("6/3");
        this.x_coords.add("6/4");
//        this.x_coords.add("6/5");
//        this.x_coords.add("6/6");
//        this.x_coords.add("6/7");
//        this.x_coords.add("6/8");
        this.x_coord_values.add("A");
        this.x_coord_values.add("B");
        this.x_coord_values.add("C");
        this.x_coord_values.add("A");
//        this.x_coord_values.add("D");
//        this.x_coord_values.add("B");
//        this.x_coord_values.add("C");
//        this.x_coord_values.add("B");
//        this.x_coord_values1.add("B");
//        this.x_coord_values1.add("A");
//        this.x_coord_values1.add("D");
//        this.x_coord_values1.add("C");
        Paint x_coordPaint =new Paint();
        x_coordPaint.setTextSize(xytextsize);
        x_coordPaint.setStyle(Paint.Style.FILL);
        Path path=new Path();
  //      Path path1 = new Path();
        //画坐标轴上小原点，坐标轴文字
        for(int i=0;i<x_coords.size();i++){
            int x=i*interval+xinit;
            if(i==0){
                path.moveTo(x, getYValue(x_coord_values.get(i)));
      //          path1.moveTo(x, getYValue(x_coord_values1.get(i)));
            }else{
                path.lineTo(x, getYValue(x_coord_values.get(i)));
     //           path1.lineTo(x, getYValue(x_coord_values1.get(i)));
            }
            x_coordPaint.setColor(xylinecolor);
            canvas.drawCircle(x, yori, xylinewidth*2, x_coordPaint);
            String text=x_coords.get(i);
            x_coordPaint.setColor(xytextcolor);
            canvas.drawText(text, x-x_coordPaint.measureText(text)/2, yori+xytextsize+xylinewidth*2, x_coordPaint);
        }

        x_coordPaint.setStyle(Paint.Style.STROKE);
        x_coordPaint.setStrokeWidth(xylinewidth);
        x_coordPaint.setColor(linecolor);
        //画折线
        canvas.drawPath(path, x_coordPaint);
   //     canvas.drawPath(path1, x_coordPaint);

//        //画表情
//        for(int i=0;i<x_coords.size();i++){
//            int x=i*interval+xinit;
//            canvas.drawBitmap(getYBitmap(x_coord_values.get(i)), x-imageWidth/2, getYValue(x_coord_values.get(i))-imageWidth/2, x_coordPaint);
//        }

                //画状态点
        for(int i=0;i<x_coords.size();i++){
            Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.linecharview_point);
            Bitmap bitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.linecharview_point1);
            int x=i*interval+xinit;
            canvas.drawBitmap(bitmap, x-imageWidth/2, getYValue(x_coord_values.get(i))-imageWidth/2, x_coordPaint);
     //       canvas.drawBitmap(bitmap1, x-imageWidth/2, getYValue(x_coord_values1.get(i))-imageWidth/2, x_coordPaint);
        }


        //将折线超出x轴坐标的部分截取掉
        x_coordPaint.setStyle(Paint.Style.FILL);
        x_coordPaint.setColor(bgColor);
        x_coordPaint.setXfermode(new PorterDuffXfermode( PorterDuff.Mode.SRC_OVER));
        RectF rectF=new RectF(0, 0, xori, heigth);
        canvas.drawRect(rectF, x_coordPaint);

    }

    //得到y坐标
    private float getYValue(String value)
    {
        if(value.equalsIgnoreCase("A")){
            return  yori-interval/2;
        }
        else if(value.equalsIgnoreCase("B")){
            return  yori-interval;
        }
        else if(value.equalsIgnoreCase("C")){
            return  (float) (yori-interval*1.5);
        }
        else if(value.equalsIgnoreCase("D")){
            return yori-interval*2;
        }else{
            return yori;
        }
    }


//    //得到表情图
//    private Bitmap getYBitmap(String value){
//        Bitmap bitmap=null;
//        if(value.equalsIgnoreCase("A")){
//            bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.cooker_hood);
//        }
//        else if(value.equalsIgnoreCase("B")){
//            bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.faucet);
//        }
//        else if(value.equalsIgnoreCase("C")){
//            bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.integrated_stove);
//        }
//        else if(value.equalsIgnoreCase("D")){
//            bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.window);
//        }
//        return bitmap;
//    }

    //画坐标轴
    private void drawXY(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(xylinecolor);
        paint.setStrokeWidth(xylinewidth);
        canvas.drawLine(xori, 0, xori, yori, paint);
        canvas.drawLine(xori, yori, width, yori, paint);
    }

    //画Y轴坐标点
    private void drawY(Canvas canvas){
        Paint paint=new  Paint();
        paint.setColor(xylinecolor);
        paint.setStyle(Paint.Style.FILL);
        for(int i=1;i<5 ;i++){
            canvas.drawCircle(xori, yori-(i*interval/2), xylinewidth*2, paint);
        }

        paint.setTextSize(xytextsize);
        paint.setColor(xytextcolor);
        canvas.drawText("D",xori-textwidth-6-xylinewidth , yori-(2*interval)+xytextsize/2, paint);
        canvas.drawText("C",xori-textwidth-6-xylinewidth , (float) (yori-(1.5*interval)+xytextsize/2), paint);
        canvas.drawText("B",xori-textwidth-6-xylinewidth , yori-interval+xytextsize/2, paint);
        canvas.drawText("A",xori-textwidth-6-xylinewidth , (float) (yori-(0.5*interval)+xytextsize/2), paint);
    }

    protected void onDraw(Canvas canvas) {
        drawX(canvas);
        drawXY(canvas);
        drawY(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {

        //如果不用滑动就可以展示所有数据，就不让滑动
        if(interval*x_coord_values.size()<=width-xori){
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float dis=event.getX()-startX;
                startX=event.getX();
                if(xinit+dis>maxXinit){
                    xinit=maxXinit;
                }else if(xinit+dis<minXinit){
                    xinit=minXinit;
                }else{
                    xinit=(int) (xinit+dis);
                }
                invalidate();

                break;
        }
        return true;
    }

    /**
     * 设置坐标折线图值
     * @param x_coords 横坐标坐标点
     * @param x_coord_values 每个点的值
     */
    public void  setValue( List<String> x_coords ,List<String> x_coord_values) {
        if(x_coord_values.size()!=x_coords.size()){
            throw new IllegalArgumentException("坐标轴点和坐标轴点的值的个数必须一样!");
        }
        for(int i = 0;i < 10;i ++){
            this.x_coord_values.add("i");
            this.x_coords.add("i");
        }

        invalidate();
    }

}
