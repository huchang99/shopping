package com.huchang.haxi.shoppingclient.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Siderbar extends View {

    //定义26个字母
    private String[] SiderData ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    //定义画笔
    private Paint mPaint = new Paint();

    //定义接口变量
    private TouchSiderbatChangedListener onTouchSiderbatChangedListener;

    public Siderbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的属性
        mPaint.setColor(Color.GRAY);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(40);
        //获取自定义View的宽和高
        int height = getHeight();
        int width = getWidth();
        //设定每一个字母所在控件的高度
        int each_height = height/SiderData.length;
        //给每一个字母绘制出来
        for (int i = 0; i < SiderData.length; i++) {
            //字体所在区域在x轴的偏移量
            float x =  width/2-mPaint.measureText(SiderData[i])/2;
            //字体所在区域在Y轴的偏移量
            float y = (1+i)*each_height;
            canvas.drawText(SiderData[i], x, y, mPaint);
        }
    }

    //定义一个监听事件的接口
    public interface TouchSiderbatChangedListener{
         void OnTouchSiderbatChange(String s);}
    //定义一个监听方法
    public void SetOnTouchSiderbatChangedListener(TouchSiderbatChangedListener onTouchSiderbatChangedListener){
        this.onTouchSiderbatChangedListener = onTouchSiderbatChangedListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction(); //获取对应的动作
        final float y = event.getY(); //获取点击Y的坐标
        final int c = (int)(y/getHeight()*SiderData.length);
        final TouchSiderbatChangedListener listener = onTouchSiderbatChangedListener;
        switch (action)
        {
            case MotionEvent.ACTION_UP:{
                break;
            }
            default:
            {
                if(c>0&&c<SiderData.length){
                    if(listener!=null)
                        listener.OnTouchSiderbatChange(SiderData[c]);
                }
                break;
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
