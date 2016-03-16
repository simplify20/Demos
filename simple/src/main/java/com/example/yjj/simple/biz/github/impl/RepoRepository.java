package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.BaseObservableRepository;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.data.web.api.QualifierConstants;
import com.example.yjj.simple.framework.datasource.DataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class RepoRepository extends BaseObservableRepository<List<Repo>, List<Repo>> {

    @Inject
    public RepoRepository(@Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_RX)DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
}
