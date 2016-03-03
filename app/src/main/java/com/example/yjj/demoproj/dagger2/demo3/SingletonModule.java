package com.example.yjj.demoproj.dagger2.demo3;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Module
public class SingletonModule {
    @Provides
    @Singleton
    public UploadManager provideUploadManager() {
        return new UploadManager();
    }

}
