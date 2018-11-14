package com.huchang.haxi.shoppingclient.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huchang.haxi.shoppingclient.R;

public class SearchFragment extends Fragment {

    //单例模式
    public static SearchFragment newInstance(){
        SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.searchfragment,container,false);
    }
}
