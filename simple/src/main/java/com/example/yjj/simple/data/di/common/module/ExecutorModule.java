package com.example.yjj.simple.data.di.common.module;

import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.web.api.QualifierConstants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@Module
public class ExecutorModule {

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_SINGLE_THREAD_EXECUTOR)
    Executor workExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
