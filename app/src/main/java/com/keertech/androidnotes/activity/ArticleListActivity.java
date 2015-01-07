package com.keertech.androidnotes.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.bean.Category;
import com.yftools.LogUtil;
import com.yftools.ViewUtil;
import com.yftools.view.annotation.ViewInject;

/**
 * *****************************************
 * Description ：文章列表
 * Created by cy on 2015/1/7.
 * *****************************************
 */
public class ArticleListActivity extends AbstractToolBarActivity {

    @ViewInject(R.id.listView)
    private ListView listView;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ViewUtil.inject(this);
        category= (Category) getIntent().getSerializableExtra("category");
        LogUtil.d("category name="+category.name);
        getSupportActionBar().setTitle(category.name);
    }
}
