package com.example.halong.application.ui.Matisse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.halong.application.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

public class MatisseDemoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_CHOOSE = 1;
    private List<Uri> mSelected = new ArrayList<>();
    private ListView mListView;
    private BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matisse_demo);
        initView();

        Matisse.from(MatisseDemoActivity.this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .theme(R.style.Matisse_Dracula)
                .countable(false)
                .maxSelectable(9)
                .imageEngine(new PicassoEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            baseAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);


        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mSelected.size();
            }

            @Override
            public Object getItem(int i) {
                return mSelected.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHolder viewHolder;
                if (view == null) {
                    view = LayoutInflater.from(MatisseDemoActivity.this).inflate(R.layout.listview_item, viewGroup, false);
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = view.findViewById(R.id.image_view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }

                viewHolder.imageView.setImageURI(mSelected.get(i));
                return view;
            }

            class ViewHolder {
                public ImageView imageView;
            }
        };


        mListView.setAdapter(baseAdapter);
    }
}
