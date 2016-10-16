package com.demo.newsclient2.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.newsclient2.MainActivity;
import com.demo.newsclient2.R;
import com.demo.newsclient2.adapter.MyBaseAdapter;
import com.demo.newsclient2.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GSJ
 * Date: 2016/10/14
 * Time: 00:01
 */
public class MenuFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.lv_menu_news_center)
    ListView mLvMenuNewsCenter;
    @Override
    protected void initData() {

    }

    @Override
    protected View initView( LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_left_menu, null);
        ButterKnife.bind(this,view);
        return view;
    }
    // 初始化菜单的集合(封装的是菜单标题)
    private List<String> mTitles = new ArrayList<String>();
    private MenuAdapter adapter;

    public void initMenu(List<String> mTitles) {
        this.mTitles.clear();
        this.mTitles.addAll(mTitles);
        LogUtils.debugI(this,"this.mTitles.size;==="+this.mTitles.size());

        if(adapter==null){

            adapter=new MenuAdapter(mTitles);
            LogUtils.debugI(this,"mLvMenuNewsCenter==="+mLvMenuNewsCenter);

            mLvMenuNewsCenter.setAdapter(adapter);
            LogUtils.debugI(this,"adapter==="+adapter);
        }else{
          adapter.notifyDataSetChanged();
            LogUtils.debugI(this,"notifyDataSetChanged===");

        }
        mLvMenuNewsCenter.setOnItemClickListener(this);
      LogUtils.debugI(this,"initMenu====");
    }
    private int  clickPosition;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.clickPosition=position;
        ((MainActivity) mContext).getHomeFragment().getNewsCenterPager()
                .switchFragment(position);
        adapter.notifyDataSetChanged();
        mSlidingMenu.toggle();
        LogUtils.debugI(this,"onItemClick==="+adapter);

    }

    private class MenuAdapter extends MyBaseAdapter<String> {

        public MenuAdapter(List<String> mData) {
            super(mData);
            LogUtils.debugI(this," super(mData);==========");

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.layout_item_menu,null);
            }
            ImageView iv_menu_item = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            TextView tv_menu_item = (TextView) convertView.findViewById(R.id.tv_menu_item);
            String title = mTitles.get(position);

            // 设置菜单的标题
            tv_menu_item.setText(title);
            LogUtils.debugI(this,"tv_menu_item==========");
            if(clickPosition==position){
                tv_menu_item.setTextColor(Color.RED);
                // 设置红色选中的图片
                iv_menu_item.setBackgroundResource(R.mipmap.menu_arr_select);
                // 设置背景图片
                convertView
                        .setBackgroundResource(R.mipmap.menu_item_bg_select);
            }else{
                tv_menu_item.setTextColor(Color.WHITE);
                 // 设置白色的图片
                iv_menu_item.setBackgroundResource(R.mipmap.menu_arr_normal);
                // 设置透明的颜色
                convertView.setBackgroundResource(R.drawable.transparent);
            }
            return convertView;
        }
    }
}
