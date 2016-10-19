package com.demo.newsclient2.menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.newsclient2.DetailActivity;
import com.demo.newsclient2.R;
import com.demo.newsclient2.View.RollViewPager;
import com.demo.newsclient2.View.pulltorefreshview.PullToRefreshBase;
import com.demo.newsclient2.View.pulltorefreshview.PullToRefreshListView;
import com.demo.newsclient2.adapter.NewsItemPagerAdapter;
import com.demo.newsclient2.bean.NewsItem;
import com.demo.newsclient2.home.BasePager;
import com.demo.newsclient2.utils.CommonUtil;
import com.demo.newsclient2.utils.Constants;
import com.demo.newsclient2.utils.GsonTools;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 新闻的条目数据
 *
 */
public class NewsItemPager extends BasePager {

	private View topView;
	String url;
	@ViewInject(R.id.dots_ll)
	private LinearLayout dots_ll;// 得到轮播图的点
	@ViewInject(R.id.top_news_title)
	private TextView top_news_title;// 文本控件
//	@ViewInject(R.id.lv_item_news)
	private PullToRefreshListView mListView;
	@ViewInject(R.id.top_news_viewpager)
	private LinearLayout mTopNewsViewpager;
	private RollViewPager mRollViewPager;

	/**
     * 
     * @param context
     * @param url  新闻条目的url数据
     */
	public NewsItemPager(Context context, String url) {
		super(context);
		this.url = url;
	}

	@Override
	public View initView() {
		// 轮播图

		topView = View.inflate(mContext, R.layout.layout_roll_view, null);
		// listview
		View view = View.inflate(mContext, R.layout.frag_item_news, null);
		x.view().inject(this, topView);
		x.view().inject(this, view);
		mListView = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
		mListView.setScrollLoadEnabled(true);
		mListView.setPullLoadEnabled(true);
		mListView.setPullRefreshEnabled(true);
		mListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NewsItem.DataBean.NewsBean newsBean = (NewsItem.DataBean.NewsBean) mListView.getRefreshableView().getItemAtPosition(position);
				String url = newsBean.getUrl();
				boolean read = newsBean.isRead();
				if(!read){
				   newsBean.setRead(true);
				}
				adapter.notifyDataSetChanged();
				Intent intent=new Intent(mContext, DetailActivity.class);
				intent.putExtra("url",url);
				mContext.startActivity(intent);
			}
		});
		mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                         getNewsItemData(true);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				  getNewsItemData(false);

			}
		});
		mListView.setLastUpdatedLabel("设置时间");
		return view;
	}

	@Override
	public void initData() {
		getNewsItemData(true);

	}

	private void getNewsItemData(final boolean isrefresh) {
		RequestParams params=new RequestParams(Constants.BASE_URL+url);
		params.setConnectTimeout(6000);
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				processData(result,isrefresh);
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {

			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {

			}
		});

	}
	List<NewsItem.DataBean.NewsBean> news = new ArrayList<>();

	private NewsItemPagerAdapter adapter;
	private void processData(String result, boolean isrefresh) {
		NewsItem newsItem = GsonTools.changeGsonToBean(result, NewsItem.class);
		if(newsItem.getRetcode()==200){
			if(newsItem.getData()!=null){
				if(isrefresh) {


					List<NewsItem.DataBean.TopnewsBean> topnews = newsItem.getData().getTopnews();
					initDot(topnews.size());
					// 初始化文本的标题
					List<String> mTitleLists = new ArrayList<String>();
					// 初始化图片地址的集合
					List<String> mImageLists = new ArrayList<String>();
					for (int i = 0; i < topnews.size(); i++) {
						mTitleLists.add(topnews.get(i).getTitle());
						mImageLists.add(topnews.get(i).getTopimage());
					}

					mRollViewPager = new RollViewPager(mContext, mDotLists);
					mRollViewPager.setImageRes(mImageLists);
					mRollViewPager.setTextTitle(top_news_title, mTitleLists);
					mRollViewPager.start();
					//将viewpager设置到布局中
					mTopNewsViewpager.removeAllViews();
					mTopNewsViewpager.addView(mRollViewPager);
					if (mListView.getRefreshableView().getHeaderViewsCount() < 1) {
						mListView.getRefreshableView().addHeaderView(topView);
					}
				}
//					String moreUrl = newsItem.getData().getMore();
//					if(!TextUtils.isEmpty(moreUrl)){
//					   mListView.setHasMoreData(true);
//					}else {
//						mListView.setHasMoreData(false);
//					}

				  if(isrefresh){
					  // 下拉刷新只加载当前页面的数据
					  news.clear();
					  news.addAll(newsItem.getData().getNews());
				  }else {
					  // 滚动加载
					  news.addAll(newsItem.getData().getNews());

				  }
					// 判断当前的新闻是否有数据
					if (null != news) {

						if (null == adapter) {
							adapter = new NewsItemPagerAdapter(news,
									mContext);
							mListView.getRefreshableView().setAdapter(adapter);
						} else {
							adapter.notifyDataSetChanged();
						}

					}

				mListView.onPullUpRefreshComplete();
				mListView.onPullDownRefreshComplete();
			}

		}
	}
	// 封装点的集合
	private List<View> mDotLists = new ArrayList<View>();
	private void initDot(int size) {
		mDotLists.clear();
		dots_ll.removeAllViews();
		for (int i = 0; i < size; i++) {
			View view =new View(mContext);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(CommonUtil.dip2px(mContext,8),CommonUtil.dip2px(mContext,8));
			if(i!=0){
			   params.leftMargin=CommonUtil.dip2px(mContext,8);
				view.setBackgroundResource(R.mipmap.dot_normal);
			}else{
				view.setBackgroundResource(R.mipmap.dot_focus);

			}
			view.setLayoutParams(params);
			mDotLists.add(view);
			dots_ll.addView(view);
		}
	}

}
