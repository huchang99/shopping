package com.huchang.haxi.shoppingclient.myinterface;

public interface IAddressClickListener {

    AddressCallBack onAddressClicked(String addressName);

    interface AddressCallBack{
        String getActivityAddress();
    }
}
