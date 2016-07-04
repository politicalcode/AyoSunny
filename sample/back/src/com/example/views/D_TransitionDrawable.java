package com.example.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
/**
 * 实现两张Drawable之间的渐入渐出切换：就两张，只能渐隐渐现，就一次，没法循环的来
 * 
 * @author seven
 *
 */
@SuppressLint("NewApi")
public class D_TransitionDrawable extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d__transition_drawable);
		
		TransitionDrawable transitionDrawable=null;  
        transitionDrawable= new TransitionDrawable(new Drawable[] {
        	getResources().getDrawable(R.drawable.animate_shower),
        	getResources().getDrawable(R.drawable.kaola),
        });
        findViewById(R.id.tv).setBackground(transitionDrawable);
        transitionDrawable.startTransition(3000);//间隔3秒
        
        /*
         * xml方式：
         * 
         * <transition xmlns:android="http://schemas.android.com/apk/res/android" >  
			    <item android:drawable="@drawable/image01"/>  
			    <item android:drawable="@drawable/image02"/>  
			</transition> 
         * 
         */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_d__transition_drawable, menu);
		return true;
	}

}
