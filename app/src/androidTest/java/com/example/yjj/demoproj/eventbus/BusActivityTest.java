package com.example.yjj.demoproj.eventbus;

import android.test.ActivityInstrumentationTestCase2;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.CountDownLatch;

/**
 * @author:YJJ
 * @date:2016/3/4
 * @email:yangjianjun@117go.com
 */
public class BusActivityTest extends ActivityInstrumentationTestCase2<BusActivity> {
    BusActivity activity;

    public BusActivityTest() {
        super(BusActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testBus() throws Exception {
        System.out.println("post thread:" + Thread.currentThread().getName());
        EventBus.getDefault().post(new TestEvent(""));
    }

    public void testBus1() throws Exception {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("post thread:" + Thread.currentThread().getName());
                EventBus.getDefault().post(new TestEvent(""));
            }
        });
    }

    public void testBus2() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("post thread:" + Thread.currentThread().getName());
                EventBus.getDefault().post(new TestEvent(""));
                countDownLatch.countDown();
            }
        }).start();
        countDownLatch.await();
    }

}
