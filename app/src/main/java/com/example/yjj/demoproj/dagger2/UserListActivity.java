package com.example.yjj.demoproj.dagger2;

import android.os.Bundle;

import com.example.yjj.demoproj.BaseActivity;
import com.example.yjj.demoproj.R;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class UserListActivity extends BaseActivity implements UserListFragment.Injector {

    private UserComponent userComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        mSharedPrefs.edit().putString("dagger2", "test injection").apply();
    }

    @Override
    public void inject(UserListFragment fragment) {
        userComponent.inject(fragment);
    }
}
