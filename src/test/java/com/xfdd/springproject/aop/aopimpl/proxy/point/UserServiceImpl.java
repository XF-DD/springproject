package com.xfdd.springproject.aop.aopimpl.proxy.point;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 15:27
 */
public class UserServiceImpl implements IUserService {

    @Override
    public void saveUser(String username, String password) throws Exception {
        System.out.println("save user [username = " + username + ",password = " + password + "]");
    }


}
