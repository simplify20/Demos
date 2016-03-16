package com.example.yjj.simple.data.di.github.component;

import com.example.yjj.simple.biz.github.RepoService;
import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.di.common.component.ApplicationComponent;
import com.example.yjj.simple.data.di.github.module.RepoModule;
import com.example.yjj.simple.presentation.view.SearchRepoActivity;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = RepoModule.class)
public interface RepoComponent {
    void inject(SearchRepoActivity searchRepoActivity);

    RepoService getRepoService();
}
