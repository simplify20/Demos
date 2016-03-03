package com.example.yjj.demoproj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yjj.demoproj.dagger2.module.ActivityModule;
import com.example.yjj.demoproj.dagger2.component.ApplicationComponent;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class BaseActivity extends AppCompatActivity {
    @Inject
    public SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
}

    protected ApplicationComponent getApplicationComponent() {
        return ((TestApplication) getApplication()).getComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
