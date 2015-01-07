package com.keertech.androidnotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.adapter.GridMenuAdapter;
import com.keertech.androidnotes.bean.Category;
import com.keertech.androidnotes.util.DbOperationManager;
import com.yftools.LogUtil;
import com.yftools.ViewUtil;
import com.yftools.exception.DbException;
import com.yftools.view.annotation.ViewInject;
import com.yftools.view.annotation.event.OnItemClick;

import java.util.List;


public class HomeActivity extends AbstractToolBarActivity {

    @ViewInject(R.id.gridView)
    private GridView gridView;
    private GridMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ViewUtil.inject(this);
        try {
            List<Category> categoryList = DbOperationManager.getInstance().getBeans(Category.class);
            LogUtil.d(categoryList.size() + "");
            adapter = new GridMenuAdapter(mContext, categoryList);
            gridView.setAdapter(adapter);
        } catch (DbException e) {
            LogUtil.e(e);
        }
    }

    @OnItemClick(R.id.gridView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, ArticleListActivity.class);
        intent.putExtra("category", adapter.getItem(position));
        startActivity(intent);
    }
}
