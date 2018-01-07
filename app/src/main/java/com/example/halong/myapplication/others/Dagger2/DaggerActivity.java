package com.example.halong.myapplication.others.Dagger2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.others.Dagger2.di.DaggerMyComponent;
import com.example.halong.myapplication.others.Dagger2.di.MyModule;
import com.example.halong.myapplication.others.Dagger2.mvp.MyPresenter;
import com.example.halong.myapplication.others.Dagger2.mvp.MyView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaggerActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.text_view)
    TextView mTextView;
    @Inject
    MyPresenter mMyPresenter;
    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        ButterKnife.bind(this);

        DaggerMyComponent.builder()   //注册Dagger
                .myModule(new MyModule(this))
                .build()
                .inject(this);

        mMyPresenter.showText();
    }

    @Override
    public void showText(String s) {
        mTextView.setText(s);
    }


    @OnClick({R.id.button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mEditText.setCursorVisible(false); //设置光标不可见
                //设置关闭键盘
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                mMyPresenter.uploadText(mEditText.getText().toString());
                mMyPresenter.showText();
                break;
        }
    }
}
