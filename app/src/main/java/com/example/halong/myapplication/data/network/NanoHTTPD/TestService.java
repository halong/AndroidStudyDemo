package com.example.halong.myapplication.data.network.NanoHTTPD;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/12/9.
 */
public class TestService extends NanoHTTPD {
    public static final String URL="http://localhost:8090";
    private TestService(int port) {
        super(port);
    }

     private static class Instance{
        public static TestService service=new TestService(8090);
    }

    public static TestService getDefault(){
        return  Instance.service;
    }


    @Override
    public Response serve(IHTTPSession session) {
        Method method=session.getMethod();
        switch (method){
            case GET:
                String params=session.getQueryParameterString();
                try {
                    params=URLDecoder.decode(params,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return NanoHTTPD.newFixedLengthResponse("GET请求成功，上传的内容为："+params);

            case POST:
                InputStream inputStream=session.getInputStream();
                byte[] bytes = new byte[0];
                try {
                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s=new String(bytes);
                try {
                    s=URLDecoder.decode(s,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return NanoHTTPD.newFixedLengthResponse("POST请求成功，上传的内容为："+s);
        }

       return super.serve(session);
    }
}
