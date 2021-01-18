package com.xfdd.springproject.aop.cglibtest;

import com.xfdd.springproject.util.MonitorUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 13:40
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    /**
     * 用于生成cglib动态代理类工具方法
     * @param target 代表需要被代理的委托类的class对象
     * @return
     */
    public Object cglibProxyGenerator(Class target){
        // 创建加强器，用于创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为代理类指定需要代理的类，即父类
        enhancer.setSuperclass(target);
        // 获取动态代理对象并返回
        enhancer.setCallback(this);
        // 创建动态代理对象
        return enhancer.create();
    }


    /**
     * 功能：在调用业务方法之前，之后添加时间统计
     * todo 1. intercept 因为  具有 MethodProxy proxy 参数的原因 不再需要代理类的引用对象了
     * todo 2. 直接通过proxy 对象访问被代理对象的方法(这种方式更快)
     * todo 3. 也可以通过反射机制，通过 method 引用实例    Object result = method.invoke(target, args); 形式反射调用被代理类方法
     * todo 4. target 实例代表被代理类对象引用, 初始化 CglibMethodInterceptor 时候被赋值 。但是Cglib不推荐使用这种方式
     *
     * @param target 代表cglib生成的动态代理类对象本身(子类)
     * @param method 代理类中被拦截的接口方法
     * @param args 接口方法参数
     * @param methodProxy 用于调用父类真正的业务方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        MonitorUtil.start();
        Object res = methodProxy.invokeSuper(target, args);
        System.out.println("after");
        MonitorUtil.finish(method.getName());
        return res;
    }
}
