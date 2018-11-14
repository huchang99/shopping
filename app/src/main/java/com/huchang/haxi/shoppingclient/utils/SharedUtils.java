package com.huchang.haxi.shoppingclient.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtils {
    private static final String FILE_NAME = "dianping";
    private static final String MODE_NAME = "welcome";

    //读取标志位
    public static boolean getWelcomeBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME,false);
    }
    //写入标志位
    public static void putWelcomeBoolean(Context context,boolean isFisrt){
         Editor  editor= context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
         editor.putBoolean(MODE_NAME,isFisrt);
         editor.commit();
    }
}
