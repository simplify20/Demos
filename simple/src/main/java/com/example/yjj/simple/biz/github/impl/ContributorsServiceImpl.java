package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.data.web.api.ApiConstants;
import com.example.yjj.simple.biz.github.ContributorsService;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class ContributorsServiceImpl implements ContributorsService {
    private BaseRepository repository;

    @Inject
    public ContributorsServiceImpl(@Named(ApiConstants.ACTION_GET_CONTRIBUTORS) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getContributors(String owner, String repo, DataCallback callback) {
        RequestParameter extraParams = RequestParameter.newActionParameter(ApiConstants.ACTION_GET_CONTRIBUTORS);
        repository.setCallback(callback);
        repository.getData(extraParams, owner, repo);
    }

}

