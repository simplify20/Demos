package com.example.yjj.dagger_mvp.ui.component;

import com.example.yjj.dagger_mvp.ApplicationComponent;
import com.example.yjj.dagger_mvp.ActivityScope;
import com.example.yjj.dagger_mvp.ui.activity.MainActivity;
import com.example.yjj.dagger_mvp.ui.module.MainActivityModule;
import com.example.yjj.dagger_mvp.ui.module.UserModelModule;
import com.example.yjj.dagger_mvp.ui.presenter.MainPresenter;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {MainActivityModule.class, UserModelModule.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);

    MainPresenter presenter();


}
