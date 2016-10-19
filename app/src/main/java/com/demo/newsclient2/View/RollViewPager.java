package com.demo.newsclient2.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.newsclient2.MainActivity;
import com.demo.newsclient2.R;
import com.demo.newsclient2.fragment.MenuFragment;
import com.demo.newsclient2.utils.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/16
 * Time: 15:42
 */
public class RollViewPager extends ViewPager {

    /**
     * 图片的url
     */
    private  List<String> imageLists;
    private Context mContext;
    private DisplayImageOptions options;
    private List<View> mDotLists;
    private Task task;
    private MyOnTouchListener onTouchListener;
    private ImageOptions imageOptions;
    private RollViewPagerAdapter adapter;

    public RollViewPager(Context context,List<View> dotLists) {
        super(context);
        mContext=context;
        this.mDotLists = dotLists;
        task = new Task();
        onTouchListener = new MyOnTouchListener();
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
        imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setRadius(DensityUtil.dip2px(4))
                .setIgnoreGif(false)
                .setCrop(true)//是否对图片进行裁剪
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .build();

        ( (MainActivity) mContext).getMenuFragment().setOnStopListener(new MenuFragment.Stop() {
            @Override
            public void stop() {
                if(handler!=null){
                   handler.removeCallbacks(task);
                }
            }
        });
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TextView mTopNewsTitle = null;
    private List<String> mTitleLists = null;

    /**
     * 设置文本标题(初始化)
     *
     * @param top_news_title
     *            文本控件
     * @param titleLists
     *            文本控件上面的数据
     */
    public void setTextTitle(TextView top_news_title, List<String> titleLists) {
        if (null != titleLists && titleLists.size() > 0
                && null != top_news_title) {
            this.mTopNewsTitle = top_news_title;
            this.mTitleLists = titleLists;
            top_news_title.setText(mTitleLists.get(0));//0????
        }

    }
    public void setImageRes(List<String> imageLists) {
        this.imageLists=imageLists;

    }


    private int currentPosition=0;
    private class Task implements Runnable {

        @Override
        public void run() {
            currentPosition=(currentPosition+1)%imageLists.size();
//            Message message = Message.obtain();
//            handler.sendMessage(message);
            RollViewPager.this.setCurrentItem(currentPosition);
            start();
        }
    }
    public  void start(){
        if(adapter==null){
            adapter = new RollViewPagerAdapter();
            this.setAdapter(adapter);
            MyOnPageChangeListener onPageChangeListener=new MyOnPageChangeListener();
            this.addOnPageChangeListener(onPageChangeListener);
        }
        handler.postDelayed(task,1500);
    }
   android.os.Handler handler=new android.os.Handler(){
       @Override
       public void handleMessage(Message msg) {

       }
   };
    private class MyOnTouchListener implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()){
               case MotionEvent.ACTION_DOWN:
                 handler.removeCallbacks(task);
               break;
                case MotionEvent.ACTION_UP:
                   start();
               break;
                case MotionEvent.ACTION_CANCEL:
                   start();
               break;
                case MotionEvent.ACTION_MOVE:
                    handler.removeCallbacks(task);

               break;

            }
            return true;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    int oldPosition;
    private class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPosition=position;
            if (null != mTitleLists && mTitleLists.size() > 0&& null != mTopNewsTitle) {

                mTopNewsTitle.setText(mTitleLists.get(position%mTitleLists.size()));
                LogUtils.debugI(this,"mTopNewsTitle.setText======="+mTitleLists.get(position%mTitleLists.size()));

            }
            if(mDotLists!=null){
               mDotLists.get(oldPosition%mTitleLists.size()).setBackgroundResource(R.mipmap.dot_normal);
               mDotLists.get(position%mTitleLists.size()).setBackgroundResource(R.mipmap.dot_focus);
                LogUtils.debugI(this,"position======="+position%mTitleLists.size());
                LogUtils.debugI(this,"oldPosition======="+oldPosition%mTitleLists.size());

            }
            oldPosition=position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private class RollViewPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.viewpager_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
//            x.image().bind(image,imageLists.get(position%imageLists.size()),imageOptions);
            ImageLoader.getInstance().displayImage(imageLists.get(position%imageLists.size()),image,options);
            container.addView(view);

            view.setOnTouchListener(onTouchListener);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return imageLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
