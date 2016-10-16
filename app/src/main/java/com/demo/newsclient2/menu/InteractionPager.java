package com.demo.newsclient2.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.newsclient2.bean.NewsCenter;
import com.demo.newsclient2.home.BasePager;

/**
 * 
 * 互动
 *
 */
public class InteractionPager extends BasePager {

	public InteractionPager(Context context, NewsCenter.DataBean url) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("互动");
		return textView;
	}

	@Override
	public void initData() {

	}

}
