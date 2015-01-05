package com.keertech.androidnotes.bean;

import com.yftools.db.annotation.Table;

/**
 * *****************************************
 * Description ：主分类
 * Created by cywf on 2015/1/5.
 * *****************************************
 */
@Table(name = "t_category")
public class Category extends AbstractBean {

    public String name;
}
