package com.ayoview.anim.tween;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.io.Serializable;


public class AnimInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static String TRANSLATE = "translate";
	public static String ALPHA = "alpha";
	public static String SCALE = "scale";
	public static String ROTATE = "rotate";
	
	public static String INFINITE = "infinite";
	public static String ABSOLUTE = "absolute";
	public static String RELATIVE_TO_PARENT = "relative to parent";
	public static String RELATIVE_TO_SELF = "relative to self";
	
	public String name; //translate, alpha, scale, rotate
	public String inspecter; //插入器
	public long duration = 2000;
	public long delay = 0;
	public boolean isInfiniteRepeat = false;
	
	//---对于alpha，旋转角度范围，不需要考虑横竖坐标
	public float from, to;
	
	//---对于缩放原点，旋转原点
	public int centerXType;// = Animation.ABSOLUTE; //Animation.INFINITE;  Animation.RELATIVE_TO_PARENT;nimation.RELATIVE_TO_SELF; 
	public float centerXValue;
	
	public int centerYType;
	public float centerYValue;
	
	//---对于平移
	public int fromXType;// = Animation.ABSOLUTE; //Animation.INFINITE;  Animation.RELATIVE_TO_PARENT;nimation.RELATIVE_TO_SELF; 
	public float fromXValue;
	
	public int fromYType;
	public float fromYValue;
	
	public int toXType;
	public float toXValue;
	
	public int toYType;
	public float toYValue;
	
	public Animation generateAnimation(){
		Animation a = null;
		if(this.name.equals(AnimInfo.TRANSLATE)){
			
			a = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, 
					fromYType, fromYValue, toYType, toYValue);
			
		}else if(this.name.equals(AnimInfo.SCALE)){
			
			a = new ScaleAnimation(fromXValue, toXValue, fromYValue, toYValue, 
					fromXType, centerXValue, fromYType, centerYValue);
			
		}else if(this.name.equals(AnimInfo.ROTATE)){
			
			a = new RotateAnimation(from, to, centerXType, centerXValue, centerYType, centerYValue);
			if(this.isInfiniteRepeat) a.setRepeatMode(Animation.INFINITE);
		}else if(this.name.equals(AnimInfo.ALPHA)){
			
			a = new AlphaAnimation(from, to);
			
		}
		if(a != null){
			a.setDuration(this.duration);
			a.setStartOffset(this.delay);
			a.setInterpolator(getInterpolator());
		}
		return a;
	}
	String[] mItems2 = new String[]{
			"Linear",
			"Accelerate",
			"Decelerate",
			"AccelerateDecelerate",
			"Cycle",
			"Anticipate",
			"Overshoot",
			"AnticipateOvershoot",
			"Bounce",
			};
	public Interpolator getInterpolator(){
		Interpolator p = null;
		if(this.inspecter.equals("Linear")){
			p = new LinearInterpolator();
		}else if(this.inspecter.equals("Accelerate")){
			p = new AccelerateInterpolator();
		}else if(this.inspecter.equals("Decelerate")){
			p = new DecelerateInterpolator();
		}else if(this.inspecter.equals("AccelerateDecelerate")){
			p = new AccelerateDecelerateInterpolator();
		}else if(this.inspecter.equals("Cycle")){
			p = new CycleInterpolator(7);
		}else if(this.inspecter.equals("Anticipate")){
			p = new AnticipateInterpolator();
		}else if(this.inspecter.equals("Overshoot")){
			p = new OvershootInterpolator();
		}else if(this.inspecter.equals("AnticipateOvershoot")){
			p = new AnticipateOvershootInterpolator();
		}else if(this.inspecter.equals("Bounce")){
			p = new BounceInterpolator();
		}else{
			
		}
		
		return p;
	}
}
