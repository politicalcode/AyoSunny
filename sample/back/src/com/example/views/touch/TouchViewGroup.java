package com.example.views.touch;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 只能在垂直方向线性布局
 * 
 * @author seven
 * 
 */
public class TouchViewGroup extends ViewGroup {

	private int usedHeight = 0;
	//private int totalHeight = 0;

	private int usedWidth = 0;
	//private int totalWidth = 0;
	
	public TouchViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TouchViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TouchViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		usedHeight = 0;
		usedWidth = 0;
		
		System.out.println("---LinearLayout-1:onMeasure");

		// ----先去测量所有子控件
		/**
		 * measureChild()函数是View提供的，倒是考虑了padding ——但是没考虑margin，
		 */
		final int size = this.getChildCount();
		for (int i = 0; i < size; ++i) {
			final View child = getChildAt(i);
			//measureChild(child, widthMeasureSpec, heightMeasureSpec);
			measureChildWithMargins(child, widthMeasureSpec, usedWidth, heightMeasureSpec, usedHeight);
			usedHeight += child.getMeasuredHeight();
		}

		// ----再来测量自己：View的默认实现
		setMeasuredDimension(
				getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
				getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
	}

	/** 
	 * 考虑了margin，padding，兄弟控件已经使用的尺寸，但光测量过程考虑这个没用，layout时得考虑margin和padding
	 * 
	 * 对于测量过程，测量子控件时，需要：
	 * 1、如果子控件是固定宽度，则只需要提供父的最大剩余尺寸（还是需要知道父的padding，子的margin，兄弟的已占尺寸），
	 * 理论上子控件应该选择自己的固定宽度，所以上面的只是建议
	 * 2、如果子控件是wrap_content，同上，还是都需要，还是都是建议，子控件应该选择
	 * 
	 * */
	protected void measureChildWithMargins(View child,
			int parentWidthMeasureSpec, int widthUsed,
			int parentHeightMeasureSpec, int heightUsed) {
		final MarginLayoutParams lp = (MarginLayoutParams) child
				.getLayoutParams();

		final int childWidthMeasureSpec = getChildMeasureSpec(
				parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight()
						+ lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
		final int childHeightMeasureSpec = getChildMeasureSpec(
				parentHeightMeasureSpec, getPaddingTop() + getPaddingBottom()
						+ lp.topMargin + lp.bottomMargin + heightUsed,
				lp.height);

		child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
	}

	/** 这个不是很全面，getChildMeasureSpec的参数2是用过的尺寸，只算padding的话，就没考虑margin和其他兄弟控件 */
//	protected void measureChild(View child, int parentWidthMeasureSpec,
//			int parentHeightMeasureSpec) {
//		final LayoutParams lp = child.getLayoutParams();
//
//		final int childWidthMeasureSpec = getChildMeasureSpec(
//				parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(),
//				lp.width);
//		final int childHeightMeasureSpec = getChildMeasureSpec(
//				parentHeightMeasureSpec, getPaddingTop() + getPaddingBottom(),
//				lp.height);
//
//		child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//	}

	public static int getDefaultSize(int size, int measureSpec) {
		int result = size;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		switch (specMode) {
		case MeasureSpec.UNSPECIFIED:
			result = size;
			break;
		case MeasureSpec.AT_MOST:
		case MeasureSpec.EXACTLY:
			result = specSize;
			break;
		}
		return result;
	}

	/**
	 * 测量子控件最困难的工作，考虑了用过的尺寸（padding），考虑了父和子的wrap_content和match_parent关系
	 * 
	 * @param spec
	 * @param padding
	 * @param childDimension
	 * @return
	 */
	public static int getChildMeasureSpec(int spec, int padding, int childDimension) 
	{
		int specMode = MeasureSpec.getMode(spec);
		int specSize = MeasureSpec.getSize(spec);

		int size = Math.max(0, specSize - padding);

		int resultSize = 0;
		int resultMode = 0;

		switch (specMode) {
		// Parent has imposed an exact size on us
		case MeasureSpec.EXACTLY:
			if (childDimension >= 0) {
				resultSize = childDimension;
				resultMode = MeasureSpec.EXACTLY;
			} else if (childDimension == LayoutParams.MATCH_PARENT) {
				// Child wants to be our size. So be it.
				resultSize = size;
				resultMode = MeasureSpec.EXACTLY;
			} else if (childDimension == LayoutParams.WRAP_CONTENT) {
				// Child wants to determine its own size. It can't be
				// bigger than us.
				resultSize = size;
				resultMode = MeasureSpec.AT_MOST;
			}
			break;

		// Parent has imposed a maximum size on us
		case MeasureSpec.AT_MOST:
			if (childDimension >= 0) {
				// Child wants a specific size... so be it
				resultSize = childDimension;
				resultMode = MeasureSpec.EXACTLY;
			} else if (childDimension == LayoutParams.MATCH_PARENT) {
				// Child wants to be our size, but our size is not fixed.
				// Constrain child to not be bigger than us.
				resultSize = size;
				resultMode = MeasureSpec.AT_MOST;
			} else if (childDimension == LayoutParams.WRAP_CONTENT) {
				// Child wants to determine its own size. It can't be
				// bigger than us.
				resultSize = size;
				resultMode = MeasureSpec.AT_MOST;
			}
			break;

		// Parent asked to see how big we want to be
		case MeasureSpec.UNSPECIFIED:
			if (childDimension >= 0) {
				// Child wants a specific size... let him have it
				resultSize = childDimension;
				resultMode = MeasureSpec.EXACTLY;
			} else if (childDimension == LayoutParams.MATCH_PARENT) {
				// Child wants to be our size... find out how big it should
				// be
				resultSize = 0;
				resultMode = MeasureSpec.UNSPECIFIED;
			} else if (childDimension == LayoutParams.WRAP_CONTENT) {
				// Child wants to determine its own size.... find out how
				// big it should be
				resultSize = 0;
				resultMode = MeasureSpec.UNSPECIFIED;
			}
			break;
		}
		return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
	}

	@Override
    protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
	/** 这个决定了在此ViewGroup里的子控件的布局被转换成什么类型的LayoutParam */
	@Override
    public TouchViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new TouchViewGroup.LayoutParams(getContext(), attrs);
    }
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		usedHeight = 0;
		usedWidth = 0;
		
