package com.xfdd.springproject.util;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:22
 */
public class MonitorUtil {
    private static ThreadLocal<Long> tl = new ThreadLocal<>();    public static void start() {
        tl.set(System.currentTimeMillis());
    }    /**
     * 结束时打印耗时
     * @param methodName 方法名
     */
    public static void finish(String methodName) {        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "方法执行耗时" + (finishTime - tl.get()) + "ms");
    }
}
