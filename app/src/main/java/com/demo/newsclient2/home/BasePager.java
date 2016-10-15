package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.newsclient2.R;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 14:12
 * 主页的基础pager
 */

public abstract class BasePager {
    protected Context mContext;
    protected View view;
    protected TextView txt_title;
    public BasePager(Context context) {
        this.mContext=context;
        view=initView();

    }
    protected abstract View initView();
    protected abstract void initData();
    public View getRootView(){
//        return initView();//在界面上显示不了视图
        return view;//可以在界面上显示视图
    }

    public void initTitleBar(View view) {
        //左边的按钮
        Button btn_left = (Button) view.findViewById(R.id.btn_left);
        //隐藏左边的按钮
        btn_left.setVisibility(View.GONE);

        ImageButton imgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);

        imgbtn_left.setVisibility(View.VISIBLE);
        //设置菜单
        imgbtn_left.setImageResource(R.mipmap.img_menu);
        //保证左右对齐。需要隐藏右边的按钮
        ImageButton btn_right = (ImageButton) view.findViewById(R.id.btn_right);

        btn_right.setVisibility(View.GONE);
        //设置标题
        txt_title = (TextView) view.findViewById(R.id.txt_title);
    }
}
