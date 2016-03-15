package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.biz.BaseObservableRepository;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.framework.datasource.DataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class ReposRepository extends BaseObservableRepository<List<Repo>, List<Repo>> {

    @Inject
    public ReposRepository(@Named("post") Scheduler postScheduler, @Named("work") Scheduler workScheduler, DataSource<Observable<List<Repo>>> dataSource) {
        super(postScheduler, workScheduler, dataSource);
    }

    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
}
