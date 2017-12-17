package com.example.halong.myapplication.data.network.OkHttpUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.data.network.NanoHTTPD.TestService;
import com.example.halong.myapplication.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class OkHttpUtilsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private TextView mTv1;
    private ImageView mIv1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_utils);

        initView();
    }

    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
        mIv1 = (ImageView) findViewById(R.id.iv1);

        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setText("uploadParamsByGet()");
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setText("uploadParamsByPost()");
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setText("uploadStringByPost()");
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setText("uploadFileByPost()");
        mBtn4.setOnClickListener(this);
        mBtn5 = (Button) findViewById(R.id.btn5);
        mBtn5.setText("downloadFile()");
        mBtn5.setOnClickListener(this);
        mBtn6 = (Button) findViewById(R.id.btn6);
        mBtn6.setText("downloadImage()");
        mBtn6.setOnClickListener(this);
        mBtn7 = (Button) findViewById(R.id.btn7);
        mBtn7.setText("uploadFormByPost()");
        mBtn7.setOnClickListener(this);
        mBtn8 = (Button) findViewById(R.id.btn8);
        mBtn8.setOnClickListener(this);
        mBtn9 = (Button) findViewById(R.id.btn9);
        mBtn9.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            TestService.getDefault().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        TestService.getDefault().stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                uploadParamsByGet();
                break;
            case R.id.btn2:
                uploadParamsByPost();
                break;
            case R.id.btn3:
                uploadStringByPost();
                break;
            case R.id.btn4:
                uploadFileByPost();
                break;
            case R.id.btn5:
                downloadFile();
                break;
            case R.id.btn6:
                downloadImage();
                break;
            case R.id.btn7:
                uploadFormByPost();
                break;
            case R.id.btn8:
                break;
            case R.id.btn9:
                break;
        }
    }


    private void uploadParamsByGet() {
        OkHttpUtils.get()
                .url(TestService.URL)
                .addParams("username", "hyman")
                .addParams("password", "中文get")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mTv1.setText(response);
                    }
                });
    }

    /**
     * to request by the post method need URLEncode and URLDecode for Chinese
     */

    private void uploadParamsByPost() {
        String zh = "中文post";

        try {
            zh = URLEncoder.encode(zh, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        OkHttpUtils.post()
                .url(TestService.URL)
                .addParams("key", "val")
                .addParams("zh", zh)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            response = URLDecoder.decode(response, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        mTv1.setText(response);
                    }
                });
    }

    private void uploadStringByPost() {
        OkHttpUtils.postString()
                .url(TestService.URL)
                .content("hello中文")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mTv1.setText(response);
                    }
                });
    }

    private void uploadFileByPost() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "hello.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write("asdjkfhsjkf中文".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postFile()
                .url(TestService.URL)
                .file(file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mTv1.setText(response);
                    }
                });
    }


    private void downloadFile() {
        OkHttpUtils.get()
                .url("http://img.bss.csdn.net/201712051123224037.jpg")
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "hello.jpg") {
                    @Override
                    public void inProgress(float progress) {
                        mTv1.setText(progress + "");
                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(File response) {
                        mIv1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hello.jpg"));
                    }
                });

    }

    private void downloadImage() {
        OkHttpUtils.get()
                .url("https://gtd.alicdn.com/tfscom/TB19Farf3LD8KJjSszetKaGRpXa")
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap response) {
                        mIv1.setImageBitmap(response);
                    }
                });

    }

    private void uploadFormByPost() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "hello.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write("asdjkfhsjkf中文".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(TestService.URL)
                .addFile("form", "h.jpg", file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mTv1.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mTv1.setText(response);
                    }
                });
    }
}
