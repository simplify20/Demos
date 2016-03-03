package com.example.yjj.demoproj.jsbridge;

import android.webkit.JavascriptInterface;

import com.example.yjj.demoproj.util.ToastUtil;

/**
 * @author:YJJ
 * @date:2016/1/27
 * @email:yangjianjun@117go.com
 */
public class JSInterface {

    @JavascriptInterface
    public void showInfo(String info){
        ToastUtil.toastShortMsg(info);
    }
}
