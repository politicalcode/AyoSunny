package com.example.views;

import android.app.ActionBar.LayoutParams;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class V_ImageSwitcher_TextSwitcher extends GhostActivity {

	private Drawable[] images = null;
	private int current = 0;
	
	private String[] strings = null;
	private int currentText = 0;
	private ImageSwitcher imageSwitcher;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_image_switcher);
		
		this.images = new Drawable[]{
				this.getResources().getDrawable(R.drawable.qie),
				this.getResources().getDrawable(R.drawable.kaola),
				this.getResources().getDrawable(R.drawable.shuimu)
		};
		
		this.strings = new String[]{
				"麒麟岂是池中物",
				"一遇风云便化龙",
				"风云雄霸天下"
		};		
		
		//---------------------------------ImageView: --------------------------------------//
		
		imageSwitcher = this.findImageSwitcher(R.id.image_switcher);
		Button btn_switch = this.findButton(R.id.btn_image_switcher);
		
		//====ImageSwitcher的factory.makeView()必须返回一个ImageView，
		//===之后使用imageSwitcher.setImageDrawable等方法传入的drawable，id，URI都是直接set给这个返回的ImageView
		imageSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {

				//====看看怎么构建一个ImageView吧
				ImageView image = new ImageView(V_ImageSwitcher_TextSwitcher.this);
				image.setScaleType(ImageView.ScaleType.FIT_CENTER);
				image.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return image;

			}
		});
		imageSwitcher.setImageDrawable(images[current]); //这句会调用imageSwitcher.factory.makeView()，获得ImageView，然后把这个set进去
		
		btn_switch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				current++;
				imageSwitcher.setImageDrawable(images[current%3]);
			}
		});
		
		//---------------------------------TextView: --------------------------------------//
		
		final TextSwitcher textSwitcher = this.findTextSwitcher(R.id.text_switcher);
		Button btn_text_switcher = this.findButton(R.id.btn_text_switcher);
		
		//====textSwitcher的factory.makeView()必须返回一个TextView，
		//===之后使用textSwitcher.setText方法传入
		textSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {

				//====看看怎么构建一个TexteView吧
				TextView textView = new TextView(V_ImageSwitcher_TextSwitcher.this);
				textView.setLayoutParams(new TextSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				textView.setTextSize(22);
				textView.setTextColor(0xff00ff00);
				return textView;

			}
		});
		textSwitcher.setText(strings[currentText]);
		
		btn_text_switcher.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentText++;
				textSwitcher.setText(strings[currentText%3]);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
