package com.example.halong.myapplication.NanoHTTPD;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/9/6.
 */

public class MyNanohttpd extends NanoHTTPD {
    public MyNanohttpd() throws IOException {
        super(8088);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        EventBus.getDefault().post(new Intent("服务已开启"));
    }



    @Override
    public Response serve(IHTTPSession session) {
        Map<String,List<String>> map=session.getParameters();
        for(String key:map.keySet()){
            EventBus.getDefault().post(new Intent("request:"+key+"="+map.get(key).get(0)));
        }

//        InputStream is=session.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//        sb.append("requst");
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "/n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        EventBus.getDefault().post(new Intent(new String(sb)));


//        EventBus.getDefault().post(new Intent("request:"+session.getQueryParameterString()));

        return NanoHTTPD.newFixedLengthResponse("hello");
    }

    @Override
    public void stop() {
        super.stop();
        EventBus.getDefault().post(new Intent("服务已停止"));
    }
}
