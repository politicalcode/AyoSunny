package com.example.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

public class V_ViewSwitcher extends GhostActivity {
	private ViewSwitcher switcher;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_view_switcher);
		
		switcher = this.findViewSwitcher(R.id.viewSwitcher);
		//===给swticher设置View工厂，当调用switcher.getNextView()时，就是返回 factory.makeView()创建的View
		//===这里给一个TextView，让swticher切换多个TextView
		switcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				return new TextView(V_ViewSwitcher.this);
			}
		});	
	}
	private int count = 0;
	//====消息响应：下一个
	public void next(View v){
		count++;
		switcher.setInAnimation(this, R.anim.slide_in_right); //切换组件：显示过程的动画
		switcher.setOutAnimation(this, R.anim.slide_out_left); //切换组件：隐藏过程的动画
		TextView textView = (TextView) switcher.getNextView();
		textView.setText("This is "+count+"!!!");
		textView.setTextSize(22);
		textView.setTextColor(0xffff0000);
		switcher.showNext();
		
	}
	
	//====消息响应：上一个
	public void prev(View v){
		count--;
		switcher.setInAnimation(this, R.anim.slide_in_left); //切换组件：显示过程的动画
		switcher.setOutAnimation(this, R.anim.slide_out_right); //切换组件：隐藏过程的动画
		TextView textView = (TextView) switcher.getNextView();
		textView.setText("This is "+count+"!!!"); 
		textView.setTextSize(22);
		textView.setTextColor(0xffff0000);
		switcher.showPrevious();
	}
}
