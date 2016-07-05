package com.example.view.events;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class SillyTextView extends TextView{

	public SillyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public SillyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public SillyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	@Override
    protected void onDraw(Canvas canvas) {
        // Draw the background for this view
        super.onDraw(canvas);
    }
}
