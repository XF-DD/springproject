package com.xfdd.springproject.aop.aopimpl.proxy.aspect;

import java.util.Objects;

/**
 * 该类作为 AOP 模型中切面角色类， 实现切面接口
 *
 *
 * @Author: XF-DD
 * @Date: 20/09/13 15:19
 */
public class CustomAspect implements IAspect {

    /**
     * 对参数做判空处理
     * @param args 切点参数列表
     * @return
     */
    @Override
    public boolean startTransaction(Object... args) {
        if (!Objects.nonNull(args)){
            return false;
        }
        boolean result = true;
        for (Object temp :args) {
            if (Objects.isNull(temp)){
                result =false;
                break;
            }
        }
        return result;
    }

    @Override
    public void endTransaction() {
        System.out.println("I get datasource here and end transaction");
    }
}
