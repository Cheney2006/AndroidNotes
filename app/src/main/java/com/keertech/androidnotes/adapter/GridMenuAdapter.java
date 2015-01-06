package com.keertech.androidnotes.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keertech.androidnotes.R;
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
public class GridMenuAdapter extends AbstractAdapter<Category> {

    int[] icons = {R.drawable.icon_basic, R.drawable.icon_view, R.drawable.icon_component,
            R.drawable.icon_database, R.drawable.icon_java, R.drawable.icon_high,
            R.drawable.icon_question, R.drawable.icon_optimize
    };

    public GridMenuAdapter(Context context, List<Category> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = getInflater().inflate(R.layout.item_grid_menu, null);
            ViewUtil.inject(viewHolder, view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.icon_iv.setImageResource(icons[position]);
        viewHolder.name_tv.setText(getItem(position).name);
        return view;
    }

    static class ViewHolder {
        @ViewInject(R.id.icon_iv)
        private ImageView icon_iv;
        @ViewInject(R.id.name_tv)
        private TextView name_tv;
    }
}
