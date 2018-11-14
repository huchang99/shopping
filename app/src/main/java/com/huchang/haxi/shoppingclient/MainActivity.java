package com.huchang.haxi.shoppingclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.huchang.haxi.shoppingclient.myinterface.CityAddressShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.startpagebt)
    Button startPage_bt;
    @BindView(R.id.welcome_pager)ViewPager welcome_papertv;

    private List<View> listpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化ViewPager
        initViewPaper();
    }



    @OnClick(R.id.startpagebt)
    public void StartPage_bt(View view){
        switch(view.getId()){
            case R.id.startpagebt:{
                startActivity(new Intent(MainActivity.this,WelcomeGuideAct.class));
                MainActivity.this.finish();
                break;
            }
        }
    }
    private void initViewPaper(){
        listpaper = new ArrayList<View>();
        LayoutInflater inflater1 = getLayoutInflater();
        View v1 = inflater1.inflate(R.layout.guide01,null);
        LayoutInflater inflater2 = getLayoutInflater();
        View v2 = inflater2.inflate(R.layout.guide02,null);
        LayoutInflater inflater3 = getLayoutInflater();
        View v3 = inflater3.inflate(R.layout.guide03,null);

        listpaper.add(v1);
        listpaper.add(v2);
        listpaper.add(v3);

        //设置viewpaper的适配器
        welcome_papertv.setAdapter(new welcomePaperAdapter());
        //设置viewpaper的滑动监听
        welcome_papertv.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
              if(i == 2)
              { startPage_bt.setVisibility(View.VISIBLE);
              }else {
                  startPage_bt.setVisibility(View.GONE);
              }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
     class welcomePaperAdapter extends PagerAdapter{
         @Override
         public int getCount() {
             return listpaper.size();
         }
         @Override
         public boolean isViewFromObject(@NonNull View arg1, @NonNull Object arg2) {
             return arg1 == arg2;
         }
         @Override
         public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
             //super.destroyItem(container, position, object);
             container.removeView(listpaper.get(position));
         }

         @NonNull
         @Override
         public Object instantiateItem(@NonNull ViewGroup container, int position) {
             container.addView(listpaper.get(position));
             return listpaper.get(position);
         }
     }



}
