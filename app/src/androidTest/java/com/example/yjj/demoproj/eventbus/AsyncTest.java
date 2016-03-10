package com.example.yjj.demoproj.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.support.test.runner.AndroidJUnit4;

import org.greenrobot.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;


/**
 * @author:YJJ
 * @date:2016/3/4
 * @email:yangjianjun@117go.com
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTest {
    private SubscriberClient subscriberClient;
    private Handler handler;

    @Ignore
    public void testPostAsync() throws Exception {
        for (int i = 0; i < 20; i++) {
            PostThread postThread = new PostThread(" data:[" + i + "]");
            postThread.start();
        }
        subscriberClient.await();
    }

    @Ignore
    public void testPostAsync1() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().toString());
            EventBus.getDefault().post(new TestEvent(" data:[" + i + "]"));
        }
        subscriberClient.await();
    }

    @Ignore
    public void testBackground() throws Exception {
        for (int i = 0; i < 100; i++) {
            final int index = i;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //UI线程post
                    System.out.println(Thread.currentThread().toString());
                    EventBus.getDefault().post(new TestEvent(" data:[" + index + "]"));
                }
            });
        }
    }

    @Before
    public void setUp() throws Exception {

        subscriberClient = new SubscriberClient(20);
        subscriberClient.onStart();
        handler = new Handler(Looper.getMainLooper());
    }

    @After
    public void tearDown() throws Exception {

        subscriberClient.onDestroy();

    }
}
