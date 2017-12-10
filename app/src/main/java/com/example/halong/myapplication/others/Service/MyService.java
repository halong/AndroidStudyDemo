package com.example.halong.myapplication.others.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service{
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
