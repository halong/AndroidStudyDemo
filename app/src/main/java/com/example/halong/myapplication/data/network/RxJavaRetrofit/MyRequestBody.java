package com.example.halong.myapplication.data.network.RxJavaRetrofit;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by halong on 2017/12/31.
 */

public class MyRequestBody extends RequestBody {
    private OnProgressListener listener;
    private long total = 1l;
    private int progress;

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        total = contentLength();
        long j = total;
        while (j >= 1024) {
            sink.write(new byte[1024]);
            j = j - 1024;
            listener.onProgress(total - j, total);
        }
        if (j < 0) {
            sink.write(new byte[(int) j]);
            listener.onProgress(total, total);
        }
    }

    @Override
    public long contentLength() throws IOException {
        return super.contentLength();
    }


    public void setOnProgressListener(OnProgressListener l) {
        listener = l;
    }

    interface OnProgressListener {
        void onProgress(long progress, long total);
    }
}
