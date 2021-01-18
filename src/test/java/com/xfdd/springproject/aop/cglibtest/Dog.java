package com.xfdd.springproject.aop.cglibtest;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 13:39
 */
public class Dog {
    public String call(){
        System.out.println("被代理狗：旺旺！！");
        return "Dog call()";
    }
}
