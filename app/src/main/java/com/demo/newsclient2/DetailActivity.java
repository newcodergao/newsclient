package com.demo.newsclient2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by GSJ
 * Date: 2016/10/19
 * Time: 09:27
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {

    }

    private void initView() {

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
    }
}
