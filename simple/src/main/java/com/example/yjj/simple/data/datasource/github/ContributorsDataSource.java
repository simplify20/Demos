package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.BaseObservableDataSource;
import com.example.yjj.simple.data.entity.github.Contributor;
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
public class ContributorsDataSource extends BaseObservableDataSource<List<Contributor>> {

    @Inject
    public ContributorsDataSource(DataFetcher<List<Contributor>> dataFetcher) {
        super(dataFetcher);
    }

    /**
     * 主要的工作量
     *
     * @param extra
     * @param values
     * @return
     */
    @Override
    public IParameter buildParameter(IParameter extra, String... values) {
        RequestParameter requestParameter = (RequestParameter) super.buildParameter(extra, values);
        requestParameter.put("owner", values[0]);
        requestParameter.put("repo", values[1]);
        return requestParameter;
    }

    public static class ContributorFetcher extends GitHubDataFetcher<List<Contributor>> {

        @Inject
        public ContributorFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public List<Contributor> fetchData(RequestParameter parameter) throws Exception {
            call = gitHubApi.contributors(parameter.get("owner"), parameter.get("repo"));
            List<Contributor> result = call.execute().body();
            return result;
        }
    }
}
