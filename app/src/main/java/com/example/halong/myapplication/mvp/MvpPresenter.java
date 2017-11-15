package com.example.halong.myapplication.mvp;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by halong on 2017/8/1.
 */

public class MvpPresenter implements MvpActivity.DataInterface {
    private ModelInterface modelInterface;


    public MvpPresenter(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }

    public interface ModelInterface {
        void updateData(String s);
    }

    @Override
    public String getInitData() {
        return "Load after Click";
    }

    @Override
    public void updateData() {
        new LoadTask().execute();
    }

    class LoadTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            publishProgress("loading...");

            String result = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("https://www.baidu.com/").build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            modelInterface.updateData((String) values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            modelInterface.updateData((String) o);

        }
    }
}
