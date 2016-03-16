package com.example.yjj.simple.data.datasource.github;

import android.support.annotation.NonNull;

import com.example.yjj.simple.data.datasource.BaseDaggerMultiDataSource;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public class TestRepoDataSource extends BaseDaggerMultiDataSource<List<Repo>, List<Repo>> {
    public TestRepoDataSource(DataFetcher<ListenableFuture<List<Repo>>>... dataFetchers) {
        super(dataFetchers);
    }

    @Override
    public List<Repo> convert(List<Repo> input) {
        return input;
    }

    public static class TestFileSystemFetcher extends BaseDataFetcher<Future<List<Repo>>> {
        private Executor executor;

        @Inject
        public TestFileSystemFetcher(@Named(QualifierConstants.PROVIDE_SINGLE_THREAD_EXECUTOR) Executor executor) {
            this.executor = executor;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            extra.put("user", values[0]);
            return extra;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
//            return Producers.submitToExecutor(new Callable<List<Repo>>() {
//                @Override
//                public List<Repo> call() throws Exception {
//                    Thread.sleep(1000);
//                    List<Repo> result = new ArrayList();
//                    for (int i = 0; i < 10; i++) {
//                        Repo repo = new Repo();
//                        repo.name = "test" + i;
//                        repo.stars = i + 20;
//                        result.add(repo);
//                    }
//                    return result;
//                }
//            }, executor);
            return null;
        }

        @Override
        public void close() {

        }
    }
}
