package com.example.halong.myapplication.data.network.Retrofit;

/**
 * Created by halong on 2017/12/10.
 */

public class EventMessage {
    public int what;
    public Object obj;

    public EventMessage(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }
}
