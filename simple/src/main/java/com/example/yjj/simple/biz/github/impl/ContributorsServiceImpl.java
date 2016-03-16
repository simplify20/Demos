package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.github.ContributorsService;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;

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
    public ContributorsServiceImpl(@Named(QualifierConstants.PROVIDE_CONTRIBUTORS_REPOSITORY) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getContributors(String owner, String repo, DataCallback callback) {
        repository.setCallback(callback);
        repository.getData(null, owner, repo);
    }

}

