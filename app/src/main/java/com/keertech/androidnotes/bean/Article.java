package com.keertech.androidnotes.bean;

import com.keertech.androidnotes.bean.base.AbstractBean;
import com.yftools.db.annotation.Table;

/**
 * *****************************************
 * Description ：具体文章
 * Created by cywf on 2015/1/7.
 * *****************************************
 */
@Table(name = "t_article")
public class Article extends AbstractBean {
    public String title;
    public String content;
    public Integer categoryId;
    public Integer childCategoryId;
}
