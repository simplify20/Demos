package com.example.yjj.rxdemo.util;

import android.widget.Toast;

import com.example.yjj.rxdemo.RxApplication;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class ToastUtil {

    public static void toastShortMsg(String msg) {
        Toast.makeText(RxApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
