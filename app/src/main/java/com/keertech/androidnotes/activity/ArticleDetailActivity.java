package com.keertech.androidnotes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.keertech.androidnotes.R;
import com.keertech.androidnotes.bean.Article;
import com.keertech.androidnotes.bean.Category;
import com.yftools.ViewUtil;
import com.yftools.view.annotation.ContentView;
import com.yftools.view.annotation.ViewInject;

/**
 * *****************************************
 * Description ：详情
 * Created by cy on 2015/1/8.
 * *****************************************
 */
@ContentView(R.layout.activity_article_detail)
public class ArticleDetailActivity extends AbstractTopBarActivity {

    @ViewInject(R.id.title_tv)
    private TextView title_tv;
    @ViewInject(R.id.content_tv)
    private TextView content_tv;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtil.inject(this);
        Category category = (Category) getIntent().getSerializableExtra("category");
        setTitle(category.name);
        article= (Article) getIntent().getSerializableExtra("article");
        initData();
    }

    private void initData() {
        title_tv.setText(article.title);
        content_tv.setText(article.content);
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
