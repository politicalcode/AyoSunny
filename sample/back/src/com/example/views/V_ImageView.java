package com.example.views;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class V_ImageView extends GhostActivity {

	private int alpha = 255;
	private int currentImage = 0;
	private int[] images = new int[]{
			R.drawable.kaola,
			R.drawable.qie,
			R.drawable.shuimu
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_imageview);
		
		final Button btn_alpha_more = this.findButton(R.id.btn_alpha_more);
		final Button btn_alpha_less = this.findButton(R.id.btn_alpha_less);
		final Button btn_next = this.findButton(R.id.btn_next);
		
		final ImageView imageWhole = this.findImageView(R.id.imageWhole);
		final ImageView imagePart = this.findImageView(R.id.imagePart);
		
		View.OnClickListener alphaListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v == btn_alpha_more) alpha += 20;
				else if(v == btn_alpha_less) alpha -= 20;
				
				if(alpha > 255) alpha = 255;
				if(alpha < 0) alpha = 0;
				
				imageWhole.setAlpha(alpha);
			}
		};
		
		btn_alpha_less.setOnClickListener(alphaListener);
		btn_alpha_more.setOnClickListener(alphaListener);
		
		btn_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				imageWhole.setImageResource(images[++currentImage % images.length]);
				imagePart.setImageResource(images[++currentImage % images.length]);
			}
		});
		
		imageWhole.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageWhole.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				
				//获得图片的缩放比例
				WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
				double scale = bitmap.getWidth() / wm.getDefaultDisplay().getWidth();
				
  				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if(x + 120 > bitmap.getWidth()) x = bitmap.getWidth() - 120;
				if(y + 120 > bitmap.getHeight()) y = bitmap.getHeight() - 120;
				
				//在imagePart上显示选中区域的细节
				imagePart.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 120, 120));
				
				return false;
			}
		});
	}
}
