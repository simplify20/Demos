package com.example.yjj.demoproj.util;

import android.util.DisplayMetrics;

import com.example.yjj.demoproj.TestApplication;

/**
 * @author:YJJ
 * @date:2016/1/20
 * @email:yangjianjun@117go.com
 */
public class DeviceUtils {

    public static DisplayMetrics getDisplay() {
        return TestApplication.getContext().getResources().getDisplayMetrics();
    }

    public static int pixelToSp(float pixelValue) {
        float scaledDensity = getDisplay().scaledDensity;
        int sp = (int) (pixelValue / scaledDensity + 0.5f);
        return sp;
    }

    public static int spToPixel(float spValue) {
        float scaledDensity = getDisplay().scaledDensity;
        int pixelValue = (int) (spValue * scaledDensity + 0.5f);
        return pixelValue;
    }
}
