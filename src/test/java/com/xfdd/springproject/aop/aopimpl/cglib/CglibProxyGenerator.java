package com.xfdd.springproject.aop.aopimpl.cglib;


import com.xfdd.springproject.aop.aopimpl.cglib.aspect.IAspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 18:10
 */
public class CglibProxyGenerator {

    public static Object generatorCglibProxy(final Object target, final IAspect aspect){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                aspect.startTransaction();
                Object res = proxy.invokeSuper(obj, args);
                aspect.endTransaction();
                return res;
            }
        });
        return enhancer.create();
    }
}
