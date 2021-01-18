package com.xfdd.springproject.aop.aopimpl.proxy.aspect;

/**
 *  定义切面接口，切面接口定义了两个方法，分别在切点接口方法执行前、后执行
 * @Author: XF-DD
 * @Date: 20/09/13 15:17
 */
public interface IAspect {

    /**
     * 在切点接口方法执行之前执行,判断方法是否执行
     * @param args 切点参数列表
     * @return
     */
    boolean startTransaction(Object... args);

    /**
     * 在切点接口方法执行之后执行
     */
    void endTransaction();

}
