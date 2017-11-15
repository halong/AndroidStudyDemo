package com.example.halong.myapplication.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<String> texts;
    private Button mButton1;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        initData();
        initView();
    }

    private void initData() {
        texts = new ArrayList();
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
        texts.add("你好");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        layout.setOrientation(LinearLayoutManager.VERTICAL);

//        RecyclerView.LayoutManager layout=new GridLayoutManager(this,2);

        RecyclerView.LayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //设置LayoutManager
        mRecyclerView.setLayoutManager(layout);

        //设置分割线
        RecyclerView.ItemDecoration deco = new MyItemDecoration();
        mRecyclerView.addItemDecoration(deco);

        //设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置适配器
        adapter = new MyAdapter(this, texts);
        mRecyclerView.setAdapter(adapter);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                texts.add(0,"hello");

//                texts.remove(0);
                adapter.notifyDataSetChanged();
                break;
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        Context context;
        List<String> list;
        public MyAdapter(Context context,List<String> list){
            this.context=context;
            this.list=list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false);
            return new MyHolder(view);
        }



        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.mText.setText(list.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int count = parent.getChildCount();

            outRect.bottom = 20;
            outRect.right = 10;
            outRect.left = 10;
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView mText;

        public MyHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text1);
        }
    }
}
