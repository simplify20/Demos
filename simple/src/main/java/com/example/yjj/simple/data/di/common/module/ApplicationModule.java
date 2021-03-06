package com.example.yjj.simple.data.di.common.module;

import android.content.Context;

import com.example.yjj.simple.data.di.common.ApplicationScope;
import com.example.yjj.simple.framework.repository.impl.RepositoryManager;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context context() {
        return context;
    }

    @Provides
    @ApplicationScope
    RepositoryManager repositoryManager() {
        return new RepositoryManager();
    }
}
