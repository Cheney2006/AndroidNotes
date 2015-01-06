package com.keertech.androidnotes.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.adapter.GridMenuAdapter;
import com.keertech.androidnotes.bean.Category;
import com.keertech.androidnotes.util.DbOperationManager;
import com.yftools.LogUtil;
import com.yftools.ViewUtil;
import com.yftools.exception.DbException;
import com.yftools.view.annotation.ViewInject;

import java.util.List;


public class HomeActivity extends AbstractToolBarActivity {

    @ViewInject(R.id.gridView)
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.app_name);
        ViewUtil.inject(this);
        try {
            List<Category> categoryList = DbOperationManager.getInstance().getBeans(Category.class);
            LogUtil.d(categoryList.size()+"");
            gridView.setAdapter(new GridMenuAdapter(mContext, categoryList));
        } catch (DbException e) {
            LogUtil.e(e);
        }
    }
}
