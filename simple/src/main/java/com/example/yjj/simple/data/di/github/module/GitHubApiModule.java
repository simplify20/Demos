package com.example.yjj.simple.data.di.github.module;

import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.data.web.api.QualifierConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@Module
public class GitHubApiModule {
    public static final String API_URL = "https://api.github.com";
    private String baseUrl = API_URL;

    public GitHubApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public GitHubApiModule() {
    }

    /***************************
     * API
     ***************************/

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_GIT_HUB_API)
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScope
    @Provides
    GitHubApi gitHubApi(@Named(QualifierConstants.PROVIDE_GIT_HUB_API) Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }


}
