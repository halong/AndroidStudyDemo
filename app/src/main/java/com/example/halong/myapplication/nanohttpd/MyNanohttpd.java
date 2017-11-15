package com.example.halong.myapplication.nanohttpd;

import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/9/6.
 */

public class MyNanohttpd extends NanoHTTPD {
    public MyNanohttpd() throws IOException {
        super(8088);
    }

    @Override
    public Response serve(IHTTPSession session) {

        Map<String,List<String>> map=session.getParameters();
        for(String key:map.keySet()){
            EventBus.getDefault().post(new Intent("Service:"+key+"="+map.get(key).get(0)));
        }

        return NanoHTTPD.newFixedLengthResponse("hello");
    }
}
