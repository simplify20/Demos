package com.example.yjj.demoproj.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjj.demoproj.R;

/**
 * @author:YJJ
 * @date:2016/1/13
 * @email:yangjianjun@117go.com
 */
public class XferView extends View {

    private Paint mMaskPaint;
    private Paint mPaint;
    private float mWidth;
    private float mHeight;

    private PorterDuff.Mode mode;

    public XferView(Context context) {
        this(context, null);
    }

    public XferView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setMode(PorterDuff.Mode mode) {
        this.mode = mode;
        mMaskPaint.setXfermode(new PorterDuffXfermode(mode));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        canvas.drawRect(mWidth / 2, mHeight / 2, mWidth / 2 + mWidth, mHeight / 2 + mHeight, mMaskPaint);
        super.onDraw(canvas);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.dst_color));
        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setStyle(Paint.Style.FILL);
        mMaskPaint.setColor(getResources().getColor(R.color.src_color));
        if (Build.VERSION.SDK_INT >= 11)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mWidth = getMeasuredWidth() - getMeasuredWidth() / 2;
            mHeight = getMeasuredHeight() - getMeasuredHeight() / 2;
        }


    }
}
