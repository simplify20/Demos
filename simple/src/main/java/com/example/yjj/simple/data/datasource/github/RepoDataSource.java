package com.example.yjj.simple.data.datasource.github;

import android.support.annotation.NonNull;

import com.example.yjj.simple.data.datasource.BaseObservableDataSource;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class RepoDataSource extends BaseObservableDataSource<List<Repo>, List<Repo>> {

    @Inject
    public RepoDataSource(DataFetcher<List<Repo>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }

    public static class RepoFetcher extends GitHubDataFetcher<List<Repo>> {

        @Inject
        public RepoFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public List<Repo> fetchDataImpl(@NonNull RequestParameter parameter) throws Exception {
            call = gitHubApi.listRepos(parameter.get("user"));
            List<Repo> result = call.execute().body();
            return result;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> parameter, @NonNull String... values) {
            if (values == null || values.length == 0)
                return parameter;
            parameter.put("user", values[0]);
            return parameter;
        }
    }
}
