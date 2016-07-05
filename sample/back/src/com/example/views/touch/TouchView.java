package com.example.views.touch;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View{

	private int red, green, blue;
	private void init(){
		Random r = new Random(System.currentTimeMillis());
		red = r.nextInt(256);
		green = r.nextInt(256);
		blue = r.nextInt(256);
		this.setBackgroundColor(Color.argb(190, red, green, blue));
	}
	
	public TouchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public TouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public TouchView(Context context) {
		super(context);
		init();
	}
	
	//--------------------------触摸事件---------------------//
	MotionEvenAgent motion = new MotionEvenAgent("TouchView");
	private static final int moveTime = 2000;
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		///----------分发
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motion.setEvent(event);
		motion.show();
		//----------处理
		//要跟着手指头动，就要消费掉：down和move事件
		//每次down之后2秒内，可以跟随手指头滑动，过时则不再消耗touch事件
		if(motion.isDown()){
			return true;
		}else if(motion.isMove()){
			if(motion.expiredFor(2000)){
				return false;
			}else{
				//滑动
				red = addColor(red);
				green = addColor(green);
				blue = addColor(blue);
				this.setBackgroundColor(Color.argb(190, red, green, blue));
				return true;
			}
		}else if(motion.isFinish()){
			//this.req
		}
		
		//0000000000000
		return super.onTouchEvent(event);
	}
	
	private int addColor(int colorPart){
		return (colorPart + 20) % 255;
	}

}
