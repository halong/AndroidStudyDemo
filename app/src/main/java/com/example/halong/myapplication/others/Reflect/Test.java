package com.example.halong.myapplication.others.Reflect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by halong on 2017/12/21.
 */

public class Test {
    private String text = "hello";

    public Test(String text) {
        this.text = text;
    }

    @Todo
    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public static String test() {
        return "你哈";
    }


    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME) //自定义注解 需要要到反射来获取被注解的属性或方法的话，需要RUNTIME
    @interface Todo {
    }
}
