package com.example.halong.myapplication.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.dagger2.di.DaggerDaggerComponent;
import com.example.halong.myapplication.dagger2.di.DaggerModule;
import com.example.halong.myapplication.dagger2.mvp.DaggerPresenter;
import com.example.halong.myapplication.dagger2.mvp.DaggerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaggerActivity extends AppCompatActivity implements DaggerView {

    @BindView(R.id.text1)
    TextView mTextView;

    @Inject
    DaggerPresenter mDaggerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        ButterKnife.bind(this);

        DaggerDaggerComponent.builder()
                .daggerModule(new DaggerModule(this))
                .build()
                .inject(this);
//        mDaggerPresenter=new DaggerPresenter(this);


        mDaggerPresenter.showText();

    }

    @Override
    public void showText(String s) {
        mTextView.setText(s);
    }
}
