package com.xfdd.springproject.aop.proxytest;

/**
 * @Author: XF-DD
 * @Date: 20/09/09 16:14
 */
public class SoftwareEngineer implements Person {

    private String name;

    public SoftwareEngineer(String name) {
        this.name = name;
    }

    @Override
    public void goWorking(String name, String dst) {
        System.out.println("name ="+name+" ， 去 "+dst +" 工作");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
