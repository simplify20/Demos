package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.BaseDaggerDataSource;
import com.example.yjj.simple.data.di.github.component.DaggerRepoProducerComponent;
import com.example.yjj.simple.data.di.github.producer.GitHubProducer;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.ApiConstants;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.ParameterFactory;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoDaggerDataSource extends BaseDaggerDataSource<List<Repo>> {

    @Inject
    public RepoDaggerDataSource(@Named(ApiConstants.SCHEDULE_EXECUTOR_SINGLE_THREAD) Executor executor) {
        super(executor, null);
    }

    @Override
    public ListenableFuture<List<Repo>> getData(IParameter extra, String... values) {
        return DaggerRepoProducerComponent.builder()
                .gitHubProducer(new GitHubProducer(RepoParameterFactoryImpl.INSTANCE.create(extra, values)))
                .executor(executor)
                .build()
                .getRepo();
    }

    public enum RepoParameterFactoryImpl implements ParameterFactory {
        INSTANCE;

        @Override
        public IParameter create(IParameter extra, String... values) {
            RequestParameter requestParameter;
            if (extra != null)
                requestParameter = (RequestParameter) extra;
            else
                requestParameter = new RequestParameter();
            if (values != null && values.length > 0)
                requestParameter.put("user", values[0]);
            return requestParameter;
        }
    }
}
