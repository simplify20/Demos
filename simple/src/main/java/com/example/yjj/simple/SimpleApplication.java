package com.example.yjj.simple;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.appcompat.BuildConfig;

import com.example.yjj.simple.data.di.common.component.ApplicationComponent;
import com.example.yjj.simple.data.di.common.component.DaggerApplicationComponent;
import com.example.yjj.simple.data.di.common.module.ApplicationModule;
import com.example.yjj.simple.framework.repository.impl.RepositoryManager;
import com.example.yjj.simple.framework.util.Logger;
import com.squareup.picasso.Picasso;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class SimpleApplication extends Application {
    public static Activity topActivity;

    private static Picasso picasso;
    private static ApplicationComponent applicationComponent;
    private static RepositoryManager repositoryManager;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityCallbacks());
        }
        initPicasso();
    }


    public static Context getContext() {
        if (context == null) {
            context = applicationComponent.getContext();
        }
        return context;
    }

    public static RepositoryManager getRepositoryManager() {
        if (repositoryManager == null) {
            repositoryManager = applicationComponent.getRepositoryManager();
        }
        return repositoryManager;
    }

    public static Picasso getPicasso() {
        return picasso;
    }

    private void initPicasso() {
        picasso = Picasso.with(this);
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG);
        picasso.setLoggingEnabled(BuildConfig.DEBUG);
    }

    private static class ActivityCallbacks implements ActivityLifecycleCallbacks {
        private static String TAG = "ActivityLifecycle";

        @Override
        public void onActivityResumed(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Resumed");
            topActivity = activity;
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Stopped");

        }

        @Override
        public void onActivityStarted(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Started");
            topActivity = activity;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Logger.i(TAG, activity.getLocalClassName() + ":SaveInstanceState");
        }


        @Override
        public void onActivityPaused(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Paused");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Logger.i(TAG, activity.getLocalClassName() + ":Destroyed");
            SimpleApplication.getRepositoryManager().unRegister(activity.getClass());
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Logger.i(TAG, activity.getLocalClassName() + ":Created");
            topActivity = activity;
        }
    }
}
