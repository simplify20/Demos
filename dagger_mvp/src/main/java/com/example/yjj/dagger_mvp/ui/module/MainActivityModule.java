package com.example.yjj.dagger_mvp.ui.module;

import com.example.yjj.dagger_mvp.ActivityScope;
import com.example.yjj.dagger_mvp.ui.activity.MainActivity;
import com.example.yjj.dagger_mvp.ui.model.UserModel;
import com.example.yjj.dagger_mvp.ui.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public MainActivity provideMainActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public MainPresenter providePresenter(MainActivity activity, UserModel userModel) {
        return new MainPresenter(activity, userModel);
    }

}
