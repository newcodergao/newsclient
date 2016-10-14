package com.demo.newsclient2.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ============================================================
 * 
 * 版权 ：安博教育集团 版权所有 (c) 2013
 * 
 * 作者:mwqi
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2013-1-18 上午1:04:20
 * 
 * 描述 ：
 * 
 * 联网，格式化时间工具类 修订历史 ：
 * 
 * ============================================================
 **/
public class CommonUtil {


	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "提示", "确定", null);
	}

	public static void showInfoDialog(Context context, String message,
			String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}

	public static String md5(String paramString) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		} catch (Exception e) {
			return paramString;
		}
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * 
	 * @param context
	 * @return
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo)
									|| "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
	 */

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int getScreenPicHeight(int screenWidth, Bitmap bitmap) {
		int picWidth = bitmap.getWidth();
		int picHeight = bitmap.getHeight();
		int picScreenHeight = 0;
		picScreenHeight = (screenWidth * picHeight) / picWidth;
		return picScreenHeight;
	}

	private static Drawable createDrawable(Drawable d, Paint p) {

		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap b = bd.getBitmap();
		Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(),
				bd.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(b, 0, 0, p); // 关键代码，使用新的Paint画原图，

		return new BitmapDrawable(bitmap);
	}

	/** 设置Selector。 本次只增加点击变暗的效果，注释的代码为更多的效果 */
	public static StateListDrawable createSLD(Context context, Drawable drawable) {
		StateListDrawable bg = new StateListDrawable();
		int brightness = 50 - 127;
		ColorMatrix cMatrix = new ColorMatrix();
		cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
				brightness,// 改变亮度
				0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });

		Paint paint = new Paint();
		paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
		// p.setColor(0x60111111); //Paint ARGB色值，A = 0x40 不透明。RGB222222 暗色

		Drawable normal = drawable;
		Drawable pressed = createDrawable(drawable, paint);
		// p = new Paint();
		// p.setColor(0x8000FF00);
		// Drawable focused = createDrawable(drawable, p);
		// p = new Paint();
		// p.setColor(0x800000FF);
		// Drawable unable = createDrawable(drawable, p);
		// View.PRESSED_ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_pressed, }, pressed);
		bg.addState(new int[] { android.R.attr.state_focused, }, pressed);
		// View.ENABLED_FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_enabled,
		// android.R.attr.state_focused }, focused);
		// View.ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_selected }, pressed);
		// View.FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_focused }, focused);
		// // View.WINDOW_FOCUSED_STATE_SET
		// bg.addState(new int[] { android.R.attr.state_window_focused },
		// unable);
		// View.EMPTY_STATE_SET
		bg.addState(new int[] {}, normal);
		return bg;
	}

	private static boolean hasMethod(String methodName, Method[] method) {
		for (Method m : method) {
			if (methodName.equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

}
