package com.demo.newsclient2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.newsclient2.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by GSJ
 * Date: 2016/10/13
 * Time: 23:59
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected SlidingMenu mSlidingMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mSlidingMenu = ((MainActivity) mContext).getSlidingMenu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  initView(inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();


    protected abstract View initView(LayoutInflater inflater);
}
