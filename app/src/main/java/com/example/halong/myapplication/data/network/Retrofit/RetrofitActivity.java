package com.example.halong.myapplication.data.network.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.halong.myapplication.R;
import com.example.halong.myapplication.data.network.NanoHTTPD.TestService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 测试GET请求
     */
    private Button mButton1;
    /**
     * 测试POST请求
     */
    private Button mButton2;
    /**
     * 测试POST FORM
     */
    private Button mButton3;
    private Button mButton4;

    private RetrofitUtil retrofitUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();

        retrofitUtil = new RetrofitUtil();
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
        TestService.getDefault().stop();
        super.onStop();
    }



    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Map<String, String> params = new HashMap<>();
                params.put("key", "中文");
                retrofitUtil.get(TestService.URL, params, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}
