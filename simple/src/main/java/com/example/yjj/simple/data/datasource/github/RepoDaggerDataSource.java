package com.example.yjj.simple.data.datasource.github;

import android.support.annotation.NonNull;

import com.example.yjj.simple.data.datasource.BaseDaggerDataSource;
import com.example.yjj.simple.data.di.github.component.DaggerRepoProducerComponent;
import com.example.yjj.simple.data.di.github.producer.GitHubProducer;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoDaggerDataSource extends BaseDaggerDataSource<List<Repo>, List<Repo>> {

    @Inject
    public RepoDaggerDataSource(@Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER)DataFetcher dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public List<Repo> convert(List<Repo> input) {
        return input;
    }


    public static class DaggerRepoFetcher extends BaseDataFetcher<Future<List<Repo>>> {
        private Future<List<Repo>> cache;
        private GitHubProducer gitHubProducer;
        private Executor executor;

        @Inject
        public DaggerRepoFetcher(GitHubProducer gitHubProducer, @Named(QualifierConstants.PROVIDE_SINGLE_THREAD_EXECUTOR) Executor executor) {
            this.executor = executor;
            this.gitHubProducer = gitHubProducer;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            extra.put("user", values[0]);
            return extra;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
            gitHubProducer.setParameter(requestParameter);
            Future<List<Repo>> result = DaggerRepoProducerComponent
                    .builder()
                    .gitHubProducer(gitHubProducer)
                    .executor(executor)
                    .build()
                    .getRepo();
            if (result != null)
                cache = result;
            return cache;
        }

        @Override
        public void close() {
            if (cache != null) {
                cache.cancel(true);
            }
        }
    }
}
