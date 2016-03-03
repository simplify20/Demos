package com.example.yjj.demoproj.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjj.demoproj.R;
import com.example.yjj.demoproj.util.DeviceUtils;

/**
 * @author:YJJ
 * @date:2016/1/18
 * @email:yangjianjun@117go.com
 */
public class RoundImageView extends View {
    private Paint mBitmapPaint;
    private Paint mMaskPaint;
    private Bitmap bitmap;
    private float screenW = DeviceUtils.getDisplay().widthPixels;
    private float screenH = DeviceUtils.getDisplay().heightPixels;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setStyle(Paint.Style.FILL);
        mMaskPaint.setColor(Color.GRAY);
        mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picasso);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rectF, 15f, 15f, mBitmapPaint);
        canvas.drawBitmap(bitmap, 0, 0, mMaskPaint);
        canvas.restoreToCount(sc);

    }

}
