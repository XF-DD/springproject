package com.xfdd.springproject.aop.aopimpl.cglib.aspect;

/**
 * @Author: XF-DD
 * @Date: 20/09/13 17:33
 */
public class CustomAspect implements IAspect {
    @Override
    public void startTransaction() {
        System.out.println("I get dataSource here and start transaction");
    }

    @Override
    public void endTransaction() {
        System.out.println("I get dataSource here and end transaction");
    }
}
