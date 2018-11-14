package com.huchang.haxi.shoppingclient;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.huchang.haxi.shoppingclient.utils.SharedUtils;

public class launcher_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedUtils.getWelcomeBoolean(getBaseContext())){
                    startActivity(new Intent(launcher_Activity.this,WelcomeGuideAct.class));
                }else{
                    startActivity(new Intent(launcher_Activity.this,MainActivity.class));
                    SharedUtils.putWelcomeBoolean(getBaseContext(),true);
                }
                finish();
            }
        },3000);
    }
}
