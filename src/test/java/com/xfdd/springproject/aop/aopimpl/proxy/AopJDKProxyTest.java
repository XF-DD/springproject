package com.xfdd.springproject.aop.aopimpl.proxy;

import com.xfdd.springproject.aop.aopimpl.proxy.aspect.CustomAspect;
import com.xfdd.springproject.aop.aopimpl.proxy.point.IUserService;
import com.xfdd.springproject.aop.aopimpl.proxy.point.UserServiceImpl;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 17:22
 */
public class AopJDKProxyTest {
    public static void main(String[] args) throws Exception{
        AopJDKProxyTest aopJDKProxyTest = new AopJDKProxyTest();
        System.out.println("========without=========");
        aopJDKProxyTest.withoutAOP();
        System.out.println();
        System.out.println();
        System.out.println("========with=========");
        aopJDKProxyTest.withAOP();

    }

    public void withoutAOP() throws Exception{
        UserServiceImpl userService = new UserServiceImpl();
        userService.saveUser("zs","123");
    }

    public void withAOP() throws Exception{
        IUserService userService = new UserServiceImpl();
        IUserService userServiceProxy = (IUserService) JDKDynamicProxyGenerator.generatorJDKProxy(userService, new CustomAspect());
        userServiceProxy.saveUser("zs","123");
        userServiceProxy.saveUser(null,null);
    }
}
