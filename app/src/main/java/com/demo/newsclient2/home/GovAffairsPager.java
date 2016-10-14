package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class GovAffairsPager extends BasePager{

	public GovAffairsPager(Context context) {
		super(context);
	}


	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("政务信息");
		return textView;
	}


	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
