package com.xfdd.springproject.aop.proxytest.target;

import com.xfdd.springproject.aop.proxytest.Person;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import java.lang.reflect.Proxy;

/**
 * 解析生成的代理类
 * @Author: XF-DD
 * @Date: 20/09/13 13:16
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
// TODO ： 继承Proxy 实现 Person
public final class PersonProxy0 extends Proxy implements Person {
    private static Method m1;
    private static Method m4;
    private static Method m3;
    private static Method m2;
    private static Method m5;
    private static Method m0;

    public PersonProxy0(InvocationHandler var1) throws Exception {
        super(var1);
    }

    public final boolean equals(Object var1) {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void setName(String var1) {
        try {
            super.h.invoke(this, m4, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String getName(){
        try {
            return (String)super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    /**
     * TODO 对接口 goWorking 的调用转变成 super.h.invoke(this, m5, new Object[]{var1, var2});
     * @param var1
     * @param var2
     */
    public final void goWorking(String var1, String var2){
        try {
            // TODO h 就是Proxy.java类的一个 InvocationHandler 接口 属性，即创建(PersonProxy0)对象时候传进去的 InvocationHandler
            super.h.invoke(this, m5, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int hashCode(){
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m4 = Class.forName("com.xfdd.springproject.aop.proxytest.Person").getMethod("setName", Class.forName("java.lang.String"));
            m3 = Class.forName("com.xfdd.springproject.aop.proxytest.Person").getMethod("getName");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m5 = Class.forName("com.xfdd.springproject.aop.proxytest.Person").getMethod("goWorking", Class.forName("java.lang.String"), Class.forName("java.lang.String"));
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