		System.out.println("---LinearLayout-1: onLayout-" + l + ", " + t + ", "
				+ r + ", " + b);
		int childCount = this.getChildCount();
		if (childCount == 0)
			return;
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();
			final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			System.out.println("---LinearLayout-1: onLayout-控件" + i + "--"
					+ childWidth + ", " + childHeight);
			int left = 0 + getPaddingLeft();//所有子控件都靠着左边放，但是要留出padding left
			int top = usedHeight + lp.topMargin; //子控件要跳过已经放好的兄弟控件，留出margin top
			int right = left + childWidth;
			int bottom = top + childHeight;
			child.layout(left, top, right, bottom);// 一个挨一个垂直往下排
			usedHeight += lp.topMargin + childHeight + lp.bottomMargin; //每次布完一个，用过的高度增量要算上当前控件和当前控件的上下margin
			//或者
			///usedHeight = bottom + lp.bottomMargin;
		}
		/**
		 * 注意看上面的实现，在测量过程是考虑了match_parent的，但在布局过程中并没有考虑
		 * ——match_parent需要从父的左上角开始布局
		 * ——match_parent的意思应该是：把下面的挤到下面去，把上面的挤到上面去，具体如何考虑，还有待分析
		 */
	}

	
	public static class LayoutParams extends ViewGroup.MarginLayoutParams{

		public LayoutParams(MarginLayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	///----------------------------------------------------------////
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		///----------分发
		/*
		 它会被调用处理触摸屏事件，可以重写覆盖此方法来拦截所
		 有触摸屏事件在这些事件分发到窗口之前。通常应该处理触
		 摸屏事件，一定要调用这个实现。当返回值为true时，表示
		 这个事件已经被消费了，即不往下分发
		 */
		
		
		//00000000000000
		boolean res = super.dispatchTouchEvent(event);
		System.out.println("TouchViewGroup.dispatchTouchEvent = " + res);
		return res;
	}
	
	//--------------------------触摸事件---------------------//
	private int red, green, blue;
	private void init(){
		Random r = new Random(System.currentTimeMillis());
		red = r.nextInt(256);
		green = r.nextInt(256);
		blue = r.nextInt(256);
		this.setBackgroundColor(Color.argb(190, red, green, blue));
	}
	
	MotionEvenAgent motion = new MotionEvenAgent("TouchView");
	private static final int moveTime = 2000;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motion.setEvent(event);
		motion.show();
		//----------处理
		//要跟着手指头动，就要消费掉：down和move事件
		//每次down之后2秒内，可以跟随手指头滑动，过时则不再消耗touch事件
		if(motion.isDown()){
			//getParent().requestDisallowInterceptTouchEvent(true); //告诉父不要拦截我的触摸事件，前提当然是父控件在onInterceptTouchEvent里拦截了
			return true;
		}else if(motion.isMove()){
			if(motion.expiredFor(2000)){
				return false;
			}else{
				//滑动
				red = addColor(red);
				green = addColor(green);
				blue = addColor(blue);
				this.setBackgroundColor(Color.argb(255, red, green, blue));
				return true;
			}
		}else if(motion.isFinish()){
			//this.req
			//getParent().requestDisallowInterceptTouchEvent(false);
			return true; 
		}
		
		//0000000000000
		return super.onTouchEvent(event);
	}
	
	private int addColor(int colorPart){
		return (colorPart + 20) % 255;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//----------拦截
		/*
		  ——返回值表示是否拦截
		 	如果在down时，返回true，即本ViewGroup拦截了，则：
		 	1、子View的收不到down了
		 	2、后续的move和up也不需要经过此函数了，而是直接给本ViewGroup的ouTouchEvent
		 */
		
		
		//requestDisallowInterceptTouchEvent(disallowIntercept)
		//00000000000000
		boolean res = super.onInterceptTouchEvent(ev);
		System.out.println("TouchViewGroup.onInterceptTouchEvent = " + res);
		return res;
	}
}
