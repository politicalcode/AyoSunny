package com.example.views;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;

public class SBViewUtils {
	
	public static boolean consider_more = false;
	
	public static String getDpiType(Activity activity){
		WindowManager wm = activity.getWindowManager();
		//wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		dp.getMetrics(dm);
		if(dm.densityDpi == 120) return "ldpi";
		if(dm.densityDpi == 160) return "mdpi";
		if(dm.densityDpi == 240) return "hdpi";
		if(dm.densityDpi == 320) return "xhdpi";
		if(dm.densityDpi == 480) return "xxhdpi";
		return "诡异屏幕";
	}
	public static int getDpi(Activity activity){
		WindowManager wm = activity.getWindowManager();
		//wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		dp.getMetrics(dm);
		return dm.densityDpi;
	}
	/**
	 * 对于放在hdpi目录下的图片，在其他设备上，会被相应缩放，其他同理
	 * @param physicalDpi  放在哪个dpi目录下，对应的dpi归一化数值
	 * @param myDpi  设备dpi
	 * @param rawWidth 图片原始大小
	 * @param rawHeight
	 * @return
	 */
	public static int[] getRealSizeBecauseOfDpi(int physicalDpi, int myDpi, int rawWidth, int rawHeight){
		int[] res = new int[2];
		res[0] = (int) ((float)myDpi * rawWidth / physicalDpi);
		res[1] = (int) ((float)myDpi * rawHeight / physicalDpi);
		return res;
	}
	
	/**
	 * 得到屏幕宽度的dp值
	 * @param activity
	 * @return
	 */
	public static int getScreenWidthDP(Activity activity){
		
		WindowManager wm = activity.getWindowManager();
		//wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		dp.getMetrics(dm);
		
		float res = dm.widthPixels/dm.density;
		return (int)res;
	}
	
	/**
	 * 得到屏幕高度的dp值
	 * @param activity
	 * @return
	 */
	public static int getScreenHeightDP(Activity activity){
		
		WindowManager wm = activity.getWindowManager();
		//wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		dp.getMetrics(dm);
		
		float res = dm.heightPixels/dm.density;
		return (int)res;
	}
	
	/**
	 * 将v的宽度设置成dp指定的dp值
	 * @param activity
	 * @param v
	 * @param dp
	 */
	public static void setViewWidth(Activity activity, View v, int dp){
		LayoutParams lp = new LayoutParams(getPixel(activity, dp), LayoutParams.WRAP_CONTENT);
		v.setLayoutParams(lp);
	}
	
	
	
	/**
	 * 为何要加0.5
	 * @param c
	 * @param dp
	 * @return
	 */
	public static int getPixel(Context c, int dp){
		 final float scale = c.getResources().getDisplayMetrics().density;  
		 if(consider_more){
			 return (int) (dp * scale + 0.5f); 
		 }else{
			 return (int) (dp * scale);
		 }
	}
	
	/**
	 * 为何要加0.5再减15
	 * @param c
	 * @param pixel
	 * @return
	 */
	public static int getDp(Context c, int pixel){
		final float scale = c.getResources().getDisplayMetrics().density;  
		if(consider_more){
			return (int) (pixel / scale + 0.5f)-15;   
		}else{
			return (int) (pixel / scale);
		}
	}
	
}
