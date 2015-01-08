package com.keertech.androidnotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
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
import com.yftools.db.sqlite.WhereBuilder;
import com.yftools.exception.DbException;
import com.yftools.view.annotation.ViewInject;
import com.yftools.view.annotation.event.OnItemClick;

import java.util.List;

/**
 * *****************************************
 * Description ：文章列表
 * Created by cy on 2015/1/7.
 * *****************************************
 */
public class ArticleListActivity extends AbstractTopBarActivity implements SearchView.OnQueryTextListener {

    @ViewInject(R.id.listView)
    private ListView listView;
    private Category category;
    private List<Article> articleList;
    private ArticleListAdapter adapter;

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
            fillList();
        } catch (DbException e) {
            LogUtil.e(e);
        }
    }

    private void fillList() {
        if(adapter==null){
            adapter=new ArticleListAdapter(mContext,articleList);
            listView.setAdapter(adapter);
        }else{
            adapter.setDataList(articleList);
            adapter.notifyDataSetChanged();
        }
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = getIntent();
        intent.setClass(mContext, ArticleDetailActivity.class);
        intent.putExtra("article", articleList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
         /* SearchView配置 */
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        /* ShareActionProvider配置 */
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu
                .findItem(R.id.action_share));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        mShareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        LogUtil.d("onQueryTextSubmit="+s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        LogUtil.d("onQueryTextChange="+s);
        try {
            String key = "%" + s + "%";
            articleList = DbOperationManager.getInstance().getBeans(Selector.from(Article.class).where("categoryId", "=", category.id).and(WhereBuilder.b("title", "like", key).or("content", "like", key)));
            fillList();
        } catch (DbException e) {
            LogUtil.e(e);
        }
        return false;
    }
}
