package com.xfdd.springproject.aop.proxytest;

import com.xfdd.springproject.util.MonitorUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:16
 */
public class PersonInvocationHandler<T> implements InvocationHandler {

    private T target;

    public PersonInvocationHandler(T target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代表动态生成的 动态代理 对象实例
     * @param method 代表被调用委托类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
     * @param args 代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型。
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被动态代理回调执行，代理类 ProxyClass = " + proxy.getClass());
        System.out.println("被动态代理回调执行，方法 method 名 = " + method.getName());
        System.out.println("被动态代理回调执行，方法 method 返回 = " + method.getReturnType());
        System.out.println("被动态代理回调执行，参数 args = " + (args ==null ? "null" : Arrays.toString(args)));
        MonitorUtil.start();
        TimeUnit.MILLISECONDS.sleep(1);
        Object res = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return res;
    }
}
