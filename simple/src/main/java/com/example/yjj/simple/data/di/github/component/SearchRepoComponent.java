package com.example.yjj.simple.data.di.github.component;

import com.example.yjj.simple.presentation.view.SearchRepoActivity;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@Component(dependencies = GitHubComponent.class)
public interface SearchRepoComponent {

    void inject(SearchRepoActivity searchRepoActivity);
}
