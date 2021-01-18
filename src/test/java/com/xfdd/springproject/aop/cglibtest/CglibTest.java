package com.xfdd.springproject.aop.cglibtest;



import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 13:59
 */
public class CglibTest {
    public static void main(String[] args) throws Exception {
        CglibTest cglibTest = new CglibTest();
       cglibTest.testCglib();
    }

    public void testCglib() throws Exception{
        System.out.println(System.getProperty("user.dir"));
        saveGeneratedCGlibProxyFiles(System.getProperty("user.dir"));

        /** 第一种方法: 创建cglib 代理类 start */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        MethodInterceptor methodInterceptor = new CglibMethodInterceptor();
        enhancer.setCallback(methodInterceptor);
        Dog proxyDog = (Dog) enhancer.create();
        System.out.println(proxyDog.call());

        // 也可以将方法封装起来
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        Dog dog = (Dog)cglibMethodInterceptor.cglibProxyGenerator(Dog.class);
        System.out.println(dog.call());
    }

    /**
     * 保存Cglib
     * @param dir
     * @throws Exception
     */
    public void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
        Field field = System.class.getDeclaredField("props");
        System.out.println(System.class.getName());
        System.out.println(field);
        field.setAccessible(true);
        System.out.println(field.get(this));
        Properties props = (Properties) field.get(null);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,dir);
        props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled",true);


    }
}
