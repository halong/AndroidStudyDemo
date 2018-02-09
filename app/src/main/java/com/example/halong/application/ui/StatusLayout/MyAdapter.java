package com.example.halong.application.ui.StatusLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.application.R;

import java.util.List;

/**
 * Created by halong on 2018/1/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<Bitmap> bitmaps;
    private List<String> strings;

    public MyAdapter(Context context,List<String> strings,List<Bitmap> bitmaps){
        this.context=context;
        this.strings=strings;
        this.bitmaps=bitmaps;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new MyHolder(view);
    }

    @Override
    public int getItemViewType(int position) { //通过返回值控制Holder的类型
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(strings.get(position));
        holder.imageView.setImageBitmap(bitmaps.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();

    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
            imageView=itemView.findViewById(R.id.image_view);
        }
    }

}