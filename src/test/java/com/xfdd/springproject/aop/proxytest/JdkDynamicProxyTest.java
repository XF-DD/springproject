package com.xfdd.springproject.aop.proxytest;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:25
 */
public class JdkDynamicProxyTest {
    public static void main(String[] args) throws Exception {
//        JdkDynamicProxyTest.firstMethod();
        JdkDynamicProxyTest.secondMethod();
    }

    // 第一种方法
    private static void firstMethod(){

        Person person = new SoftwareEngineer("Vincent");
        // 创建一个与代理类相关联的InvocationHandler,每一个代理类都有一个关联的 InvocationHandler，并将代理类引用传递进去
        PersonInvocationHandler<Person> handler = new PersonInvocationHandler<>(person);
        // 创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
        personProxy.goWorking(personProxy.getName(),"深圳");
    }

    // 第二种方法
    /** 步骤
     *  1. 创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
     *  2. Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；
     *      JDK动态代理的特点是代理类必须继承Proxy类
     *  3. 通过代理类 proxyClass 获得他的 带InvocationHandler接口 的构造函数 ProxyConstructor
     *  4、通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。
     */
    public static void secondMethod() throws Exception {
        // todo 1. 创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
        Person person = new SoftwareEngineer("Vincent");
        InvocationHandler handler = new PersonInvocationHandler<>(person);

        // todo 2. Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), Person.class);

        System.out.println("==========2. 生成stuProxyClass=============");
        System.out.println("package = " + proxyClass.getPackage());
        System.out.println("SimpleName = " + proxyClass.getName());
        System.out.println("CanonicalName = " + proxyClass.getCanonicalName());
        System.out.println("实现的接口，Interface = " + Arrays.toString(proxyClass.getInterfaces()));
        System.out.println("superClass = " + proxyClass.getSuperclass());
        System.out.println("method = " + Arrays.toString(proxyClass.getMethods()));

        // todo 3. 通过代理类 proxyClass 获得他的 带InvocationHandler接口 的构造函数 ProxyConstructor
        Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);

        // todo 4. 通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。
        Person stuProxy = (Person) proxyClassConstructor.newInstance(handler);
        System.out.println("==========4. stuProxy=============");
        System.out.println("stuProxy isProxy "+Proxy.isProxyClass(stuProxy.getClass()));
        stuProxy.goWorking(stuProxy.getName(),"广州");

        // todo 其他测试
        //
        InvocationHandler handlerObject = Proxy.getInvocationHandler(stuProxy);
        System.out.println(handlerObject.getClass().getName());

        // 保存代理类 , 可见target目录
        saveClass("$PersonProxy0", proxyClass.getInterfaces(), "/123/");

    }



    /**
     *  生成代理类class 并保持到文件中
     *
     * @param className 生成的代理类的名字
     * @param interfaces 代理类需要实现的接口
     * @param path 代理类保存的目录路径，目录分隔符结尾
     */
    public static void saveClass(String className, Class<?>[] interfaces, String path){
        byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);

        // 如果目录不存在就新建所有子目录
        Path pathDir = Paths.get(path);
        if (!pathDir.toFile().exists()){
            pathDir.toFile().mkdirs();
        }
        String finalPath = path + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(finalPath)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

    /**
     * 设置保存Java动态代理生成的类文件。
     *
     * @throws Exception
     */
    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
