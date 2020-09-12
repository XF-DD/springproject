package com.xfdd.springproject.aop.proxytest;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:13
 */
public interface Person {
    void goWorking(String name, String dst);

    /**
     * 获取名称
     * @return
     */
    String getName( );

    /**
     * 设置名称
     * @param name
     */
    void  setName(String name);
}
