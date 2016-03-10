package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.RetrofitDataFetcher;
import com.example.yjj.simple.data.web.api.GitHubApi;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class GitHubDataFetcher<T> extends RetrofitDataFetcher<T> {


    protected GitHubApi gitHubApi;

    public GitHubDataFetcher(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }
}