package com.example.yjj.dagger_mvp.ui.module;

import com.example.yjj.dagger_mvp.data.AccountManager;
import com.example.yjj.dagger_mvp.data.User;
import com.example.yjj.dagger_mvp.UserScope;
import com.example.yjj.dagger_mvp.util.UserRecorder;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Module
public class MainPresenterModule {
    @Provides
    @UserScope
    public User provideUser() {
        return AccountManager.getCurrentUser();
    }

    @Provides
    @UserScope
    public UserRecorder provideUserRecorder() {
        return new UserRecorder();
    }
}
