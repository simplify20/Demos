package com.example.yjj.simple.data.datasource.github;

import android.support.annotation.NonNull;

import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataFetcher;
import com.example.yjj.simple.framework.datasource.impl.MultiStrategyDataSource;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public class RepoMultiDataSource extends MultiStrategyDataSource<List<Repo>,List<Repo>> {
    public RepoMultiDataSource(DataFetcher<List<Repo>>... dataFetchers) {
        super(dataFetchers);
    }

    @Override
    public List<Repo> convert(List<Repo> input) {
        return null;
    }

    public static class FileSystemDataFetcher extends BaseDataFetcher<Future<List<Repo>>>{

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values) {
            return null;
        }

        @Override
        public Future<List<Repo>> fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception {
            return null;
        }

        @Override
        public void close() {

        }
    }
}
