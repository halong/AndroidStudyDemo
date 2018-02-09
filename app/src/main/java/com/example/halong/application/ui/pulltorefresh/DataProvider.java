package com.example.halong.application.ui.pulltorefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by halong on 2017/7/28.
 */

public class DataProvider  implements PulltorefreshActivity.DataInterface{
    DataCallback dataCallback;
    public DataProvider(DataCallback dataCallback){
        this.dataCallback = dataCallback;
    }

    interface DataCallback {
        public void updateData(List<String> list);
    }

    @Override
    public List<String> getInitListData() {
        List<String> list = new ArrayList<>();
        list.add("啥都好看风景哈萨克积分");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("啥都好看风景哈萨克积分");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("啥都好看风景哈萨克积分");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        list.add("ajdhjadaa");
        return list;
    }
}
