package com.example.halong.myapplication.others.Reflect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        initView();

    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton1.setText("测试Reflect");
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton2.setText("测试Annotation");
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://测试Reflect
                String s1="测试:\n";
                try {
                    Class clazz=Class.forName("com.example.halong.myapplication.others.Reflect.Test");
                    //通过完整路径获取到目标类--属性 方法

                    Constructor constructor=clazz.getConstructor(String.class);//获取到目标类的构造器
                    Object instance=constructor.newInstance("hello3");//获取到目标类的实例
                    s1+="目标类的实例:"+instance.toString()+"\n";

                    Field field1=clazz.getDeclaredField("text");//获取到目标类的属性
                    field1.setAccessible(true);
                    s1+="目标类的属性值:"+field1.get(instance)+"\n";

                    Method method1=clazz.getDeclaredMethod("setText",String.class);//获取到目标类的方法
                    method1.setAccessible(true);
                    Method method2=clazz.getDeclaredMethod("getText");
                    method2.setAccessible(true);
                    Method method3=clazz.getMethod("test");//public方法 不需要setAccessible(true);

                    method1.invoke(instance,"beautiful");//调用目标类的方法
                    String st=(String)method2.invoke(instance);
                    s1+="目标类的方法返值:"+st+"\n";

                    String s11= (String) method3.invoke(null);//静态方法不需要invoke instance
                    s1+="目标类的静态方法返值:"+s11+"\n";
                } catch (Exception e) {
                    s1+="Exception"+e.getMessage()+"\n";
                }
                mText.setText(s1);
                break;

            case R.id.button2://测试Annotation    Evenbus源码
                String s2="测试Annotation:\n";

                Class testClass = Test.class;//获取class

                Object object2=null;
                try {
                    Constructor constructor2=testClass.getConstructor(String.class);
                    object2=constructor2.newInstance("dfhskgf");//构造方法获取Instance
                } catch (Exception e) {
                    e.printStackTrace();
                }


                for(Method method : testClass.getMethods()) {
                    Test.Todo todoAnnotation = (Test.Todo)method.getAnnotation(Test.Todo.class);
                    if(todoAnnotation != null) {
                        s2+=method.getName()+"\n";
                        try {
                            s2+=method.invoke(object2)+"\n";
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mText.setText(s2);
                break;
            case R.id.button3:
                break;
        }
    }
}
