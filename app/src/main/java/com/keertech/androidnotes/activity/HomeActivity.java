package com.keertech.androidnotes.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.keertech.androidnotes.R;


public class HomeActivity extends AbstractToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.app_name);
        ListView mListView = (ListView) findViewById(R.id.listView);

    }
}
