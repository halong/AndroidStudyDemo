package com.example.halong.myapplication.NanoHTTPD;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NanohttpdActivity extends AppCompatActivity implements View.OnClickListener {
    private MyNanohttpd myNanohttpd = null;
    private TextView mTv1;

    private String text1 = "";
    private Button mBtn1;
    /**
     * 开启
     */
    private Button mBtn2;
    /**
     * 停止
     */
    private Button mBtn3;
    private ImageView mImage;
    /**
     * 测试Post
     */
    private Button mBtn4;
    /**
     * 测试Form
     */
    private Button mBtn5;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanohttpd);

        initView();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Intent intent) {
        String date = dateFormat.format(new Date());
        text1 += date + "  " + intent.getAction() + "\n";
        mTv1.setText(text1);
    }

    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
        mBtn5 = (Button) findViewById(R.id.btn5);
        mBtn5.setOnClickListener(this);

//        mImage.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"13.jpg")));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //开启服务
            case R.id.btn2:
                try {
                    if (myNanohttpd == null)
                        myNanohttpd = new MyNanohttpd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            //关闭服务
            case R.id.btn3:
                if (myNanohttpd != null) {
                    myNanohttpd.stop();
                    myNanohttpd = null;
                }
                break;

            //测试Get    1!将中文放到url中会自动编码  服务器端需要解码
            case R.id.btn1:
                Request request1 = new Request.Builder()
                        .get()
                        .url(MyNanohttpd.LocalURL + "?key=中文")
                        .build();
                Call call1 = new OkHttpClient().newCall(request1);
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new Intent("response:" + e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new Intent("response:" + response.body().string()));
                    }
                });
                break;

            //测试Post
            case R.id.btn4:
                RequestBody body4 = new FormBody.Builder()
                        .add("param", "中文")
                        .build();
                Request request4 = new Request.Builder()
                        .url(MyNanohttpd.LocalURL)
                        .post(body4)
                        .build();
                new OkHttpClient().newCall(request4).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new Intent("response:" + e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        //将stream转存到本地
                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "00.jpg");
                        InputStream inputStream = response.body().byteStream();
                        OutputStream outputStream = new FileOutputStream(file);
                        int length = 0;
                        byte[] bytes = new byte[1024];
                        while ((length = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, length);
                        }
                        inputStream.close();
                        outputStream.close();
                    }
                });
                break;

            //测试Form
            case R.id.btn5:
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "hello.jpg");
                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("form", "hl.jpg", RequestBody.create(MediaType.parse("image/jpg"), file))
                        .build();
                Request request = new Request
                        .Builder()
                        .url(MyNanohttpd.LocalURL)
                        .post(body)
                        .build();
                Call call = new OkHttpClient().newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new Intent("response:" + e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new Intent("response:" + response.body().string()));

                    }
                });
                break;
        }
    }
}
