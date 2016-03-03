package com.example.yjj.demoproj.dagger2.producers;

import com.google.common.util.concurrent.ListenableFuture;

import dagger.producers.ProductionComponent;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@ProductionComponent(modules = UserResponseModule.class)
public interface UserProducerComponent {
    ListenableFuture<String> userInfo();
}
