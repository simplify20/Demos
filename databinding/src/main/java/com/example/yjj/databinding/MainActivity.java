package com.example.yjj.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.example.yjj.databinding.databinding.LayoutViewStubBinding;
import com.example.yjj.databinding.model.ObservableUser;
import com.example.yjj.databinding.model.User;
import com.example.yjj.databinding.vm.MainViewDataBinding;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MainViewDataBinding mainViewDataBinding;
    private ObservableUser observableUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewDataBinding = DataBindingUtil.setContentView(this, R.layout.layout_main);
        user = new User("steve", "jobs");
        mainViewDataBinding.setUser(user);
        mainViewDataBinding.setImgBack(getResources().getDrawable(R.drawable.breadtree));
        //view with id
        mainViewDataBinding.firstName.setTextColor(Color.RED);
        //observable bean
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final Random random = new Random();
        observableUser = new ObservableUser();
        mainViewDataBinding.setObservableUser(observableUser);
        //update in no-main thread
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //contrast with observable
                user.setFirstName(String.valueOf(random.nextInt(100)));
                observableUser.setUser(new User(String.valueOf(random.nextInt(100)), "ddd"));
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        //ViewStub binding
        mainViewDataBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                LayoutViewStubBinding layoutViewStubBinding = DataBindingUtil.bind(inflated);
                layoutViewStubBinding.setObservableUser(observableUser);
            }
        });
        //don't panic for red error reporting. Just ignore it and run the app. Surprise never ends.
        if (!mainViewDataBinding.viewStub.isInflated()) {
            mainViewDataBinding.viewStub.getViewStub().inflate();
        }


    }

    public void seeUsers(View v) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

}
