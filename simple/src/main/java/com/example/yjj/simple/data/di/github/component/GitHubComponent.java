package com.example.yjj.simple.data.di.github.component;

import com.example.yjj.simple.biz.github.impl.ContributorsServiceImpl;
import com.example.yjj.simple.biz.github.impl.ReposServiceImpl;
import com.example.yjj.simple.data.di.github.module.GitHubApiModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@Component(modules = GitHubApiModule.class)
public interface GitHubComponent {

    /**
     * if use interface type,must have a provide method
     *
     * @return
     */
    ContributorsServiceImpl getContributorsService();

    ReposServiceImpl getRepoService();
}
