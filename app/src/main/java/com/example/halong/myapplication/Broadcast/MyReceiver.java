package com.example.halong.myapplication.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

/**
 * Created by halong on 2017/7/24.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"在manifest注册的BroadcastReceiver",Toast.LENGTH_LONG).show();
        Logger.d("在manifest注册的BroadcastReceiver");
    }
}
