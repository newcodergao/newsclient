package com.demo.newsclient2.menu;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.demo.newsclient2.R;
import com.demo.newsclient2.bean.NewsCenter;
import com.demo.newsclient2.home.BasePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 
 *新闻
 * 
 */
public class NewsPager extends BasePager {

	//viewpager的指示器
	@Bind(R.id.indicator)
	TabPageIndicator mTabPageIndicator;
	@Bind(R.id.pager)
	ViewPager mViewPager;
	NewsCenter.DataBean dataBean;
	public NewsPager(Context context, NewsCenter.DataBean dataBean) {
		super(context);
		this.dataBean=dataBean;
	}

	@Override
	protected View initView() {
		View view = View.inflate(mContext, R.layout.simple_tabs, null);
		ButterKnife.bind(this, view);
		return view;
	}
	// 初始化新闻的条目数据
	private List<NewsItemPager> mNewsItemDatas = new ArrayList<NewsItemPager>();
	//当前的位置
	private int mCurrentPostion = 0;
		int currentPosition=0;
	@Override
	public void initData() {
		List<NewsCenter.DataBean.ChildrenBean> children = dataBean.getChildren();
		for (int i = 0; i < children.size(); i++) {
			mNewsItemDatas.add(new NewsItemPager(mContext,children.get(i).getUrl()));
		}
		NewsPagerAdapter adapter=new NewsPagerAdapter();
		mViewPager.setAdapter(adapter);
		mTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				currentPosition=position;
				if(!mNewsItemDatas.get(position).isLoaded){

				mNewsItemDatas.get(position).initData();//展示新闻条目的数据
				}

				if(position==0){
				  mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}else{
					mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

				}

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				switch(state){


				}

			}
		});

		mTabPageIndicator.setViewPager(mViewPager);
		mTabPageIndicator.setCurrentItem(currentPosition);

		mNewsItemDatas.get(currentPosition).initData();
		mNewsItemDatas.get(currentPosition).isLoaded=true;

	}

	private class NewsPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mNewsItemDatas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mNewsItemDatas.get(position).getRootView());
			return mNewsItemDatas.get(position).getRootView();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		   container.removeView(mNewsItemDatas.get(position).getRootView());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return dataBean.getChildren().get(position).getTitle();
		}
	}
}
