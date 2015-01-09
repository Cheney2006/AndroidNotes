/*
 * Copyright (C) 2014 Antonio Leiva Gordillo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keertech.androidnotes.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.keertech.androidnotes.R;
/**
 * *****************************************
 * Description ：toolbar 作为actionbar固定在头部
 * Created by cy on 2015/1/8.
 * *****************************************
 */
public abstract class AbstractTopBarActivity extends AbstractActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        init().addView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        getLayoutInflater().inflate(layoutResID, init(), true);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        init().addView(view, params);
    }

    private ViewGroup init() {
        super.setContentView(R.layout.abstract_topbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Menu item click 的监听事件一樣要在设定 setSupportActionBar后 才有作用
        //toolbar.setOnMenuItemClickListener(onMenuItemClick);
        return (ViewGroup) findViewById(R.id.content_layout);
    }

    public void hideHomeAsUp() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void setActionBarIcon(int iconRes) {
        // Navigation Icon 要设定在setSupoortActionBar后 才有作用
        toolbar.setNavigationIcon(iconRes);
    }

    protected void setTitle(String title) {
        //Title要在设定 setSupoortActionBar前才有作用
        //toolbar.setTitle(title);//
        getSupportActionBar().setTitle(title);
    }

    public void setTitle(int id) {
        getSupportActionBar().setTitle(id);
        //Title要在设定 setSupoortActionBar前才有作用
        //toolbar.setTitle(getResources().getString(id));
    }


}
