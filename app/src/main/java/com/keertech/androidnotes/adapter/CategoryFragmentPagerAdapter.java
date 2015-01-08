package com.keertech.androidnotes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.keertech.androidnotes.bean.ChildCategory;
import com.keertech.androidnotes.fragment.ArticleFragment;

import java.util.List;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<ChildCategory> categoryList;

    public CategoryFragmentPagerAdapter(FragmentManager fm, List<ChildCategory> categoryList) {
        super(fm);
        this.categoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {
        if (categoryList != null) {
            ArticleFragment.newInstance(categoryList.get(position));
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).name;
    }

    @Override
    public int getCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

}