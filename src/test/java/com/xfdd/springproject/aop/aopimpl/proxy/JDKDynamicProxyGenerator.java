package com.xfdd.springproject.aop.aopimpl.proxy;


import com.xfdd.springproject.aop.aopimpl.proxy.aspect.IAspect;
import com.xfdd.springproject.aop.aopimpl.proxy.point.IUserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 15:33
 */
public class JDKDynamicProxyGenerator {

    /**
     *
     * @param targetPoint 需要被代理的委托类对象
     * @param aspect 切面对象，该对象方法将在切点方法之前或之后执行
     * @return
     */
    public static Object generatorJDKProxy(IUserService targetPoint, final IAspect aspect){

        return Proxy.newProxyInstance(
                targetPoint.getClass().getClassLoader(),
                targetPoint.getClass().getInterfaces(),
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 执行切面方法,对入参进行校验
                        boolean prepareAction = aspect.startTransaction(args);
                        if(!prepareAction){
                            throw new RuntimeException("args : "+ Arrays.toString(args)+"不能为null ");
                        }
                        Object result = method.invoke(targetPoint, args);
                        aspect.endTransaction();
                        return result;
                    }
                }
        );
    }
}
