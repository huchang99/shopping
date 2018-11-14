package com.huchang.haxi.shoppingclient.encity;

public class ResponseObject<T> {
    private String state;
    private T data;

    public ResponseObject(String state, T data) {
        this.state = state;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
