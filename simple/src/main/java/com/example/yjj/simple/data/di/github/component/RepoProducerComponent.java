package com.example.yjj.simple.data.di.github.component;

import com.example.yjj.simple.data.di.github.producer.GitHubProducer;
import com.example.yjj.simple.data.entity.github.Repo;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import dagger.producers.ProductionComponent;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
@ProductionComponent(modules = GitHubProducer.class)
public interface RepoProducerComponent {

    ListenableFuture<List<Repo>> getRepo();
}
