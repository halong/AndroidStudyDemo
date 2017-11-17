package com.example.halong.myapplication.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * get String from Internet
     */
    private TextView mText;

    /**
     * 用于发出命令
     */
    public interface OrderInterface {
        void getHtmlText();
    }

    private OrderInterface orderInterface=new OrderCenter();

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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage event) {
        switch (event.flag){
            case EventMessage.Get_Text:
                mText.setText(DataCenter.getText());
                break;
        }

    }


    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                orderInterface.getHtmlText();
                break;
        }
    }
}
