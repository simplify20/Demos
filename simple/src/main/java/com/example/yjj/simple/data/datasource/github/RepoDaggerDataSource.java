package com.example.yjj.simple.data.datasource.github;

import com.example.yjj.simple.data.datasource.BaseDaggerDataSource;
import com.example.yjj.simple.data.di.github.component.DaggerRepoProducerComponent;
import com.example.yjj.simple.data.di.github.producer.GitHubProducer;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoDaggerDataSource extends BaseDaggerDataSource<List<Repo>> {


    public RepoDaggerDataSource() {
        super(null);
    }

    @Override
    public IParameter buildParameter(IParameter extra, String... values) {
        RequestParameter requestParameter = (RequestParameter) super.buildParameter(extra, values);
        requestParameter.put("user", values[0]);
        return requestParameter;
    }

    @Override
    public ListenableFuture<List<Repo>> getData(IParameter parameter) {
        return DaggerRepoProducerComponent.builder()
                .gitHubProducer(new GitHubProducer(parameter))
                .executor(executor)
                .build()
                .getRepo();
    }
}
