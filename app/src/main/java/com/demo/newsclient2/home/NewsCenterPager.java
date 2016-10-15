package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;

import com.demo.newsclient2.R;
import com.demo.newsclient2.utils.LogUtils;

/**
 * 新闻中心
 */
public class NewsCenterPager extends BasePager {

	public NewsCenterPager(Context context) {
		super(context);
	}

	@Override
	protected View initView() {
		View view = View.inflate(mContext, R.layout.news_center_frame, null);
		LogUtils.debugI(this,"view==="+view);
		initTitleBar(view);
		return view;
	}



	@Override
	protected void initData() {

	}
}
