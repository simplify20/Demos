package com.example.yjj.dagger_mvp.mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yjj.dagger_mvp.ApplicationComponent;
import com.example.yjj.dagger_mvp.DaggerApplication;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(DaggerApplication.getComponent());
    }

    protected abstract void setupActivityComponent(ApplicationComponent appComponent);
}
