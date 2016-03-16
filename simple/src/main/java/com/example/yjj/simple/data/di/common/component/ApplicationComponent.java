package com.example.yjj.simple.data.di.common.component;

import android.content.Context;

import com.example.yjj.simple.data.di.common.ApplicationScope;
import com.example.yjj.simple.data.di.common.module.ApplicationModule;
import com.example.yjj.simple.data.di.common.module.ScheduleModule;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.repository.impl.RepositoryManager;

import javax.inject.Named;

import dagger.Component;
import rx.Scheduler;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
@ApplicationScope
@Component(modules = {ApplicationModule.class, ScheduleModule.class})
public interface ApplicationComponent {
    RepositoryManager getRepositoryManager();

    Context getContext();

    @Named(QualifierConstants.PROVIDE_POST_SCHEDULE)
    Scheduler getPostScheduler();

    @Named(QualifierConstants.PROVIDE_WORK_SCHEDULE)
    Scheduler getWorkScheduler();
}
