package com.example.halong.myapplication.NanoHTTPD;

import android.content.Intent;
import android.os.Environment;

import com.example.halong.myapplication.GreenDao.User;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/9/6.
 */

public class MyNanohttpd extends NanoHTTPD {
    public static String LocalURL = "http://localhost:8088";

    public MyNanohttpd() throws IOException {
        super(8088);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        EventBus.getDefault().post(new Intent("服务已开启"));
    }

    @Override
    public Response serve(IHTTPSession session) {
        switch (session.getMethod()) {
            //解析GET
            case GET:
                //解析Parameters
                Map<String, List<String>> map = session.getParameters();
                for (String key : map.keySet()) {
                    String val = map.get(key).get(0);
                    try {
                        val=URLDecoder.decode(val,"UTF-8"); //对value进行解码
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                User user=new User(null,"xiaomong",21);
                List<User> users=new ArrayList<>();
                users.add(user);
                String s=new Gson().toJson(users);
                return NanoHTTPD.newFixedLengthResponse(s);

            //解析POST
            case POST:
                //post方法需要parseBody
                Map<String, String> files = new HashMap<String, String>();//Map<key,上传的文件保存path>
                try {
                    session.parseBody(files);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ResponseException e) {
                    e.printStackTrace();
                }

                //解析Parameters
                Map<String, List<String>> map2 = session.getParameters();//Map<key,fileName>
                for (String key : map2.keySet()) {
                    switch (key) {
                        //解析post上传的form
                        case "form":
                            String fileName = map2.get(key).get(0);
                            String filePath = files.get(key);
                            /*
                             filePath=/data/data/com.example.halong.myapplication/cache/NanoHTTPD--2063279122
                              保存在cache中，当session结束，cache清空，所以在FileExplorer中看不到
                              需要在session中，copy到sdcard中
                             */

                            //将cache中的文件copy到本地
                            File cacheFile = new File(filePath);
                            File cFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "13.jpg");
                            FileInputStream in = null;
                            FileOutputStream out = null;
                            try {
                                in = new FileInputStream(cacheFile);
                                out = new FileOutputStream(cFile);
                                byte[] bytes = new byte[1024 * 8];
                                int length;
                                while ((length = in.read(bytes)) != -1) {
                                    out.write(bytes, 0, length);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (in != null)
                                        in.close();
                                    if (out != null)
                                        out.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            return NanoHTTPD.newFixedLengthResponse(cFile.getAbsolutePath());

                        //解析post上传的param
                        case "param":
                            String val = session.getParameters().get("param").get(0);
                            try {
                                val = URLDecoder.decode(val, "UTF-8");//Post接收的Param需要解码
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            //NanoHttpd下传文件
                            try {
                                File cFile2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "123.jpg");
                                return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/octet-stream", new FileInputStream(cFile2), cFile2.getTotalSpace());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                return  NanoHTTPD.newFixedLengthResponse("下传文件失败");
                            }
                    }
                }

                break;
        }

        return NanoHTTPD.newFixedLengthResponse("error");
    }

    @Override
    public void stop() {
        super.stop();
        EventBus.getDefault().post(new Intent("服务已停止"));
    }
}
