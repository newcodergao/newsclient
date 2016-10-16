package com.demo.newsclient2.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.newsclient2.home.BasePager;

/**
 * 
 * 新闻的条目数据
 *
 */
public class NewsItemPager extends BasePager {
    /**
     * 
     * @param context
     * @param url  新闻条目的url数据
     */
	public NewsItemPager(Context context, String url) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("新闻的条目数据");
		return textView;
	}

	@Override
	public void initData() {

	}

}
