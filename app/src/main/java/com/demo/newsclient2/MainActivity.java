package com.demo.newsclient2;

import android.os.Bundle;

import com.demo.newsclient2.fragment.HomeFragment;
import com.demo.newsclient2.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置菜单页面
        setBehindContentView(R.layout.menu);
        setContentView(R.layout.activity_main);
//        SlidingMenu menu = new SlidingMenu(this);
        SlidingMenu menu = getSlidingMenu();
//        menu.setMode(SlidingMenu.LEFT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        menu.setFadeDegree(0.35f);
//        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        // 设置滑动菜单的模式
        // SlidingMenu.RIGHT右边的滑动菜单
        // SlidingMenu.LEFT左边的滑动菜单
        // SlidingMenu.LEFT_RIGHT左右都有
        menu.setMode(SlidingMenu.LEFT);
        //设置触摸的模式
        //SlidingMenu.TOUCHMODE_FULLSCREEN全屏模式
        //SlidingMenu.TOUCHMODE_MARGIN边沿模式
        //SlidingMenu.TOUCHMODE_NONE不能滑动
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影的图片
        menu.setShadowDrawable(R.drawable.shadow);
        //当菜单页面出来之后。内容页面的剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
                menu.setFadeDegree(0.35f);
//        menu.setMenu(R.layout.menu);

//        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        HomeFragment homeFragment=new HomeFragment();
        MenuFragment menuFragment=new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_menu,homeFragment,"Main").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu,menuFragment,"Menu").commit();

    }
}
