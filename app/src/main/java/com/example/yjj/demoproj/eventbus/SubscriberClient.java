package com.example.yjj.demoproj.eventbus;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.CountDownLatch;

/**
 * @author:YJJ
 * @date:2016/3/4
 * @email:yangjianjun@117go.com
 */
public class SubscriberClient {
    private EventBus eventBus;
    private volatile CountDownLatch countDownLatch;

    public SubscriberClient(int threadNum) {
        this.eventBus = EventBus.getDefault();
        countDownLatch = new CountDownLatch(threadNum);
    }

    public void onStart() {
        eventBus.register(this);
    }

    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC,priority = 4)
    public void handleEventAsync(TestEvent event) {
        System.out.println("Async---"+Thread.currentThread().toString() +"---handleEvent:" + event.postThreadInfo);
        countDownLatch.countDown();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND,priority = 3)
    public void handleEventBackground(TestEvent event) {
        System.out.println("Background---"+Thread.currentThread().toString() + "---handleEvent:" + event.postThreadInfo);
        countDownLatch.countDown();
    }
    @Subscribe(threadMode = ThreadMode.POSTING,priority = 2)
    public void handleEventPosting(TestEvent event) {
        System.out.println("Posting---"+Thread.currentThread().toString() + "---handleEvent:" + event.postThreadInfo);
        countDownLatch.countDown();
    }


    public void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
