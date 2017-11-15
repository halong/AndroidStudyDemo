package com.example.halong.myapplication.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.halong.myapplication.R;

public class MvpActivity extends AppCompatActivity {

    private TextView mText;
    DataInterface dataInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv);

        dataInterface = new MvpPresenter(new MvpPresenter.ModelInterface() {
            @Override
            public void updateData(String s) {
                updateView(s);//数据驱动
            }
        });

        initView();
    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mText.setText(dataInterface.getInitData());

        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataInterface.updateData();
            }
        });
    }

    private void updateView(String text) {
        mText.setText(text);
    }

    public interface DataInterface {
        String getInitData();
        void updateData();
    }
}
