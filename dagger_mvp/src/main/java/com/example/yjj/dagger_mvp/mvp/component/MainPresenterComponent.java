package com.example.yjj.dagger_mvp.mvp.component;

import com.example.yjj.dagger_mvp.data.User;
import com.example.yjj.dagger_mvp.UserScope;
import com.example.yjj.dagger_mvp.mvp.module.MainPresenterModule;
import com.example.yjj.dagger_mvp.mvp.presenter.MainPresenter;
import com.example.yjj.dagger_mvp.util.UserRecorder;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@UserScope
@Component(dependencies = MainActivityComponent.class, modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(MainPresenter presenter);

    User user();

    UserRecorder userRecorder();

}
