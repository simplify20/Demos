package com.example.yjj.simple;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class SimleApplication extends Application {

    private static Context context;
    private static Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initPicasso();
    }

    private void initPicasso() {
        picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(BuildConfig.DEBUG);
        picasso.setLoggingEnabled(BuildConfig.DEBUG);
    }

    public static Context getContext() {
        return context;
    }

    public static Picasso getPicasso() {
        return picasso;
    }
}
