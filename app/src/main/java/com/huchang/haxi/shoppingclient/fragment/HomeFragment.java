package com.huchang.haxi.shoppingclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.huchang.haxi.shoppingclient.CityActivity;
import com.huchang.haxi.shoppingclient.R;
import com.huchang.haxi.shoppingclient.WelcomeGuideAct;
import com.huchang.haxi.shoppingclient.adapter.CagegoryViewPagerAdapter;
import com.huchang.haxi.shoppingclient.adapter.HomeMenuEnterAdapter;
import com.huchang.haxi.shoppingclient.myclass.FindAddress;
import com.huchang.haxi.shoppingclient.myclass.ModeHomeMenu;
import com.huchang.haxi.shoppingclient.myinterface.CityAddressShow;
import com.huchang.haxi.shoppingclient.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.huchang.haxi.shoppingclient.utils.MyUtils.CityActivityResultCode;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.REQUESTCODE;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.home_menu_iamges;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.home_menu_names;

public class HomeFragment extends Fragment implements FindAddress.ListenGetAddrValue {

    private static final String TAG = "HomeFragment" ;
    private static final int HOME_ENTRANCE_PAGE_SIZE = 10;
    private List<ModeHomeMenu>  home_menuMode_list;
    private LinearLayout homefragmentlayout;

    @BindView(R.id.index_top_city)
    TextView index_top_city_tv;
    @BindView(R.id.home_Entrace_vp)
    ViewPager home_menu_viewpager;



    private FindAddress findAddress;
    //单例模式
    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.homefragment,container,false);
        ButterKnife.bind(this,view);
        initData();
        init();
        return  view;
    }

    private void init() {
        //定义viewpager页面宽度
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)(ScreenUtil.getScreenWidth()/2));
        LogUtils.d((int)ScreenUtil.getScreenWidth()/2);
        home_menu_viewpager.setLayoutParams(layoutParams);
        //初始化recycleview
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        int pageSize = HOME_ENTRANCE_PAGE_SIZE;
        //总页数
        int pageCount = (int)Math.ceil(home_menuMode_list.size()*1.0/pageSize);
        List<View> viewList = new ArrayList<View>();
        for(int index = 0; index < pageCount; index++){
            //每个页面inflate一个新实例
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_home_enterace_vp,home_menu_viewpager,false);
            recyclerView.setLayoutParams(layoutParams);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
            //设置适配器
            recyclerView.setAdapter(new HomeMenuEnterAdapter(getActivity(),home_menuMode_list,index,pageSize));
            viewList.add(recyclerView);
        }
        //设置ViewPager的adapter
        home_menu_viewpager.setAdapter(new CagegoryViewPagerAdapter(viewList));


    }

    private void initData() {
        home_menuMode_list = new ArrayList<ModeHomeMenu>();
        for(int i=0;i<home_menu_names.length;i++) {
            home_menuMode_list.add(new ModeHomeMenu(home_menu_names[i], home_menu_iamges[i]));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //get address and display data
//        findAddress = new FindAddress(getActivity(),this);
//        findAddress.initAmapLocationData();
//        updateWithNewLocation();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //sava city
        //findAddress.stopLocation();
    }

    private void updateWithNewLocation(){
      findAddress.startLocation();
    }

    @Override
    public void getAddrvalue(String addr) {
        Log.d(TAG+"addr",addr);
        //更新fragment控件
        index_top_city_tv.setText(addr);
    }

    @OnClick(R.id.index_top_city) void button_top_city(){
        startActivityForResult( new Intent(getActivity(),CityActivity.class),REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == CityActivityResultCode )
        {
            if(requestCode == REQUESTCODE)
            {
                String addrData = data.getStringExtra("CityAddr");
                index_top_city_tv.setText(addrData);
            }
        }
    }
}
