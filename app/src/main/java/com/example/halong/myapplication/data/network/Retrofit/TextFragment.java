package com.example.halong.myapplication.data.network.Retrofit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halong.myapplication.R;

public class TextFragment extends Fragment {
    private TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        mText = (TextView) view.findViewById(R.id.text);
        return view;
    }

    public void setText(String text) {
        mText.setText(text);
    }

}
