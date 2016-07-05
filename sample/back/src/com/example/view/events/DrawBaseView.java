package com.example.view.events;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class DrawBaseView extends View{

	public DrawBaseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public DrawBaseView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public DrawBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);//--这里面肯定有画背景的功能吧
		
	}
	
}
