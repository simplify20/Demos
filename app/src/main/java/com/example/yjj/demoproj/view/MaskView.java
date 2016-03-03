package com.example.yjj.demoproj.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjj.demoproj.util.DeviceUtils;

/**
 * @author:YJJ
 * @date:2016/1/20
 * @email:yangjianjun@117go.com
 */
public class MaskView extends View {
    Paint mPaint;
    private float screenW = DeviceUtils.getDisplay().widthPixels;
    private float screenH = DeviceUtils.getDisplay().heightPixels;

    public MaskView(Context context) {
        this(context, null);
    }

    public MaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setMaskFilter(new BlurMaskFilter(5f, BlurMaskFilter.Blur.SOLID));
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(DeviceUtils.spToPixel(20));

//        setLayerType(LAYER_TYPE_SOFTWARE, null);//DELETE THIS LINE TO SEE IF THE BLUR effect IS APPLIED
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect bound = new Rect(0, 0, 0, 0);
        mPaint.getTextBounds("HELLO", 0, "HELLO".length(), bound);
        setMeasuredDimension(bound.width() * 4, bound.height() * 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.MATRIX_SAVE_FLAG |
//                Canvas.CLIP_SAVE_FLAG |
//                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
//                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
//                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        Rect bound = new Rect(0, 0, 0, 0);
        mPaint.getTextBounds("HELLO", 0, "HELLO".length(), bound);
        canvas.drawText("HELLO", 0, bound.height(), mPaint);

        canvas.drawRect(0, bound.height(), 50, bound.height() + 50, mPaint);


//        canvas.restoreToCount(sc);
    }
}
