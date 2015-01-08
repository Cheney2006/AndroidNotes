package com.keertech.androidnotes.bean;

import com.keertech.androidnotes.bean.base.AbstractBean;
import com.yftools.db.annotation.Table;

/**
 * *****************************************
 * Description ：主分类
 * Created by cywf on 2015/1/5.
 * *****************************************
 */
@Table(name = "t_child_category")
public class ChildCategory extends AbstractBean {

    public String name;

    public Integer categoryId;
}
