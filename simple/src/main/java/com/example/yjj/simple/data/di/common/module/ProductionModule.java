package com.example.yjj.simple.data.di.common.module;

import com.example.yjj.simple.presentation.bindingadapter.ImageAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@Module
public class ProductionModule {

    @Provides
    ImageAdapter imageAdapter() {
        return new ImageAdapter();
    }
}
