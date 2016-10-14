package com.demo.newsclient2.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.demo.newsclient2.R;
import com.demo.newsclient2.View.CustomLazyViewPager;
import com.demo.newsclient2.View.LazyViewPager;
import com.demo.newsclient2.home.BasePager;
import com.demo.newsclient2.home.FunctionPager;
import com.demo.newsclient2.home.GovAffairsPager;
import com.demo.newsclient2.home.NewsCenterPager;
import com.demo.newsclient2.home.SettingPager;
import com.demo.newsclient2.home.SmartServicePager;
import com.demo.newsclient2.utils.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 00:01
 */
public class HomeFragment extends BaseFragment implements LazyViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.main_radio)
     RadioGroup main_radio;
//    @Bind(R.id.view_pager)
     CustomLazyViewPager mViewPager;
    private List<BasePager> mDatas;

    @Override
    protected void initData() {
        main_radio.setOnCheckedChangeListener(this);
        mDatas = new ArrayList<>();
        mDatas.add(new FunctionPager(mContext));
        mDatas.add(new NewsCenterPager(mContext));
        mDatas.add(new SmartServicePager(mContext));
        mDatas.add(new GovAffairsPager(mContext));
        mDatas.add(new SettingPager(mContext));
        LogUtils.debugI(this,"mViewPager=="+mViewPager);

        mViewPager.setOnPageChangeListener(this);

        HomeAdapter adapter=new HomeAdapter();
        mViewPager.setAdapter(adapter);
//        mViewPager.setCurrentItem(1);
        main_radio.check(R.id.rb_news_center);
    }
    private class HomeAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDatas.get(position).getRootView());
            return mDatas.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDatas.get(position).getRootView());
        }
    }
    @Override
    protected View initView( LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_home, null);
        ButterKnife.bind(this,view);
        mViewPager= (CustomLazyViewPager) view.findViewById(R.id.view_pager);
        LogUtils.debugI(this,"mViewPager=="+mViewPager);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
           case R.id.rb_function:
               mViewPager.setCurrentItem(0);
               mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_NONE);
           break;
            case R.id.rb_news_center:
                mViewPager.setCurrentItem(1);
                mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

                break;
            case R.id.rb_smart_service:
                mViewPager.setCurrentItem(2);
                mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

                break;
            case R.id.rb_gov_affairs:
                mViewPager.setCurrentItem(3);
                mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

                break;
            case R.id.rb_setting:
                mViewPager.setCurrentItem(4);
                mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_NONE);

                break;

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
