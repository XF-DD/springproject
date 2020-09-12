package com.xfdd.springproject.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: XF-DD
 * @Date: 20/09/12 19:54
 */
public class User {

    private List<String> typeList;

    public void setTypeList(String... typeList){
        this.typeList.add(typeList[0]);
        this.typeList.addAll(Arrays.asList(typeList));
    }

}
