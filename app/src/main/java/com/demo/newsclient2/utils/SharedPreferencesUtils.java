package com.demo.newsclient2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GSJ
 * Date: 2016/10/13
 * Time: 10:42
 */
public class SharedPreferencesUtils {
    private static final String name="config";
    private static SharedPreferences sp;

    public static void putBoolean(Context context,String key,boolean value){
        if(sp==null){

        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();

    }
    public static boolean getBoolean(Context context,String key,boolean value){
        if(sp==null){

        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
      return   sp.getBoolean(key,value);

    }
    public static void putString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences(name, 0);
        sp.edit().putString(key, value).commit();
    }
    public static String getString(Context context, String key, String defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(name, 0);
        return sp.getString(key, defValue);
    }

}
