package com.demo.newsclient2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.newsclient2.View.CustomizeViewPager;
import com.demo.newsclient2.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/13
 * Time: 10:55
 */
public class GuideActivity extends Activity implements  ViewPager.OnPageChangeListener, View.OnClickListener {

    private LinearLayout mLlAllPoint;
    private CustomizeViewPager mViewPager;
    private int[] pic;
    private List<ImageView> mImageList;
    private View mPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }
private int width;
    private void initView() {
        setContentView(R.layout.activity_guide);
        mViewPager = (CustomizeViewPager) findViewById(R.id.view_pager);
        mLlAllPoint = (LinearLayout) findViewById(R.id.ll_all_point);
        mPoint = findViewById(R.id.point);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        pic = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

        mImageList = new ArrayList<>();
        for (int i = 0; i < pic.length; i++) {
            ImageView imageView=new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(pic[i]);
            mImageList.add(imageView);
            View view=new View(this);
            view.setBackgroundResource(R.mipmap.dot_normal);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(CommonUtil.dip2px(this,10), CommonUtil.dip2px(this,10));
            if(i>0){

            params.leftMargin= CommonUtil.dip2px(this,10);
            }
            view.setLayoutParams(params);
            mLlAllPoint.addView(view);

        }
        GuideAdapter adapter=new GuideAdapter();
        mViewPager.setAdapter(adapter);
//        mViewPager.setPageTransformer(true,);
        mViewPager.addOnPageChangeListener(this);
        mLlAllPoint.post(new Runnable() {
            @Override
            public void run() {
              width=  mLlAllPoint.getChildAt(1).getLeft()-mLlAllPoint.getChildAt(0).getLeft();

            }
        });


    }
     private  int oldPosition=0;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int newPosition= (int) (width*(position+positionOffset));
        System.out.println("positionOffset=="+positionOffset);
        System.out.println("positionOffsetPixels=="+positionOffsetPixels);
        System.out.println("position==="+position);
        System.out.println("newPosition==="+newPosition);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mPoint.getLayoutParams();
        params.leftMargin=newPosition;
        mPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        mLlAllPoint.getChildAt(oldPosition).setBackgroundResource(R.mipmap.dot_normal);
        mLlAllPoint.getChildAt(position).setBackgroundResource(R.mipmap.dot_focus);
        oldPosition=position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    class GuideAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageList.get(position));
             mViewPager.addView(position,mImageList.get(position));
            return mImageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mViewPager.removeView(mImageList.get(position));

        }
    }
}
