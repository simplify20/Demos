package com.example.yjj.demoproj.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author:YJJ
 * @date:2016/3/4
 * @email:yangjianjun@117go.com
 */
public class PostThread extends Thread {
    private String data;

    public PostThread(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        EventBus.getDefault().post(new TestEvent(data));
    }
}
