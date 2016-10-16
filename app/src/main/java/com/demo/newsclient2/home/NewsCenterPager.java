package com.demo.newsclient2.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.newsclient2.MainActivity;
import com.demo.newsclient2.R;
import com.demo.newsclient2.bean.NewsCenter;
import com.demo.newsclient2.menu.InteractionPager;
import com.demo.newsclient2.menu.NewsPager;
import com.demo.newsclient2.menu.PicPager;
import com.demo.newsclient2.menu.TopicPager;
import com.demo.newsclient2.utils.Constants;
import com.demo.newsclient2.utils.GsonTools;
import com.demo.newsclient2.utils.LogUtils;
import com.demo.newsclient2.utils.SharedPreferencesUtils;
import com.demo.newsclient2.utils.ThreadPoolManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻中心
 */
/**
 * listview展示数据的流程
 * 1  获取服务器的数据
 * 获取数据成功。缓存数据
 * 获取数据失败。给用户提示的图片
 * 2  加载数据
 * 先判断当前的缓存里面是否有数据。 如果缓存有数据。先展示缓存里面的数据。然后在从服务器获取数据。如果获取数据成功。那么把缓存的数据直接替换
 * 3 当展示完数据的时候
 * 需要判断当前的用户是否阅读过当前的新闻(商品),如果读过当前的新闻,一般客户端自己需要添加一个字段。表示当前的新闻是否读过isRead。
 * 那么就需要把文字改变颜色。(比较浅的颜色) 没有读过。就展示比较深的颜色
 *
 */
public class NewsCenterPager extends BasePager {
//	@Bind(R.id.news_center_fl)
	FrameLayout mNewsCenterFl;
	private View view;

	public NewsCenterPager(Context context) {
		super(context);
	}

	@Override
	protected View initView() {
		if(view==null){

		view = View.inflate(mContext, R.layout.news_center_frame, null);
		}
		mNewsCenterFl = (FrameLayout) view.findViewById(R.id.news_center_fl);
//		ButterKnife.bind(this,view);
		initTitleBar(view);
		return view;
	}

	@Override
	public void initData() {
		String json = SharedPreferencesUtils.getString(mContext, Constants.NEW_CENTER, "");
		if(!TextUtils.isEmpty(json)){
		    processData(json);
		}else {
			ThreadPoolManager.getShortPool().execute(new Runnable() {
				@Override
				public void run() {
		         getNewsCenter();

				}
			});
		}
	}

	private void getNewsCenter() {
		RequestParams params=new RequestParams(Constants.NEW_CENTER);
		params.setConnectTimeout(8000);
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String result) {
				SharedPreferencesUtils.putString(mContext,Constants.NEW_CENTER,result);
				LogUtils.debugI(this,"result==="+result);
				processData(result);
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
              LogUtils.debugI(this,"onError=="+ex);
			}

			@Override
			public void onCancelled(CancelledException cex) {
				LogUtils.debugI(this,"onCancelled=="+cex);

			}

			@Override
			public void onFinished() {
				LogUtils.debugI(this,"onFinished=====");

			}
		});

	}
	// 封装菜单的title,如新闻、组图等
	private List<String> mTitles = new ArrayList<String>();
	// 初始化左侧的菜单，如...
	private List<BasePager> mMenuLists = new ArrayList<BasePager>();

	private void processData(String result) {
		NewsCenter newsCenter = GsonTools.changeGsonToBean(result, NewsCenter.class);
		if(newsCenter.getRetcode()==200){
			List<NewsCenter.DataBean> data = newsCenter.getData();
//			List<NewsCenter.DataBean.ChildrenBean> children = data.get(0).getChildren();

			mTitles.clear();//记得清空
			for (int i = 0; i < data.size(); i++) {

			  mTitles.add(data.get(i).getTitle());
			}
          mMenuLists.clear();//记得清空
          mMenuLists.add(new NewsPager(mContext,data.get(0)));
          mMenuLists.add(new TopicPager(mContext,data.get(1)));
          mMenuLists.add(new PicPager(mContext,data.get(2)));
          mMenuLists.add(new InteractionPager(mContext,data.get(3)));
			LogUtils.debugI(this,"mTitles=="+mTitles.size());
			((MainActivity)mContext).getMenuFragment().initMenu(mTitles);
			switchFragment(0);
		}

	}

	public void switchFragment(int position) {
		BasePager pager = mMenuLists.get(position);
		mNewsCenterFl.removeAllViews();
		mNewsCenterFl.addView(pager.getRootView());
		pager.initData();
		switch(position){
			case 0:
				txt_title.setText("新闻");
				// 新闻
				break;

			case 1:
				txt_title.setText("专题");
				// 专题
				break;
			case 2:
				txt_title.setText("组图");
				// 组图
				break;
			case 3:
				txt_title.setText("互动");
				// 互动
				break;


		}

	}
}
