package com.demo.newsclient2.utils;

import android.util.Log;

public class LogUtils {
	/**
	 * 调试的模式
	 */
	public static boolean DEBUG_MODE = true;

	public static void debugD(Object object, String msg) {
		if (DEBUG_MODE) {
			Log.d(object.getClass().getSimpleName(), msg);
		}

	}
	
	public static void debugI(Object object, String msg) {
		if (DEBUG_MODE) {
			Log.i(object.getClass().getSimpleName(), msg);
		}

	}

}
