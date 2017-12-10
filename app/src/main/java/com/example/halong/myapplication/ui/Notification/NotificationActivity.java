package com.example.halong.myapplication.ui.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.halong.myapplication.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * show a default notification.
     */
    private Button mButton1;
    /**
     * show a custom notification.
     */
    private Button mButton2;

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mDefaultNotificationBuilder;
    private NotificationCompat.Builder mCustomNotificationBuilder;
    private RemoteViews mCostomContenView;

    private BroadcastReceiver mReceiver;
    private Handler mHandler;
    private MyThread mThread = null;

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //注册BroadcastReceiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("PauseOrContinue");
        filter.addAction("Exit");
        registerReceiver(mReceiver, filter);

        //开启ProgressBar更新线程
        if (mThread != null && !mThread.isAlive()) {
            mThread.start();
        }
    }

    @Override
    protected void onStop() {
        //注销BroadcastReceiver
        unregisterReceiver(mReceiver);

        //关闭更新线程
        if (mThread != null && mThread.isAlive()) {
            mThread.interrupt();
        }
        super.onStop();
    }

    //以下是自定义方法

    private void initData() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mDefaultNotificationBuilder = new NotificationCompat.Builder(this);
        mCustomNotificationBuilder = new NotificationCompat.Builder(this);
        mCostomContenView = new RemoteViews(getPackageName(), R.layout.layout_notification);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "PauseOrContinue":
                        if (mThread.isPause) {
                            mCostomContenView.setTextViewText(R.id.text1, "Pause");
                            mCustomNotificationBuilder.setCustomContentView(mCostomContenView);
                            mNotificationManager.notify(1, mCustomNotificationBuilder.build());
                            mThread.goon();
                        } else {
                            mCostomContenView.setTextViewText(R.id.text1, "Continue");
                            mCustomNotificationBuilder.setCustomContentView(mCostomContenView);
                            mNotificationManager.notify(1, mCustomNotificationBuilder.build());
                            mThread.pause();
                        }
                        break;

                    case "Exit":
                        mThread.pause();
                        mNotificationManager.cancel(1);
                        break;
                }

            }
        };

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Log.d("==>", msg.what + "");
                switch (msg.what) {
                    case 1:
                        if (progress >= 100) {
                            mThread.pause();
                            mNotificationManager.cancel(1);
                        } else {
                            progress++;
                            mCostomContenView.setProgressBar(R.id.progress, 100, progress, false);
                            mCustomNotificationBuilder.setCustomContentView(mCostomContenView);
                            mNotificationManager.notify(1, mCustomNotificationBuilder.build());
                        }
                        break;

                }

            }
        };

        mThread = new MyThread(mHandler);
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                showDefaultNotification();
                break;
            case R.id.button2:
                showCustomNotification();
                progress = 0;
                mThread.goon();
                break;
        }
    }

    public void showDefaultNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        mDefaultNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setTicker("this is a default notification.")
                .setContentTitle("content tittle")
                .setContentText("this is a default notification.")
                .setDefaults(Notification.DEFAULT_ALL)
                .setNumber(1)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "click1", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "click2", pendingIntent)
                .setProgress(100, 20, true);

        mNotificationManager.notify(0, mDefaultNotificationBuilder.build());
    }


    private void showCustomNotification() {
        mCustomNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("mCustomNotificationBuilder");

        mCostomContenView.setTextViewText(R.id.text1, "Pause");

        Intent intent = new Intent("PauseOrContinue");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mCostomContenView.setOnClickPendingIntent(R.id.text1, pendingIntent);

        Intent intent2 = new Intent("Exit");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
        mCostomContenView.setOnClickPendingIntent(R.id.text2, pendingIntent2);

        mCostomContenView.setProgressBar(R.id.progress, 100, 0, false);

        mCustomNotificationBuilder.setCustomContentView(mCostomContenView);
        mCustomNotificationBuilder.setOngoing(true);
        mNotificationManager.notify(1, mCustomNotificationBuilder.build());
    }


    //时间线程  Thread + Handler用来更新ProgressBar
    class MyThread extends Thread {
        Handler mHandler;
        boolean isStop = false;
        boolean isPause = true;

        public MyThread(Handler mHandler) {
            this.mHandler = mHandler;
        }

        @Override
        public void run() {
            super.run();
            while (!isStop) {
                if (!isPause) {
                    mHandler.sendEmptyMessage(1);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void pause() {
            isPause = true;
        }

        public void goon() {
            isPause = false;
        }

        @Override
        public void interrupt() {
            isPause=true;
            isStop=true;
            super.interrupt();
        }
    }
}
