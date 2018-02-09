package com.example.halong.application.ui.StatusLayout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.halong.application.R;

import java.util.List;

import me.bakumon.statuslayoutmanager.library.DefaultOnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

public class StatusLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StatusLayoutPresenter presenter;
    private Handler mHandler;
    private StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_layout);

        presenter = new StatusLayoutPresenter(this);
        presenter.showInitView();

        mHandler = new Handler(getMainLooper()) {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case 0:
                        // 加载失败
                        statusLayoutManager.showErrorLayout();
                        break;
                    case 1:
                        // 空数据
                        statusLayoutManager.showEmptyLayout();
                        break;
                    case 2:
                        // 加载成功，显示原布局
                        statusLayoutManager.showSuccessLayout();
                        break;
                }
            }
        };


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    public void initView(List<String> strings, List<Bitmap> bitmaps) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new MyAdapter(this, strings, bitmaps);
        mRecyclerView.setAdapter(adapter);

        statusLayoutManager = new StatusLayoutManager.Builder(mRecyclerView)
                // 设置重试事件监听器
                .setOnStatusChildClickListener(new DefaultOnStatusChildClickListener() {
                    @Override
                    public void onEmptyChildClick(View view) {
                        statusLayoutManager.showLoadingLayout();
                        mHandler.sendEmptyMessageDelayed(1,4000);
                    }

                    @Override
                    public void onErrorChildClick(View view) {
                        statusLayoutManager.showLoadingLayout();
                        mHandler.sendEmptyMessageDelayed(2,4000);
                    }
                })
                // 设置默认加载中布局的提示文本
                .setDefaultLoadingText("拼命加载中...")

                // 设置默认空数据布局的提示文本
                .setDefaultEmptyText("空白了，哈哈哈哈")
                // 设置默认空数据布局的图片
//                .setDefaultEmptyImg(R.mipmap.ic_launcher)
                // 设置默认出错布局的提示文本

                .setDefaultErrorText("错误")
                // 设置默认出错布局的图片
//                .setDefaultErrorImg(R.mipmap.ic_launcher)
                // 设置布局背景，包括加载中、空数据和出错布局
                .setDefaultLayoutsBackgroundColor(Color.WHITE)
                .build();

        statusLayoutManager.showLoadingLayout();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(0);
            }
        }).start();

    }


}
