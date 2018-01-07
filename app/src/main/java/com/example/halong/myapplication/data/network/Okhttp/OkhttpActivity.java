package com.example.halong.myapplication.data.network.Okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.data.network.NanoHTTPD.TestNanoHTTPD;
import com.example.halong.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private ImageView mIamge;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    /**
     * 测试->POST上传文件
     */
    private Button mButton5;
    /**
     * 测试->POST上传表单
     */
    private Button mButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        try {
            TestNanoHTTPD.getDefault().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        TestNanoHTTPD.getDefault().stop();
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage message) {
        switch (message.flag) {
            case EventMessage.image:
                mIamge.setVisibility(View.VISIBLE);
                mIamge.setImageBitmap((Bitmap) message.object);
                mText.setVisibility(View.INVISIBLE);
                break;
            case EventMessage.text:
                mText.setVisibility(View.VISIBLE);
                mText.setText((String) message.object);
                mIamge.setVisibility(View.INVISIBLE);
                break;
        }
    }


    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mIamge = (ImageView) findViewById(R.id.iamge);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                MyOkHttpUtil.get("https://gtd.alicdn.com/tfscom/TB1eqIqfRTH8KJjy0Fiwu3RsXXa", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        EventBus.getDefault().post(new EventMessage(EventMessage.image, bitmap));
                    }
                });

                break;
            case R.id.button2:
                Map<String, String> params2 = new HashMap<>();
                params2.put("key", "val");
                params2.put("zh", "中文");
                MyOkHttpUtil.get(TestNanoHTTPD.URL, params2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, response.body().string()));
                    }
                });
                break;
                //post->params
            case R.id.button3:
                Map<String, String> params3 = new HashMap<>();
                params3.put("key", "val");
                params3.put("zh", "中文");
                MyOkHttpUtil.post(TestNanoHTTPD.URL, params3, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, response.body().string()));
                    }
                });
                break;
                //post->String
            case R.id.button4:
                String sting="adlsjkgljadg中文";
                MyOkHttpUtil.post(TestNanoHTTPD.URL, sting, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, response.body().string()));
                    }
                });
                break;

                //post->file
            case R.id.button5:
                File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"hh.txt");
                try {
                    OutputStream outputStream=new FileOutputStream(file);
                    outputStream.write("fhsdjhflsjkhfj中文".getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MyOkHttpUtil.post(TestNanoHTTPD.URL, file, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, response.body().string()));
                    }
                });
                break;

                //post->form
            case R.id.button6:
                Map<String,String> params=new HashMap<>();
                params.put("key","sdajkfh中文");

                Map<String, Map<String, File>> files=new HashMap<>();
                Map<String, File> val=new HashMap<>();
                File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"gggg.text");
                try {
                    OutputStream outputStream=new FileOutputStream(f);
                    outputStream.write("sdjkfagjha中文".getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                val.put("shj.txt",f);
                files.put("kkk",val);
                MyOkHttpUtil.post(TestNanoHTTPD.URL, params, files, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new EventMessage(EventMessage.text, response.body().string()));
                    }
                });
                break;
        }
    }
}
