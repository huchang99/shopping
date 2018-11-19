package com.huchang.haxi.shoppingclient.utils;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

public class ScreenUtil {

    static double scale;
    static int screenWidth = 0, screenHeight = 0;

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void init(Context context) {
        scale = context.getResources().getDisplayMetrics().density;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        LogUtils.d(screenWidth);
        LogUtils.d(screenHeight);
    }

}
