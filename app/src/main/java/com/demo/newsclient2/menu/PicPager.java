package com.demo.newsclient2.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.newsclient2.bean.NewsCenter;
import com.demo.newsclient2.home.BasePager;


/**
 * 组图
 */
public class PicPager extends BasePager {

	public PicPager(Context context, NewsCenter.DataBean dataBean) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("组图");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
