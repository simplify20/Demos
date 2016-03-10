package com.example.yjj.simple.biz.github.impl;

import com.example.yjj.simple.framework.datasource.DataSource;
import com.example.yjj.simple.biz.BaseObservableRepository;
import com.example.yjj.simple.data.entity.github.Contributor;

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
public class ContributorsRepository extends BaseObservableRepository<List<Contributor>> {

    @Inject
    public ContributorsRepository(@Named("post") Scheduler postScheduler, @Named("work") Scheduler workScheduler, DataSource<Observable<List<Contributor>>> dataSource) {
        super(postScheduler, workScheduler, dataSource);
    }
}
