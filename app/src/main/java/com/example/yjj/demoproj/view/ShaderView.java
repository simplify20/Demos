package com.example.yjj.demoproj.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjj.demoproj.R;

/**
 * @author:YJJ
 * @date:2016/1/20
 * @email:yangjianjun@117go.com
 */
public class ShaderView extends View {
    Bitmap bitmap;
    Paint mPaint;
    int width;
    int height;

    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picasso);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width * 2, height * 2);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRoundRect(0, 0, width - 20, height - 20, 10, 10, mPaint);
        canvas.drawRect(0, 0, width * 2, height * 2, mPaint);

    }
}
