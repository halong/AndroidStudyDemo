package com.example.halong.myapplication.data.network.Okhttp;

/**
 * Created by halong on 2017/12/9.
 */

public class EventMessage {
    public static final String text="text";
    public static final String image="image";
    String flag;
    Object object;

    public EventMessage(String flag,Object object){
        this.flag=flag;
        this.object=object;
    }
}
