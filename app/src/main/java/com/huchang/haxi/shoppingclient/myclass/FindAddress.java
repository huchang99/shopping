package com.huchang.haxi.shoppingclient.myclass;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.huchang.haxi.shoppingclient.utils.LocationUtils;

public class FindAddress {
    private Context context;
    public ListenGetAddrValue mListenGetAddrValue;

    public String getAddr_result() {
        return addr_result;
    }

    //地址结果
    private String addr_result;
    //addr flag

    //声名AMapLocationClient对象
    public AMapLocationClient mapLocationClient = null;
    //声明定位回调监听器
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    public FindAddress(Context context,ListenGetAddrValue mListenGetAddrValue) {
        this.context = context;
        this.mListenGetAddrValue = mListenGetAddrValue;
    }

    public void initAmapLocationData() {
        mapLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        //设置定位参数
        mapLocationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        mapLocationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author huchang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }
    //监听函数
    //public AMapLocationListener mapLocationListener = new AMapLocationListener();
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if(aMapLocation != null)
            {
                //解析定位结果
                String result = LocationUtils.getLocationStr(aMapLocation);
                Log.d("Location is ",result);
                addr_result = aMapLocation.getCity();
                Log.d("++++addr_result",addr_result);
                // send fragment value
                mListenGetAddrValue.getAddrvalue(addr_result);
            }
            else{
                Log.d("Location is ","error");
            }
        }
    };

    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void startLocation(){
        // 设置定位参数
        mapLocationClient.setLocationOption(locationOption);
        // 启动定位
        mapLocationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void stopLocation(){
        // 停止定位
        mapLocationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void destroyLocation(){
        if (null != mapLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mapLocationClient.onDestroy();
            mapLocationClient = null;
            locationOption = null;
        }
    }

    public interface ListenGetAddrValue {
        void getAddrvalue(String addr);
    }

}
