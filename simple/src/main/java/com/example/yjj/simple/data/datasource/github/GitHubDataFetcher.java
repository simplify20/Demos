package com.example.yjj.simple.data.datasource.github;

import android.support.annotation.NonNull;

import com.example.yjj.simple.data.datasource.RetrofitDataFetcher;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.IParameter;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class GitHubDataFetcher<R> extends RetrofitDataFetcher<R> {


    protected GitHubApi gitHubApi;

    public GitHubDataFetcher(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    /**
     * default impl of putValues,do nothing
     *
     * @param parameter
     * @param values
     */
    @Override
    public IParameter putValues(@NonNull IParameter<String,String> parameter, @NonNull String... values) {
        return parameter;
    }
}