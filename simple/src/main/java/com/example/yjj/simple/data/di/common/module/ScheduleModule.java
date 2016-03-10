package com.example.yjj.simple.data.di.common.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
@Module
public class ScheduleModule {
    @Provides
    @Named("post")
    Scheduler postScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("work")
    Scheduler workScheduler() {
        return Schedulers.newThread();
    }
}
