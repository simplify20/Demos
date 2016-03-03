package com.example.yjj.dagger_mvp.util;

import android.widget.Toast;

import com.example.yjj.dagger_mvp.DaggerApplication;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class ToastUtil {

    public static void toastShortMsg(String msg) {
        Toast.makeText(DaggerApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
