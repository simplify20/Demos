package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.producers.DaggerUserProducerComponent;
import com.example.yjj.demoproj.dagger2.producers.UserProducerComponent;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
public class ProducerTest {

    private UserProducerComponent userProducerComponent;

    private Executor executor;

    //for async test
    private CountDownLatch countDownLatch;

    @Before
    public void setUp() throws Exception {

        executor = Executors.newFixedThreadPool(2);
        userProducerComponent = DaggerUserProducerComponent.builder().executor(executor).build();
        countDownLatch = new CountDownLatch(1);

    }


    @Test
    public void testProducer() throws Exception {
        System.out.println(Thread.currentThread());
        final ListenableFuture<String> future = userProducerComponent.userInfo();
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                //thread is future thread
                System.out.println(Thread.currentThread());
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();

    }
}
