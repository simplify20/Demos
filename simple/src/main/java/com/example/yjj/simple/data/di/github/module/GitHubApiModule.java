package com.example.yjj.simple.data.di.github.module;

import com.example.yjj.simple.biz.github.RepoService;
import com.example.yjj.simple.biz.github.impl.ContributorsRepository;
import com.example.yjj.simple.biz.github.impl.RepoServiceImpl;
import com.example.yjj.simple.biz.github.impl.ReposRepository;
import com.example.yjj.simple.data.datasource.github.ContributorsDataSource;
import com.example.yjj.simple.data.datasource.github.ReposDataSource;
import com.example.yjj.simple.data.di.common.module.ScheduleModule;
import com.example.yjj.simple.data.entity.github.Contributor;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.ApiConstants;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.DataSource;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@Module(includes = ScheduleModule.class)
public class GitHubApiModule {
    public static final String API_URL = "https://api.github.com";
    private String baseUrl = API_URL;

    public GitHubApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public GitHubApiModule() {
    }

    /***************************API*********************************************************************************/

    @Provides
    @Named("github")
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    GitHubApi gitHubApi(@Named("github") Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }

    /**************************************DataFetcher*********************************************************************************/

    @Provides
    @Named(ApiConstants.ACTION_GET_CONTRIBUTORS)
    DataFetcher<List<Contributor>> contributionsDataFetcher(ContributorsDataSource.ContributorFetcher contributorFetcher) {
        return contributorFetcher;
    }

    @Provides
    @Named(ApiConstants.ACTION_GET_REPOS)
    DataFetcher<List<Repo>> reposDataFetcher(ReposDataSource.ReposFetcher reposFetcher) {
        return reposFetcher;
    }

    /***************************************DataSource*********************************************************************************/

    @Provides
    DataSource<Observable<List<Contributor>>> contributorDataSource(@Named(ApiConstants.ACTION_GET_CONTRIBUTORS) DataFetcher<List<Contributor>> dataFetcher) {
        return new ContributorsDataSource(dataFetcher);
    }

    @Provides
    DataSource<Observable<List<Repo>>> repoDataSource(@Named(ApiConstants.ACTION_GET_REPOS) DataFetcher<List<Repo>> dataFetcher) {
        return new ReposDataSource(dataFetcher);
    }

    /**************************************Repository*********************************************************************************/

    @Provides
    @Named(ApiConstants.ACTION_GET_CONTRIBUTORS)
    BaseRepository contributorRepository(ContributorsRepository repository) {
        return repository;
    }

    @Provides
    @Named(ApiConstants.ACTION_GET_REPOS)
    BaseRepository reposRepository(ReposRepository repository) {
        return repository;
    }

    /*************************************Service*********************************************************************************/

    @Provides
    RepoService repoService(RepoServiceImpl repoService) {
        return repoService;
    }
}
