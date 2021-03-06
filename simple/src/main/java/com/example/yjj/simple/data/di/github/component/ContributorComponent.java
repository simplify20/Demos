package com.example.yjj.simple.data.di.github.component;

import com.example.yjj.simple.biz.github.ContributorsService;
import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.di.common.component.ApplicationComponent;
import com.example.yjj.simple.data.di.github.module.ContributorsModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,modules = ContributorsModule.class)
public interface ContributorComponent {
    ContributorsService getContributorsService();
}
