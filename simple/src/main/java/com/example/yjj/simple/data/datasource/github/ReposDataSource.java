package com.example.yjj.simple.data.datasource.github;

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
public class ReposDataSource extends BaseObservableDataSource<List<Repo>> {

    @Inject
    public ReposDataSource(DataFetcher<List<Repo>> dataFetcher) {
        super(dataFetcher);
    }

    /**
     *
     *
     * @param extra
     * @param values
     * @return
     */
    @Override
    public IParameter buildParameter(IParameter extra, String... values) {
        RequestParameter requestParameter = (RequestParameter) super.buildParameter(extra,values);
        requestParameter.put("user", values[0]);
        return requestParameter;
    }

    public static class ReposFetcher extends GitHubDataFetcher<List<Repo>> {

        @Inject
        public ReposFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public List<Repo> fetchData(RequestParameter parameter) throws Exception {
            call = gitHubApi.listRepos(parameter.get("user"));
            List<Repo> result = call.execute().body();
            return result;
        }
    }
}
