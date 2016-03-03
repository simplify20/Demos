package com.example.yjj.demoproj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.graphics.ColorUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/1/14
 * @email:yangjianjun@117go.com
 *
 * https://corner.squareup.com/2015/12/welcome-to-the-color-matrix.html
 */
public class ColorActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView imageView;
    @Bind(R.id.desc)
    TextView textView;

    private static Bitmap bitmap;
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);
        loadBitmapInBackground();
    }

    private void loadBitmapInBackground() {
        HandlerThread handlerThread = new HandlerThread("loadBitmap");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new LoadBitmapRunnable());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null)
            bitmap.recycle();
    }

    private class LoadBitmapRunnable implements Runnable {
        int color = 0xffbeef00;

        @Override
        public void run() {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            Bitmap temp = BitmapFactory.decodeResource(TestApplication.getContext().getResources(), R.drawable.picasso, options);
            bitmap = processBitmap(temp);
            final int textColor = computeTitleColor(color);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    textView.setTextColor(textColor);
                }
            });
        }

        private Bitmap processBitmap(Bitmap source) {
            float lr = 0.2126f;
            float lg = 0.7152f;
            float lb = 0.0722f;

            ColorMatrix grayscaleMatrix = new ColorMatrix(new float[]{
                    lr, lg, lb, 0, 0,
                    lr, lg, lb, 0, 0,
                    lr, lg, lb, 0, 0,
                    0, 0, 0, 1, 0
            });


            int dr = Color.red(color);
            int dg = Color.green(color);
            int db = Color.blue(color);
            float drf = dr / 255f;
            float dgf = dg / 255f;
            float dbf = db / 255f;

            ColorMatrix tintMatrix = new ColorMatrix(new float[]{
                    drf, 0, 0, 0, 0, //
                    0, dgf, 0, 0, 0, //
                    0, 0, dbf, 0, 0, //
                    0, 0, 0, 1, 0, //
            });
            tintMatrix.preConcat(grayscaleMatrix);


            float lDestination = drf * lr + dgf * lg + dbf * lb;
            float scale = 1f - lDestination;
            float translate = 1 - scale * 0.5f;

            ColorMatrix scaleMatrix = new ColorMatrix(new float[]{
                    scale, 0, 0, 0, dr * translate, //
                    0, scale, 0, 0, dg * translate, //
                    0, 0, scale, 0, db * translate, //
                    0, 0, 0, 1, 0, //
            });

            scaleMatrix.preConcat(tintMatrix);

            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(scaleMatrix);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColorFilter(colorMatrixColorFilter);

            int width = source.getWidth();
            int height = source.getHeight();
            Bitmap destination = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(destination);
            canvas.drawBitmap(source, 0, 0, paint);
            source.recycle();

            return destination;
        }

        private int computeTitleColor(int destinationColor) {
            int titleAlpha = ColorUtils.calculateMinimumAlpha(Color.WHITE, destinationColor, 3.0f);
            int titleColor;
            if (titleAlpha != -1) {
                titleColor = ColorUtils.setAlphaComponent(Color.WHITE, titleAlpha);
            } else {
                titleAlpha = ColorUtils.calculateMinimumAlpha(Color.BLACK, destinationColor, 3.0f);
                titleColor = ColorUtils.setAlphaComponent(Color.BLACK, titleAlpha);
            }
            return titleColor;
        }
    }
}
