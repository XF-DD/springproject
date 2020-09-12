package com.xfdd.springproject.aop.proxytest;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:25
 */
public class JdkDynamicProxyTest {
    public static void main(String[] args) {
        JdkDynamicProxyTest.firstMethod();
    }

    public static void firstMethod(){

        Person person = new SoftwareEngineer("Vincent");
        // 创建一个与代理类相关联的InvocationHandler,每一个代理类都有一个关联的 InvocationHandler，并将代理类引用传递进去
        PersonInvocationHandler<Person> handler = new PersonInvocationHandler<>(person);
        // 创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
        personProxy.goWorking(personProxy.getName(),"深圳");

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
