package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.BaseDaggerDataSource;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoDaggerDataSource extends BaseDaggerDataSource<List<Repo>> {
    public RepoDaggerDataSource(DataFetcher<ListenableFuture<List<Repo>>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public IParameter buildParameter(IParameter extra, String... values) {
        RequestParameter requestParameter = (RequestParameter) super.buildParameter(extra, values);
        requestParameter.put("user", values[0]);
        return requestParameter;
    }

    public static class RepoDaggerFetcher extends GitHubDataFetcher<ListenableFuture<List<Repo>>> {

        @Inject
        public RepoDaggerFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public ListenableFuture<List<Repo>> fetchData(RequestParameter parameter) throws Exception {
            return null;
        }
    }
}
