package com.xfdd.springproject.aop.aopimpl.cglib.point;

/**
 * Cglib是基于类实现的动态代理即业务类只需要实现类即可
 * 不用强制必须实现某个接口
 * 为了突出这个优点这里没有实现接口
 * @Author: XF-DD
 * @Date: 20/09/13 17:34
 */

public class UserServiceImpl {
    public void saveUser(String username,String password){
        System.out.println("cglib save user [username = "+username+",password = " + password +"]");
    }
}
