package com.keertech.androidnotes.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.activity.ArticleDetailActivity;
import com.keertech.androidnotes.adapter.ArticleListAdapter;
import com.keertech.androidnotes.bean.Article;
import com.keertech.androidnotes.bean.ChildCategory;
import com.keertech.androidnotes.fragment.base.AbstractFragment;
import com.keertech.androidnotes.util.DbOperationManager;
import com.yftools.LogUtil;
import com.yftools.ViewUtil;
import com.yftools.db.sqlite.Selector;
import com.yftools.exception.DbException;
import com.yftools.view.annotation.ViewInject;
import com.yftools.view.annotation.event.OnItemClick;

import java.util.List;

/**
 * *****************************************
 * Description ：
 * Created by cy on 2015/1/8.
 * *****************************************
 */
public class ArticleFragment extends AbstractFragment {

    @ViewInject(R.id.listView)
    private ListView listView;
    private ChildCategory childCategory;
    private List<Article> articleList;
    private ArticleListAdapter adapter;

    public static ArticleFragment newInstance(ChildCategory childCategory) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle b = new Bundle();
        b.putSerializable("childCategory", childCategory);
        articleFragment.setArguments(b);
        return articleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        childCategory= (ChildCategory) getArguments().getSerializable("childCategory");
        View view = inflater.inflate(R.layout.activity_article_list, container, false);
        ViewUtil.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        try {
            articleList = DbOperationManager.getInstance().getBeans(Selector.from(Article.class).where("childCategoryId", "=", childCategory.id));
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
        Intent intent = getActivity().getIntent();
        intent.setClass(mContext, ArticleDetailActivity.class);
        intent.putExtra("article", articleList.get(position));
        startActivity(intent);
    }
}
