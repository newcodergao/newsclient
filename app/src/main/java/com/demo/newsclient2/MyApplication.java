package com.demo.newsclient2;

import android.app.Application;

import org.xutils.x;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 17:41
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       x.Ext.init(this);
        x.Ext.setDebug(true);

    }
}
