package com.huchang.haxi.shoppingclient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.huchang.haxi.shoppingclient.adapter.CityListAdapter;
import com.huchang.haxi.shoppingclient.encity.City;
import com.huchang.haxi.shoppingclient.encity.ResponseObject;
import com.huchang.haxi.shoppingclient.myview.Siderbar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.huchang.haxi.shoppingclient.utils.MyUtils.CityActivityResultCode;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.Host;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.CityActivityResultCode;
import static com.huchang.haxi.shoppingclient.utils.MyUtils.httpcity;

public class CityActivity extends Activity implements Siderbar.TouchSiderbatChangedListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CityActivity";

    @BindView(R.id.list_activity)
    ListView CityListView;
    @BindView(R.id.Siderbar)
    com.huchang.haxi.shoppingclient.myview.Siderbar Siderbarbt;
    @BindView(R.id.select_top_city)
    TextView select_top_city_tv;
    //准备数据
    private List<City> CityDatas;

    private updateCityAddressData mUpdateCityAddressData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        //数组初始化
        CityDatas = new ArrayList<City>();
        //初始化任务
        mUpdateCityAddressData = new updateCityAddressData();
        mUpdateCityAddressData.execute(Host + httpcity);
        Siderbarbt.SetOnTouchSiderbatChangedListener(this);
        CityListView.setOnItemClickListener(this);
        select_top_city_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void OnTouchSiderbatChange(String s) {
        CityListView.setSelection(findIndex(CityDatas, s));
    }

    private int findIndex(List<City> list, String s) {
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                if (s.equals(list.get(i).getSortKey())) {
                    return i;
                }
            }
        return -1;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = ((TextView)view.findViewById(R.id.city_list_item_name)).getText().toString();
        //构建值传回
        Intent intent = new Intent();
        intent.putExtra("CityAddr",text);
        setResult(CityActivityResultCode,intent);
        finish();

    }

    class updateCityAddressData extends AsyncTask<String, Integer, List<City>> {

        @Override
        protected List<City> doInBackground(String... url) {
            LogUtils.tag("updateCityAddressData").d(url[0]);
            // 从网络拉取数据
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    CityDatas = null;
                    LogUtils.d("error response");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String CityAddrString = response.body().string();
                    CityDatas = ParseJsonCityData(CityAddrString);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CityListView.setAdapter(new CityListAdapter(CityActivity.this, CityDatas));
                        }
                    });
                }
            });
            return CityDatas;
        }
    }

    //解析城市数据的json
    public List<City> ParseJsonCityData(String json) {

        List<City> CitydatasJson = new ArrayList<>();
        //拿到数组
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("datas");
        // LogUtils.d(jsonArray);
        for (JsonElement city : jsonArray) {
            City responseCity = new Gson().fromJson(city, new TypeToken<City>() {
            }.getType());
            CitydatasJson.add(responseCity);
        }
        // LogUtils.d(Citydatas);
        return CitydatasJson;
    }
}
