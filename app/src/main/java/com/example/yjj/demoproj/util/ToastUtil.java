package com.example.yjj.demoproj.util;

import android.widget.Toast;

import com.example.yjj.demoproj.TestApplication;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class ToastUtil {

    public static void toastShortMsg(String msg) {
        Toast.makeText(TestApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
