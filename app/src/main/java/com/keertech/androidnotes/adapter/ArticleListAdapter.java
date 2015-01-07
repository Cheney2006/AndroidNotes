package com.keertech.androidnotes.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.bean.Article;
import com.keertech.androidnotes.bean.Category;
import com.yftools.ViewUtil;
import com.yftools.view.annotation.ViewInject;

import java.util.List;

/**
 * *****************************************
 * Description ：首页菜单
 * Created by cywf on 2015/1/5.
 * *****************************************
 */
public class ArticleListAdapter extends AbstractAdapter<Article> {

    public ArticleListAdapter(Context context, List<Article> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = getInflater().inflate(R.layout.item_list_article, null);
            ViewUtil.inject(viewHolder, view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title_tv.setText(getItem(position).title);
        viewHolder.subTitle_tv.setText(getItem(position).content);
        return view;
    }

    static class ViewHolder {
        @ViewInject(R.id.title_tv)
        private TextView title_tv;
        @ViewInject(R.id.subTitle_tv)
        private TextView subTitle_tv;
    }
}
