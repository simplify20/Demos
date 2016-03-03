package com.example.yjj.demoproj.dagger2.demo3;

import javax.inject.Singleton;

import dagger.*;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Singleton
@Component(modules = SingletonModule.class)
public interface SingletonComponent {

    UploadManager uploadManager();

    SubComponent subcomponent();
}
