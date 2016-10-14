package com.demo.newsclient2.home;

import android.content.Context;
import android.view.View;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 14:12
 * 主页的基础pager
 */

public abstract class BasePager {
    Context mContext;
    public BasePager(Context context) {
        this.mContext=context;

    }
    protected abstract View initView();
    protected abstract void initData();
    public View getRootView(){
        return initView();
    }


}
