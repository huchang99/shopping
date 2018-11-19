package com.huchang.haxi.shoppingclient;

import android.app.Application;

import com.huchang.haxi.shoppingclient.utils.ScreenUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.init(this);
    }
}
