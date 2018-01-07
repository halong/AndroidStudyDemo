package com.example.halong.myapplication.ui.SlideAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.wyh.slideAdapter.FooterBind;
import com.wyh.slideAdapter.ItemBind;
import com.wyh.slideAdapter.ItemView;
import com.wyh.slideAdapter.SlideAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapterActivity extends AppCompatActivity {

    final List<Bean> data = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_adapter);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            data.add(new Bean("我是第" + i + "个item"));
        }
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SlideAdapterActivity.this));

        ItemBind itemBind = new ItemBind<Bean>() {
            @Override
            public void onBind(ItemView itemView, Bean data, int position) {
                itemView.setText(R.id.textView, data.getInfo())
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //点击item
                            }
                        })
                        .setOnClickListener(R.id.textView, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //点击textView
                            }
                        });
            }
        };
        SlideAdapter.load(data)           //加载数据
                .item(R.layout.item)  //指定布局
                .bind(itemBind)       //视图绑定
                .into(mRecyclerView);  //填充到recyclerView中

//        RecyclerView.Adapter adapter=new RecyclerView.Adapter<MyViewHolder>() {
//            @Override
//            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View itemView= LayoutInflater.from(SlideAdapterActivity.this).inflate(R.layout.item,parent,false);
//                return new MyViewHolder(itemView);
//            }
//
//            @Override
//            public void onBindViewHolder(MyViewHolder holder, int position) {
//                holder.textView.setText(data.get(position).getInfo());
//            }
//
//            @Override
//            public int getItemCount() {
//                return data.size();
//            }
//        };
//        mRecyclerView.setAdapter(adapter);
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView=(TextView)itemView.findViewById(R.id.textView);
        }
    }
}
