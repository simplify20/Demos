package com.example.yjj.simple.data.di.github.module;

import com.example.yjj.simple.biz.github.ContributorsService;
import com.example.yjj.simple.biz.github.impl.ContributorsRepository;
import com.example.yjj.simple.biz.github.impl.ContributorsServiceImpl;
import com.example.yjj.simple.data.datasource.github.ContributorsDataSource;
import com.example.yjj.simple.data.di.common.ActivityScope;
import com.example.yjj.simple.data.entity.github.Contributor;
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
@Module(includes = {GitHubApiModule.class})
public class ContributorsModule {
    /**************************************
     * DataFetcher
     **************************************/
    @ActivityScope
    @Provides
    DataFetcher<List<Contributor>> contributionsDataFetcher(ContributorsDataSource.ContributorFetcher contributorFetcher) {
        return contributorFetcher;
    }

    /***************************************
     * DataSource
     ***************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_CONTRIBUTOR_DATA_SOURCE_RX)
    DataSource contributorDataSource(DataFetcher<List<Contributor>> dataFetcher) {
        return new ContributorsDataSource(dataFetcher);
    }

    /**************************************
     * Repository
     **************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_CONTRIBUTORS_REPOSITORY)
    BaseRepository contributorRepository(ContributorsRepository repository) {
        return repository;
    }


    /*************************************
     * Service
     *************************************/
    @ActivityScope
    @Provides
    ContributorsService contributorsService(ContributorsServiceImpl contributorsService) {
        return contributorsService;
    }

}
