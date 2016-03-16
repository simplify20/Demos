package com.example.yjj.simple.framework.util;

import android.app.Activity;

import com.example.yjj.simple.SimpleApplication;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.Repository;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public abstract class RepositoryHook {

    public void onRepositoryConstruct(Repository repository) {
        Activity topActivity = SimpleApplication.topActivity;
        Class viewClass;
        if (topActivity == null) {
            viewClass = SimpleApplication.class;
        } else {
            viewClass = topActivity.getClass();
        }
        SimpleApplication.getRepositoryManager().register(viewClass, repository);
    }

    public void onRepositoryClose(Repository repository) {

    }

    public void onRepositoryGetData(Repository repository, IParameter parameter, String... values) {

    }

    public void onRepositorySetCallback(Repository repository, DataCallback callback) {

    }


}
