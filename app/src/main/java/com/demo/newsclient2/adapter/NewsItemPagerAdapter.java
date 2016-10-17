package com.demo.newsclient2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.newsclient2.R;
import com.demo.newsclient2.bean.NewsItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/16
 * Time: 21:30
 */
public class NewsItemPagerAdapter extends BaseAdapter {
    List<NewsItem.DataBean.NewsBean> news;
    Context mContext;
    private final DisplayImageOptions options;

    public NewsItemPagerAdapter(List<NewsItem.DataBean.NewsBean> news, Context mContext) {
        this.news=news;
        this.mContext=mContext;
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.home_scroll_default)
                .showImageOnFail(R.mipmap.home_scroll_default)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.layout_news_item,
                    null);
        }
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);

        TextView tv_pub_date = (TextView) convertView
                .findViewById(R.id.tv_pub_date);

        NewsItem.DataBean.NewsBean newsBean = news.get(position);

        tv_title.setText(newsBean.getTitle());

        tv_pub_date.setText(newsBean.getPubdate());

        if (TextUtils.isEmpty(newsBean.getListimage())) {
            iv_img.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(news.get(position).getListimage(),iv_img,options);
        return convertView;
    }
}
