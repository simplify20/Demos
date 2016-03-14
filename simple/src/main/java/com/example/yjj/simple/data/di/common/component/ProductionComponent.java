package com.example.yjj.simple.data.di.common.component;

import android.databinding.DataBindingComponent;

import com.example.yjj.simple.data.di.common.module.ProductionModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@Component(modules = ProductionModule.class)
public interface ProductionComponent extends DataBindingComponent {
}
