package com.example.views.measure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.Button;

public class MyButton1 extends Button{

	public MyButton1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public MyButton1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public MyButton1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int specModeW = MeasureSpec.getMode(widthMeasureSpec);
        int specSizeW = MeasureSpec.getSize(widthMeasureSpec);
        switch (specModeW) {
        case MeasureSpec.UNSPECIFIED:
            System.out.println("---MeasureSpec.UNSPECIFIED");
            break;
        case MeasureSpec.AT_MOST:
        	System.out.println("---MeasureSpec.AT_MOST（wrap_conent）");
        	break;
        case MeasureSpec.EXACTLY:
        	System.out.println("---MeasureSpec.EXACTLY（match_parent或固定尺寸）");
            break;
        }
        System.out.println("---specSize = " + specSizeW);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		System.out.println("---结果  = " + getMeasuredWidth());
		
	}
	
	

}
