package com.example.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.Menu;

/**
 * InsetDrawable本身是一张Drawable，再往里嵌入一个：
 * 当控件需要的背景比实际的边框小的时候比较适合使用InsetDrawable，指定了上下左右空出的距离
 * ——注意：对应View的内容也以InsetDRAWable的边距为边距
 * 
 * @author seven
 *
 */
@SuppressLint("NewApi")
public class D_InsetDrawable extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d__inset_drawable);
		
		InsetDrawable insetDrawable=new InsetDrawable(getResources().getDrawable(R.drawable.animate_shower), 20, 30, 30, 20); 
	
		/*
		 * xml:
		 * 
		 * <inset xmlns:android="http://schemas.android.com/apk/res/android"   
			    android:drawable="@drawable/image4"  
			    android:insetLeft="50dp"  
			    android:insetRight="50dp"  
			    android:insetTop="20dp"  
			    android:insetBottom="20dp">  
			</inset>
		 */
		findViewById(R.id.tv).setBackground(insetDrawable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_d__inset_drawable, menu);
		return true;
	}

}
