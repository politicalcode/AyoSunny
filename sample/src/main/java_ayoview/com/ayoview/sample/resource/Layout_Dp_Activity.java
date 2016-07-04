package com.ayoview.sample.resource;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;


/**
 * 屏幕dpi和dp和适配
 * @author seven
 *
 *	你的手机是：什么什么dpi（320）
 *
 *	1、你想要在你手机上显示多大（dp）？宽，高
 *	——显示你需要的像素（在各个dpi目录下）：你需要在ldpi里有一张n*n的，或者在mdpi里有一张m*m的，这些会适配所有dpi，如果你只在一张，则图片可能会被缩放模糊
 *	——显示在其他dpi设备下你需要的多大：在lpdi里你需要几dp*几dp
 *	
 *	2、你有一张多少像素的图片？宽，高，在哪个目录下显示最合适？
 *	——缩放结果：这张图片在ldpi设备上是几dp*几dp，红色显示在你的机器上的dp
 *	——想要适配：这张图片在其他目录下应该是多少像素
 *
 *	3、我就有一张图片，知道像素，不知道想显示成多大，也不知道该放哪个目录！
 *	——这没办法，至少需要知道想显示成多大，请使用二分法调试查看，然后回到问题1
 *
 *	4、我就知道在多大的屏幕上要显示成多大的像素：宽，高，屏幕宽高
 *	——根据屏幕宽高计算出dpi，把像素换算成dp，然后回到问题1
 *
 *	5、请将图片放到你的手机对应的dpi目录下，并输入id，滑动进度条，找到最合适大小
 *
 *	在您的手机（dpi）上，显示的大小是：ndp*ndp，对应pixel为：m*k
 *	如果您想在所有手机（标准）上，让“显示的大小看起来几乎一致”，您需要：
 *	——ldpi里：像素m*k
 *	——mdpi里：
 *	——hdpi里：
 *	——xhdpi里：
 *	——xxhdpi里：
 *	您有以下三种选择：
 *	1、如果您懒，则只放一张，并承担缩放模糊的风险（最好放xxhdpi里，那么就都会缩小），注意wrap_content	
 *	2、如果您不懒，则各个像素都提供即可，还是wrap_content
 *	3、如果您根本不会切图，则直接使用固定大小吧（ndp*ndp），图片还是最好放在xxhdpi里，能缩小就不放大
 *	
 *	
 *
 *	6、换算
 *	XXXX dp = YYYY pixel
 *  XXXX sp = YYYY pt
 *
 */
public class Layout_Dp_Activity extends BaseActivity {

	private TextView tv1;
	private TextView tv2;
	private TextView tv3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp_ac_res_dp);
		
		WindowManager wm = getActivity().getWindowManager();
		//wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		dp.getMetrics(dm);
		
		StringBuilder sb = new StringBuilder();
		sb.append("dm.xdpi = " + dm.xdpi + "\n");
		sb.append("dm.ydpi = " + dm.ydpi + "\n");
		sb.append("dm.densityDpi = " + dm.densityDpi);
		if(dm.densityDpi == 120) sb.append("--ldpi\n");
		else if(dm.densityDpi == 160) sb.append("--mdpi\n");
		else if(dm.densityDpi == 240) sb.append("--hdpi\n");
		else if(dm.densityDpi == 320) sb.append("--xdpi\n");
		else sb.append("--什么dpi\n");
		sb.append("\n");
		sb.append("dm.density = " + dm.density + "--表示一个dp是几个像素\n");
		sb.append("dm.scaledDensity = " + dm.scaledDensity + "\n");
		sb.append("\n");
		sb.append("dm.widthPixels = " + dm.widthPixels + "--(" + SBViewUtils.getScreenWidthDP(this.getActivity()) + "dp)\n");
		sb.append("dm.heightPixels = " + dm.heightPixels + "--(" + SBViewUtils.getScreenHeightDP(this.getActivity()) + "dp)\n");
		
		setText(R.id.tv_dpi, sb.toString());
		
		//----宽度：
		View v = findViewById(R.id.tv_width);
		int screenWidthDp = SBViewUtils.getScreenWidthDP(this.getActivity());
		SBViewUtils.setViewWidth(this.getActivity(), v, screenWidthDp);
		setText(R.id.tv_width, screenWidthDp+"dp");
		
		//----背景图行为：当背景图有自己的大小时
		
		
		
	}
	@Override
	protected void onResume() {
		System.out.println("onResume--" + tv1.getHeight()+","+tv1.getHeight()+","+tv1.getHeight());
		super.onResume();
	}
	@Override
	protected void onStart() {

		tv1 = (TextView) findViewById(R.id.tv_bg1);
		tv2 = (TextView) findViewById(R.id.tv_bg2);
		tv3 = (TextView) findViewById(R.id.tv_bg3);
		
		System.out.println("onStart--" + tv1.getHeight()+","+tv1.getHeight()+","+tv1.getHeight());
		
		ViewTreeObserver vto2 = tv1.getViewTreeObserver();   
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
		    @Override   
		    public void onGlobalLayout() { 
		    	System.out.println("----onGlobalLayout----");
		    	tv1.getViewTreeObserver().removeGlobalOnLayoutListener(this);   //不加这句，如果后面要导致重绘，就会无限执行？
		    	String str = tv1.getHeight()+""; 
		    	tv1.setText(str);
		    }   
		});
		vto2 = tv2.getViewTreeObserver();   
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
		    @Override   
		    public void onGlobalLayout() { 
		    	System.out.println("----onGlobalLayout----");
		    	tv2.getViewTreeObserver().removeGlobalOnLayoutListener(this);   //不加这句，如果后面要导致重绘，就会无限执行？
		    	String str = tv2.getHeight()+""; 
		    	tv2.setText(str);
		    }   
		});
		vto2 = tv3.getViewTreeObserver();   
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { 
		    @Override   
		    public void onGlobalLayout() { 
		    	System.out.println("----onGlobalLayout----");
		    	tv3.getViewTreeObserver().removeGlobalOnLayoutListener(this);   //不加这句，如果后面要导致重绘，就会无限执行？
		    	String str = tv3.getHeight()+""; 
		    	tv3.setText(str);
		    }   
		});
		
		super.onStart();
	}

	
	private void setText(int resId, String txt){
		((TextView)findViewById(resId)).setText(txt);
	}

}
