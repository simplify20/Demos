package com.example.yjj.demoproj.eventbus;

import android.os.Bundle;

import com.example.yjj.demoproj.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author:YJJ
 * @date:2016/3/4
 * @email:yangjianjun@117go.com
 */
public class BusActivity extends BaseActivity {
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
        eventBus.register(this);


    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void async(TestEvent event) {
        System.out.println("async()--" + Thread.currentThread().getName() + event.postThreadInfo);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void back(TestEvent event) {
        System.out.println("back()--" + Thread.currentThread().getName() + event.postThreadInfo);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void posting(TestEvent event) {
        System.out.println("posting()--" + Thread.currentThread().getName() + event.postThreadInfo);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void main(TestEvent event) {
        System.out.println("main()--" + Thread.currentThread().getName() + event.postThreadInfo);
    }
}
