package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.github.RepoService;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class RepoServiceImpl implements RepoService {
    private BaseRepository repository;

    @Inject
    public RepoServiceImpl(@Named(QualifierConstants.PROVIDE_REPO_REPOSITORY_DAGGER) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getRepos(String owner, DataCallback repoListCallback) {

        RequestParameter extraParams = RequestParameter.newActionParameter(QualifierConstants.PROVIDE_REPO_REPOSITORY);
        repository.setCallback(repoListCallback);
        repository.getData(extraParams, owner);
    }
}
