package com.example.yjj.simple.data.di.github.producer;

import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.IParameter;

import java.util.List;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;
import retrofit2.Call;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@ProducerModule
public class GitHubProducer {
    private IParameter<String, String> parameter;
    private GitHubApi gitHubApi;

    public GitHubProducer(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    public void setParameter(IParameter<String, String> parameter) {
        this.parameter = parameter;
    }

    @Produces
    List<Repo> getRepos() throws Exception {
        Call<List<Repo>> call = gitHubApi.listRepos(parameter.get("user"));
        List<Repo> result = call.execute().body();
        return result;
    }

}
