package com.example.yjj.simple.data.di.github.producer;

import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.IParameter;

import java.util.List;

import javax.inject.Named;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@ProducerModule
public class GitHubProducer {
    public static final String API_URL = "https://api.github.com";
    private String baseUrl = API_URL;
    private IParameter<String, String> parameter;

    public GitHubProducer(IParameter<String, String> parameter) {
        this.parameter = parameter;
    }

    public GitHubProducer(String baseUrl, IParameter<String, String> parameter) {
        this.baseUrl = baseUrl;
        this.parameter = parameter;
    }

    @Produces
    @Named("github")
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Produces
    GitHubApi gitHubApi(@Named("github") Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    @Produces
    List<Repo> getRepos(GitHubApi api) throws Exception {
        Call<List<Repo>> call = api.listRepos(parameter.get("user"));
        List<Repo> result = call.execute().body();
        return result;
    }

//    @Produces
//    ListenableFuture<List<Repo>> listListenableFuture(@Named(ApiConstants.ACTION_GET_REPOS) final DataFetcher<List<Repo>> dataFetcher) {
//        try {
//            return Futures.immediateFuture(dataFetcher.fetchData(parameter));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<Repo> empty = new ArrayList();
//        return Futures.immediateFuture(empty);
//    }

//    @Produces
//    DataFetcher<ListenableFuture<List<Repo>>> listenFecter(@Named(ApiConstants.ACTION_GET_REPOS) final DataFetcher<List<Repo>> dataFetcher) {
//        return new DataFetcher<ListenableFuture<List<Repo>>>() {
//            @Override
//            public ListenableFuture<List<Repo>> fetchData(RequestParameter parameter) throws Exception {
//                return Futures.immediateFuture(dataFetcher.fetchData(parameter));
//            }
//
//            @Override
//            public void close() {
//            }
//        };
//    }


}
