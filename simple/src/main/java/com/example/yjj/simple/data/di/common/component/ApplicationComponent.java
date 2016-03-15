package com.example.yjj.simple.data.di.common.component;

import android.content.Context;

import com.example.yjj.simple.data.di.common.module.ApplicationModule;
import com.example.yjj.simple.framework.repository.impl.RepositoryManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    RepositoryManager getRepositoryManager();

    Context getContext();
}
