package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.BaseObservableDataSource;
import com.example.yjj.simple.data.entity.github.Contributor;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class ContributorsDataSource extends BaseObservableDataSource<List<Contributor>, List<Contributor>> {

    @Inject
    public ContributorsDataSource(DataFetcher<List<Contributor>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public List<Contributor> convert(List<Contributor> contributors) {
        return contributors;
    }

    public static class ContributorFetcher extends GitHubDataFetcher<List<Contributor>> {

        @Inject
        public ContributorFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public List<Contributor> fetchDataImpl(RequestParameter parameter) throws Exception {
            call = gitHubApi.contributors(parameter.get("owner"), parameter.get("repo"));
            List<Contributor> result = call.execute().body();
            return result;
        }

        @Override
        public void putValues(RequestParameter parameter, String... values) {
            parameter.put("owner", values[0]);
            parameter.put("repo", values[1]);
        }
    }
}
