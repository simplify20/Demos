package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.github.ReposService;
import com.example.yjj.simple.data.web.api.ApiConstants;
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
public class ReposServiceImpl implements ReposService {
    private BaseRepository repository;

    @Inject
    public ReposServiceImpl(@Named(ApiConstants.ACTION_GET_REPOS) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getRepos(String owner, DataCallback callback) {

        RequestParameter extraParams = RequestParameter.newActionParameter(ApiConstants.ACTION_GET_REPOS);
        repository.setCallback(callback);
        repository.getData(extraParams, owner);
    }
}
