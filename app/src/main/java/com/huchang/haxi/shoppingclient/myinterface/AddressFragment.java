package com.huchang.haxi.shoppingclient.myinterface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddressFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() !=null){
            getView().setOnClickListener(mAddressClickedListener);
        }
    }

    private View.OnClickListener mAddressClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonClicked();
        }
    };

    private void buttonClicked(){
       if(getActivity() instanceof  IAddressClickListener){
           IAddressClickListener listener  = (IAddressClickListener)getActivity();
           IAddressClickListener.AddressCallBack activityCallBack = listener.onAddressClicked("this is AddressFragment");
           String result = activityCallBack.getActivityAddress();
       }
    }
}
