package com.example.halong.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.halong.myapplication.nanohttpd.MyNanohttpd;

import java.io.FileDescriptor;
import java.io.IOException;

public class MyService extends Service{
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            new MyNanohttpd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public  void test(){
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

   public class MyBinder extends Binder{
       public MyService getService(){
            return MyService.this;
        }
   }

}
