package com.example.yjj.demoproj.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yjj.demoproj.R;

/**
 * @author:YJJ
 * @date:2016/2/19
 * @email:yangjianjun@117go.com
 */
public class RectangleProgressBar extends RelativeLayout {


    private View progressView;
    private int progress;

    public RectangleProgressBar(Context context) {
        this(context, null);
    }

    public RectangleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectangleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        progressView = LayoutInflater.from(context).inflate(R.layout.progress_bar, this, true);
    }

    public void setWidth(int width) {
        RelativeLayout.LayoutParams lp1 = (LayoutParams) progressView.getLayoutParams();
        lp1.width = width;
        progressView.setLayoutParams(lp1);
    }

    public void setHeight(int height) {
        RelativeLayout.LayoutParams lp1 = (LayoutParams) progressView.getLayoutParams();
        lp1.height = height;
        progressView.setLayoutParams(lp1);
    }

    public void setSize(int width, int height) {
        RelativeLayout.LayoutParams lp1 = (LayoutParams) progressView.getLayoutParams();
        lp1.height = height;
        lp1.width = width;
        progressView.setLayoutParams(lp1);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        View view = progressView.findViewById(R.id.foreground);
        final ClipDrawable clipDrawable = (ClipDrawable) view.getBackground();
        int startLevel = clipDrawable.getLevel();
        int endLevel = 1000 * progress / 10;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startLevel, endLevel);
        valueAnimator.setDuration(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                clipDrawable.setLevel((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    public int getProgress() {
        return progress;
    }
}
