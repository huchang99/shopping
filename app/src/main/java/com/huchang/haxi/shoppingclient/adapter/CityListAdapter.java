package com.huchang.haxi.shoppingclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huchang.haxi.shoppingclient.R;
import com.huchang.haxi.shoppingclient.encity.City;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends BaseAdapter {

    private Context mcontext;
    private List<City> CityDatas;

    //保存第一次保存城市的索引
   private StringBuffer buffer = new StringBuffer();
   private List<String> FirstCityNames = new ArrayList<>();

    public CityListAdapter(Context mcontext, List<City> cityDatas) {
        this.mcontext = mcontext;
        CityDatas = cityDatas;
    }

    @Override
    public int getCount() {
        return CityDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return CityDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder = null;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        if(convertView ==null)
        {
            convertView =inflater.inflate(R.layout.listitem_city,parent,false);
            holder = new ViewHolder();
            holder.city_sort = (TextView)convertView.findViewById(R.id.city_list_item_sort);
            holder.city_name = (TextView)convertView.findViewById(R.id.city_list_item_name);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
       //显示
        City mcity = CityDatas.get(position);
        String sort = mcity.getSortKey();
        String cityname = mcity.getName();
        if(buffer.indexOf(sort) == -1)
        {
            buffer.append(sort);
            FirstCityNames.add(cityname);
        }
        if(FirstCityNames.contains(cityname))
        {
            holder.city_sort.setText(sort);
            holder.city_sort.setVisibility(View.VISIBLE);
        }
        else{
            holder.city_sort.setVisibility(View.GONE);
        }
        holder.city_name.setText(cityname);
        return convertView;
    }
    class ViewHolder {
        TextView city_sort;
        TextView city_name;
    }
}
