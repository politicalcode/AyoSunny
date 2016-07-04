package com.example.view.events;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnHoverListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.example.views.R;

public class TextViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview);
		
		//---TextView的监听
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toast("TextView.onClickListener.onClick(v)");
			}
		});
		
		tv.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				toast("TextView.OnDragListener.onDrag(v, dragEvent)\n" + event.getX() + "," + event.getY());
				return false;
			}
		});
		tv.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				toast("TextView.OnEditorActionListener.onEditorAction(v, actionId, keyEvent)");
				return false;
			}
		});
		tv.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				toast("TextView.OnEditorActionListener.onFocusChange(v, hasFocus)");
				
			}
		});
		tv.setOnGenericMotionListener(new OnGenericMotionListener() {
			
			@Override
			public boolean onGenericMotion(View v, MotionEvent event) {
				toast("TextView.OnGenericMotionListener.onGenericMotion(v, motionEvent)");
				return false;
			}
		});
		
		tv.setOnHoverListener(new OnHoverListener() {
			
			@Override
			public boolean onHover(View v, MotionEvent event) {
				toast("TextView.OnHoverListener.onHover(v, motionEvent)");
				return false;
			}
		});
		
		tv.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				toast("TextView.OnKeyListener.onKey(v, keycode, keyEvent)");
				return false;
			}
		});
		
		tv.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				toast("TextView.OnLongClickListener.OnLongClickListener(v)");
				return false;
			}
		});
		
		tv.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
			
			@Override
			public void onSystemUiVisibilityChange(int visibility) {
				toast("TextView.OnSystemUiVisibilityChangeListener.onSystemUiVisibilityChange(visibility)");
			}
		});
		
		tv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//toast("TextView.OnTouchListener.onTouch(v, motionEvent)");
				return false;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_events, menu);
		return true;
	}

	private void toast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		System.out.println("--" + str);
	}
}
