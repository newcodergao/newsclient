package com.demo.newsclient2.home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * 新闻中心
 */
public class NewsCenterPager extends BasePager {

	public NewsCenterPager(Context context) {
		super(context);
	}

	@Override
	protected View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("新闻中心");
		textView.setTextColor(Color.parseColor("#000000"));
		return textView;
	}

	@Override
	protected void initData() {

	}
}
