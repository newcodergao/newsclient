package com.demo.newsclient2.home;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class SmartServicePager extends BasePager{

	public SmartServicePager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("智慧服务");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
