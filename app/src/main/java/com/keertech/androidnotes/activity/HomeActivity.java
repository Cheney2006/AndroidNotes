package com.keertech.androidnotes.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.bean.Category;
import com.keertech.androidnotes.util.DbOperationManager;
import com.yftools.LogUtil;
import com.yftools.exception.DbException;
import com.yftools.util.StorageUtil;


public class HomeActivity extends AbstractToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.app_name);
        try {
            DbOperationManager.getInstance().getBeans(Category.class);
        } catch (DbException e) {
            LogUtil.e(e);
        }
    }
}
