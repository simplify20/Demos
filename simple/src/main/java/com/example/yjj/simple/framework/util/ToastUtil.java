package com.example.yjj.simple.framework.util;

import android.widget.Toast;

import com.example.yjj.simple.SimleApplication;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class ToastUtil {

    public static void toastShortMsg(String msg) {
        Toast.makeText(SimleApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
