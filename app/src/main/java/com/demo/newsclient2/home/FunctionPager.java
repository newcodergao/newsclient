package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class FunctionPager extends BasePager{

	public FunctionPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mContext);
		tv.setText("首页");
		return tv;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}



}
