package com.example.yjj.demoproj;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/1/29
 * @email:yangjianjun@117go.com
 */
public class AnimationActivity extends BaseActivity {
    @Bind(R.id.circle)
    View circle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_scale_in);
        circle.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animator animator = AnimatorInflater.loadAnimator(AnimationActivity.this, R.animator.trsnslate_scale_in);
                animator.setTarget(circle);
                animator.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
