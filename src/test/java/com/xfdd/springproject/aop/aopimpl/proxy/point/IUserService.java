package com.xfdd.springproject.aop.aopimpl.proxy.point;

/**
 * 切点角色接口 因为是基于JDK动态代理，所以委托类需要时接口
 *
 * @Author: XF-DD
 * @Date: 20/09/13 15:24
 */
public interface IUserService {
    void saveUser(String username, String password) throws Exception;
}
