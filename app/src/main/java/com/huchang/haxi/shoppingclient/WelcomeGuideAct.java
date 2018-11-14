package com.huchang.haxi.shoppingclient;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.huchang.haxi.shoppingclient.fragment.HomeFragment;
import com.huchang.haxi.shoppingclient.fragment.MyFragment;
import com.huchang.haxi.shoppingclient.fragment.SearchFragment;
import com.huchang.haxi.shoppingclient.fragment.TuanFragment;
import com.huchang.haxi.shoppingclient.myclass.FindAddress;
import com.huchang.haxi.shoppingclient.myinterface.CityAddressShow;
import com.huchang.haxi.shoppingclient.utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeGuideAct extends FragmentActivity{
    private static final  String TAG = "WelcomeGuideAct";

    @BindView(R.id.main_home)
    RadioButton main_home_bt;
    @BindView(R.id.main_tuan)
    RadioButton main_tuan_bt;
    @BindView(R.id.main_search)
    RadioButton main_search_bt;
    @BindView(R.id.main_my)
    RadioButton main_my_bt;
    @BindView(R.id.main_bottom_tabs)
    RadioGroup main_bottom_tabs_bt;

    //fragment
    private List<Fragment> mainFragments = new ArrayList<>();
    private Fragment mainfragment;
    private FragmentManager main_fm;
    private FragmentTransaction main_transaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
         //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_guide);
        ButterKnife.bind(this);
        mainFragments = get_main_fragments();
        //set initial fragment
        init_defaultFragment(0);
        //set initial radio button
        init_views();

//        FindAddress  findAddress = new FindAddress(this);
//        findAddress.initAmapLocationData();
//        findAddress.startLocation();
//        String addr = findAddress.getAddr_result();
//        if(addr!=null&&addr.length()>0)
//            Log.d("TAG",addr);
        //findAddress.stopLocation();

        main_bottom_tabs_bt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.main_home:{
                        init_defaultFragment(0);
                        break;
                    }
                    case R.id.main_tuan:{
                        init_defaultFragment(1);
                        break;
                    }
                    case R.id.main_search:{
                        init_defaultFragment(2);
                        break;
                    }
                    case R.id.main_my:{
                        init_defaultFragment(3);
                        break;
                    }
                }
            }
        });
    }

    public List<Fragment> get_main_fragments() {
        mainFragments.add(HomeFragment.newInstance());
        mainFragments.add(TuanFragment.newInstance());
        mainFragments.add(SearchFragment.newInstance());
        mainFragments.add(MyFragment.newInstance());
        return mainFragments;
    }

    private void init_defaultFragment(int position)
    {
        main_fm = getSupportFragmentManager();
        main_transaction = main_fm.beginTransaction();
            switch (position)
            {
                case 0:{mainfragment = mainFragments.get(0);break;}
                case 1:{mainfragment = mainFragments.get(1);break;}
                case 2:{mainfragment = mainFragments.get(2);break;}
                case 3:{mainfragment = mainFragments.get(3);break;}
            }
            main_transaction.replace(R.id.main_fragment, mainfragment).commit();

    }

    private void init_views()
    {
        main_home_bt.setChecked(true);
        // get fragment 控件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // destroyLocation();
    }



}
