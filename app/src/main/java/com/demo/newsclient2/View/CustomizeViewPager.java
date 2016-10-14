package com.demo.newsclient2.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Created by GSJ
 * Date: 2016/10/13
 * Time: 19:43
 */
public class CustomizeViewPager extends ViewPager {
    public CustomizeViewPager(Context context) {
        super(context);
    }

    public CustomizeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private HashMap<Integer,View>  mViewMap=new HashMap<>();
    public void addView(int position,View view){
        mViewMap.put(position,view);
    }
    public void removeView(int position){
        mViewMap.remove(position);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        View mLeftView = mViewMap.get(position);
        View mRightView=mViewMap.get(position+1);
        startAnimation(mLeftView,mRightView,offset,offsetPixels);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void startAnimation(View mLeftView, View mRightView, float offset, int offsetPixels) {
        if(mLeftView!=null){
           mLeftView.bringToFront();
        }

        if(mRightView!=null){
          float  mTranslationX=-getMeasuredWidth()+offsetPixels;
             mRightView.setTranslationX(mTranslationX);
            mRightView.setScaleX(0.25f*offset+0.75f);
            mRightView.setScaleY(0.25f*offset+0.75f);
        }

    }
}
