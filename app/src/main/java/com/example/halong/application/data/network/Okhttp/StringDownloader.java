package com.example.halong.application.data.network.Okhttp;

import android.os.Handler;
import android.os.Looper;
import com.orhanobut.logger.Logger;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by halong on 2018/1/28.
 */

public class StringDownloader extends Loader {
    private Builder mBuilder;
    private Request mRequest;
    private Call mCall;
    private Handler mHandler;

    private StringDownloader(Builder builder) {
        mBuilder = builder;
        mHandler = new Handler(Looper.getMainLooper());
        mRequest = new Request.Builder().get().url(builder.url).build();
        mCall = RequesterManager.getInstance().getOkHttpClient().newCall(mRequest);
    }


    @Override
    public void execute() {
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String msg = e.getMessage();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuilder.mCallback != null)
                            mBuilder.mCallback.onFailure(msg);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d(response.headers());

                final String data = response.body().string();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuilder.mCallback != null)
                            mBuilder.mCallback.onResponse(data);
                    }
                });

            }
        });
    }


    @Override
    public void cancel() {
        mCall.cancel();
    }


    public static class Builder {
        private String url;
        private StringCallback mCallback;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder params(Map<String, String> params) {
            if (params != null && params.size() > 0) {
                url += "?";
                for (String key :
                        params.keySet()) {
                    url += key + "=" + params.get(key) + "&";
                }
                url = url.substring(0, url.length() - 1);
            }
            return this;
        }

        public Builder callback(StringCallback callback) {
            this.mCallback = callback;
            return this;
        }

        public StringDownloader build() {
            return new StringDownloader(this);
        }
    }


    public interface StringCallback {

        void onFailure(String msg);

        void onResponse(String data);
    }
}
