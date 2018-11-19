package com.huchang.haxi.shoppingclient.myclass;

import android.media.Image;

public class ModeHomeMenu {

    private String name;
    private int image;

    public ModeHomeMenu(String name,int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
