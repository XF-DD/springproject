package com.xfdd.springproject.aop.aopimpl.cglib.aspect;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 17:31
 */
public interface IAspect {
    /**
     * 在切点接口方法执行之前执行
     */
    void startTransaction();

    /**
     * 在切点接口方法执行之后执行
     */
    void endTransaction();
}
