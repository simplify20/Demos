package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.BaseDaggerRepository;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.framework.datasource.DataSource;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class DaggerRepoRepository extends BaseDaggerRepository<List<Repo>,List<Repo>> {
    public DaggerRepoRepository(DataSource<ListenableFuture<List<Repo>>> dataSource) {
        super(dataSource);
    }

    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
}
