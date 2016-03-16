package com.example.yjj.simple.data.di.github.module;

import com.example.yjj.simple.biz.github.RepoService;
import com.example.yjj.simple.biz.github.impl.DaggerRepoRepository;
import com.example.yjj.simple.biz.github.impl.RepoRepository;
import com.example.yjj.simple.biz.github.impl.RepoServiceImpl;
import com.example.yjj.simple.data.datasource.github.RepoDaggerDataSource;
import com.example.yjj.simple.data.datasource.github.RepoDataSource;
import com.example.yjj.simple.data.datasource.github.TestRepoDataSource;
import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.di.common.module.ExecutorModule;
import com.example.yjj.simple.data.di.github.producer.GitHubProducer;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.GitHubApi;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.DataSource;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
@Module(includes = {ExecutorModule.class, GitHubApiModule.class})
public class RepoModule {
    /**************************************
     * DataFetcher
     **************************************/
    @ActivityScope
    @Provides
    DataFetcher<List<Repo>> reposDataFetcher(RepoDataSource.RepoFetcher reposFetcher) {
        return reposFetcher;
    }

    /***************************************
     * DataSource
     ***************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_RX)
    DataSource repoDataSource(DataFetcher<List<Repo>> dataFetcher) {
        return new RepoDataSource(dataFetcher);
    }

    /**************************************
     * Repository
     **************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_REPOSITORY)
    BaseRepository reposRepository(RepoRepository repository) {
        return repository;
    }

    /**************************************
     * Dagger test
     **************************************/
    @ActivityScope
    @Provides
    GitHubProducer gitHubProducer(GitHubApi gitHubApi) {
        return new GitHubProducer(gitHubApi);
    }

    @ActivityScope
    @Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER)
    @Provides
    DataFetcher daggerRepoFetcher(RepoDaggerDataSource.DaggerRepoFetcher daggerRepoFetcher) {

        return daggerRepoFetcher;
    }

    @ActivityScope
    @Named(QualifierConstants.PROVIDE_REPO_DATA_FILE_FETCHER_DAGGER)
    @Provides
    DataFetcher daggerRepoFileFetcher(TestRepoDataSource.TestFileSystemFetcher daggerRepoFetcher) {
        return daggerRepoFetcher;
    }

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_GUAVA)
    DataSource daggerRepoDataSource(@Named(QualifierConstants.PROVIDE_REPO_DATA_FILE_FETCHER_DAGGER) DataFetcher fileDataFetcher, @Named(QualifierConstants.PROVIDE_REPO_DATA_FETCHER_DAGGER) DataFetcher netWorkFetcher) {
        return new TestRepoDataSource(fileDataFetcher,netWorkFetcher);
    }

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_REPOSITORY_DAGGER)
    BaseRepository daggerRepository(DaggerRepoRepository daggerRepoRepository) {
        return daggerRepoRepository;
    }


    /*************************************
     * Service
     *************************************/
    @ActivityScope
    @Provides
    RepoService repoService(RepoServiceImpl repoService) {
        return repoService;
    }


}
