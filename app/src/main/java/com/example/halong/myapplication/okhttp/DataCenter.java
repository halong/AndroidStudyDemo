package com.example.halong.myapplication.okhttp;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by halong on 2017/11/12.
 *
 *     1， 用于持久化保存数据
 *
 *     2， 数据发生变化通过EventBus通知Activity
 */

public class DataCenter {
    private static String text;

    private DataCenter(){

    }

    public static void setText(String t){
        text=t;
        EventBus.getDefault().post(new EventMessage(EventMessage.Get_Text));
    }

    public static String getText() {
        return text;
    }
}
