package com.example.views.touch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.views.R;

public class TouchActivity extends Activity{

	private TouchViewGroup v_root1;
	private TouchViewGroupChild v_root2;
	private TouchView v_view1;
	private TouchView v_view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_touch);
		
		v_root1 = (TouchViewGroup) findViewById(R.id.v_root1);
		v_root2 = (TouchViewGroupChild) findViewById(R.id.v_root2);
		v_view1 = (TouchView) findViewById(R.id.v_view1);
		v_view2 = (TouchView) findViewById(R.id.v_view2);
		
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		///----------分发
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//----------处理
		return super.onTouchEvent(event);
	}
	
}
