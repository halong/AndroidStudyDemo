package com.example.halong.myapplication.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.halong.myapplication.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * default
     */
    private TextView mText1;
    /**
     * custom
     */
    private TextView mText2;

    private AlertDialog.Builder builder;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
//            showDialog();
//        }
        return false;
    }

    private void initView() {
        mText1 = (TextView) findViewById(R.id.text1);
        mText1.setOnClickListener(this);
        mText2 = (TextView) findViewById(R.id.text2);
        mText2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                showDialog();
                break;
            case R.id.text2:
                break;
        }
    }

    public void showDialog(){
        builder=new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.btn_star);
        builder.setTitle("提示：");
        builder.setMessage("确定退出？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
                DialogActivity.this.finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        dialog=builder.create();
        dialog.show();
    }
}
