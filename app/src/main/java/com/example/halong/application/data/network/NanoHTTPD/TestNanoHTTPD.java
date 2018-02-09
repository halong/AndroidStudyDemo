package com.example.halong.application.data.network.NanoHTTPD;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/12/9.
 */
public class TestNanoHTTPD extends NanoHTTPD {
    public static final String URL = "http://localhost:8090/";

    private TestNanoHTTPD(int port) {
        super(port);
    }

    private static class Instance {
        public static TestNanoHTTPD service = new TestNanoHTTPD(8090);
    }

    public static TestNanoHTTPD getDefault() {
        return Instance.service;
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        switch (method) {
            case GET:
                String params = session.getQueryParameterString();

                if(params==null){//如果GET请求无参，则返回JSON
                    User user1=new User("王明",34);
                    String json1=new Gson().toJson(user1);
                    return NanoHTTPD.newFixedLengthResponse(json1);
                }

                //如果有参，则返回参数
                try {
                    params = URLDecoder.decode(params, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return NanoHTTPD.newFixedLengthResponse("GET请求成功，上传的内容为：" + params);

            case POST:
                InputStream inputStream = session.getInputStream();

                try {
                    if (inputStream==null||inputStream.available()==0){ //如果POST请求无参，则返回一个文件
                        File file2=new File(Environment.getExternalStorageDirectory(),"file2.txt");
                        InputStream data= null;
                        try {
                            OutputStream outputStream2=new FileOutputStream(file2);
                            outputStream2.write("我是中文文本文档lalalala".getBytes());
                            outputStream2.flush();
                            outputStream2.close();

                            data = new FileInputStream(file2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return NanoHTTPD.newFixedLengthResponse(Response.Status.OK,"text/plain",data,file2.length());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //如果有参，则返回上传的内容
                byte[] bytes = new byte[0];
                try {
                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s = new String(bytes);
                try {
                    s = URLDecoder.decode(s, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return NanoHTTPD.newFixedLengthResponse("POST请求成功，上传的内容为：" + s);
        }

        return super.serve(session);
    }
}
