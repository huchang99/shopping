package com.huchang.haxi.shoppingclient.myclass;

import android.media.Image;

public class ModeHomeMenu {

    private String name;
    private Image image;

    public ModeHomeMenu(String name,Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
}
