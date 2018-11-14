package com.huchang.haxi.shoppingclient.myinterface;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huchang.haxi.shoppingclient.R;

public class AddressAcivity extends Activity implements IAddressClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_acivity);
    }

    @Override
    public AddressCallBack onAddressClicked(final String addressName) {
//todo
         AddressCallBack  callBack = new AddressCallBack() {
             @Override
             public String getActivityAddress() {
                 return addressName + AddressAcivity.class.getSimpleName();
             }
         };
         return callBack;
    }
}
