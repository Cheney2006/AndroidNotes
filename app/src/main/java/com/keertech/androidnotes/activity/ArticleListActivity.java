package com.keertech.androidnotes.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.adapter.ArticleListAdapter;
import com.keertech.androidnotes.bean.Article;
import com.keertech.androidnotes.bean.Category;
import com.keertech.androidnotes.util.DbOperationManager;
import com.yftools.LogUtil;
import com.yftools.ViewUtil;
import com.yftools.db.sqlite.Selector;
import com.yftools.exception.DbException;
import com.yftools.view.annotation.ViewInject;
import com.yftools.view.annotation.event.OnChildClick;
import com.yftools.view.annotation.event.OnItemClick;

import java.util.List;

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
    private List<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ViewUtil.inject(this);
        category = (Category) getIntent().getSerializableExtra("category");
        setTitle(category.name);
        initData();
    }

    private void initData() {
        try {
            articleList = DbOperationManager.getInstance().getBeans(Selector.from(Article.class).where("categoryId", "=", category.id));
            listView.setAdapter(new ArticleListAdapter(mContext, articleList));
        } catch (DbException e) {
            LogUtil.e(e);
        }
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
