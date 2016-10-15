package com.demo.newsclient2.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 12:49
 */
public class CustomLazyViewPager extends LazyViewPager {
    public CustomLazyViewPager(Context context) {
        super(context);
    }

    public CustomLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);
        return false;
    }
}
