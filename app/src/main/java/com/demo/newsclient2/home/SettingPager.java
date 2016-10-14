package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SettingPager extends BasePager {

	public SettingPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("设置");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
