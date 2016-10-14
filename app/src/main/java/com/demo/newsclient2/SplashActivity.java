package com.demo.newsclient2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.demo.newsclient2.utils.Constants;
import com.demo.newsclient2.utils.SharedPreferencesUtils;

/**
 * Created by GSJ
 * Date: 2016/10/13
 * Time: 10:39
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedPreferencesUtils.getBoolean(SplashActivity.this, Constants.isFirstOpen,true)){
                    Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                    SharedPreferencesUtils.putBoolean(SplashActivity.this,Constants.isFirstOpen,false);

                }else{
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();

                }

            }
        },2000);

    }
}
