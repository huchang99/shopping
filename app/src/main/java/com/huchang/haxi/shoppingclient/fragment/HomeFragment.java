package com.huchang.haxi.shoppingclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huchang.haxi.shoppingclient.CityActivity;
import com.huchang.haxi.shoppingclient.R;
import com.huchang.haxi.shoppingclient.WelcomeGuideAct;
import com.huchang.haxi.shoppingclient.myclass.FindAddress;
import com.huchang.haxi.shoppingclient.myinterface.CityAddressShow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.huchang.haxi.shoppingclient.utils.MyUtils.CityActivityResultCode;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.REQUESTCODE;

public class HomeFragment extends Fragment implements FindAddress.ListenGetAddrValue {

    private static final String TAG = "HomeFragment" ;

    @BindView(R.id.index_top_city)
    TextView index_top_city_tv;
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
        return  view;
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
