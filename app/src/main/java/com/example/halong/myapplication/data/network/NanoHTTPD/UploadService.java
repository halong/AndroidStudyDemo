package com.example.halong.myapplication.data.network.NanoHTTPD;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by halong on 2017/9/6.
 */

public class UploadService extends NanoHTTPD {
    public static String URL = "http://localhost:8088";

    public UploadService() {
        super(8088);
    }


    @Override
    public Response serve(IHTTPSession session) {
        Logger.d(session.getParameters());

        //客户端上传会自动编码  服务器端接收后要自动解码
        switch (session.getMethod()) {
            //解析GET
            case GET:
                String string = session.getQueryParameterString();
                if (string != null) {
                    try {
                        string = URLDecoder.decode(string, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return NanoHTTPD.newFixedLengthResponse("GET请求成功，请求参数为：" + string);

 /*
           //解析Parameters
          Map<String, List<String>> map = session.getParameters();
            List<String> vals = map.get("param");
            if (vals != null && vals.size() > 0) {
                String val = vals.get(0);
                try {
                    val = URLDecoder.decode(val, "UTF-8"); //对value进行解码
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                switch (val) {
                    case "请求JSON": //接收的请求地址：http://localhost:8088?param=请求JSON
                        User user1 = new User("xiaomong", 21);
                        User user2 = new User("xiaohong", 31);
                        User user3 = new User("王明", 25);
                        List<User> users = new ArrayList<>();
                        users.add(user1);
                        users.add(user2);
                        users.add(user3);
                        String s = new Gson().toJson(users);
                        return NanoHTTPD.newFixedLengthResponse(s);

                    case "请求文件":  //接收的请求地址:http://localhost:8088?param=请求文件
                        //NanoHttpd下传文件
                        try {
                            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "123.jpg");
                            return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/octet-stream", new FileInputStream(file), file.length());
                            //！这里有个坑  最后一个参数要用file.length
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            return NanoHTTPD.newFixedLengthResponse("下传文件失败");
                        }
                }
            }
*/
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


                String parameterString = session.getQueryParameterString();
                if (parameterString != null) {
                    try {
                        parameterString = URLDecoder.decode(parameterString, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return NanoHTTPD.newFixedLengthResponse("POST请求成功，请求参数为：" + parameterString);


            /*

                //解析Parameters
                Map<String, List<String>> map2 = session.getParameters();//Map<key,fileName>
                for (String key : map2.keySet()) {
                    switch (key) {
                        //解析post上传的param
                        case "param": //请求地址: http://localhost:8088  参数：param=请求JSON
                            String val = session.getParameters().get("param").get(0);
                            try {
                                val = URLDecoder.decode(val, "UTF-8");//Post接收的Param需要解码
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            if (val.equals("请求JSON")) {
                                User user1 = new User("成龙h", 21);
                                User user2 = new User("xiaohong", 31);
                                User user3 = new User("王明", 25);
                                List<User> users = new ArrayList<>();
                                users.add(user1);
                                users.add(user2);
                                users.add(user3);
                                String s = new Gson().toJson(users);
                                return NanoHTTPD.newFixedLengthResponse(s);
                            }

                            //解析post上传的form
                        case "form"://请求地址: http://localhost:8088  参数：form -- fileName ---file
                            String fileName = map2.get(key).get(0);
                            String filePath = files.get(key);
                            *//*
                             filePath=/data/data/com.example.halong.myapplication/cache/NanoHTTPD--2063279122
                              保存在cache中，当session结束，cache清空，所以在FileExplorer中看不到
                              需要在session中，copy到sdcard中
                             *//*


                            //将cache中的文件copy到本地
                            File cacheFile = new File(filePath);
                            File cFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
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
                            return NanoHTTPD.newFixedLengthResponse("Form上传成功");
                    }
                }*/
        }

        return NanoHTTPD.newFixedLengthResponse("Nanohttpd请求失败");
    }
}
