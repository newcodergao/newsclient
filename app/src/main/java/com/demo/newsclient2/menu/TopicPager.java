package com.demo.newsclient2.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.newsclient2.bean.NewsCenter;
import com.demo.newsclient2.home.BasePager;


/**
 * 专题
 */
public class TopicPager extends BasePager {

	public TopicPager(Context context, NewsCenter.DataBean dataBean) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("专题");
		return textView;
	}

	@Override
	public void initData() {

	}

}
