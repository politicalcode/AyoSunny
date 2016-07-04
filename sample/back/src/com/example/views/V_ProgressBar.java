package com.example.views;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class V_ProgressBar extends GhostActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		//========标题栏进度条=============//
		requestWindowFeature(Window.FEATURE_PROGRESS); //设置窗口特征：启用标题栏上的显示进度的进度条
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); //设置窗口特征：启用标题栏上的不显示进度的进度条
		setContentView(R.layout.uistudy_progressbar);
		
		Button show_progress_on_titlebar = this.findButton(R.id.btn_show_progress_on_titlebar);
		Button hide_progress_on_titlebar = this.findButton(R.id.btn_hide_progress_on_titlebar);
		show_progress_on_titlebar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//===显示标题栏的带进度的进度条，并设置进度
				setProgressBarVisibility(true);
				setProgress(50);
				
				//===显示标题栏的不带进度的进度条
				setProgressBarIndeterminateVisibility(true);
				
			}
		});
		
		hide_progress_on_titlebar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//===隐藏标题栏的带进度的进度条，并设置进度
				setProgressBarVisibility(false);
				
				//===隐藏标题栏的不带进度的进度条
				setProgressBarIndeterminateVisibility(false);
				
			}
		});
		
		
		//==================================//
		
		ProgressBar progress = this.findProgressBar(R.id.progress);
		progress.setProgress(50);
		
		final ProgressBar layer_list_progress = this.findProgressBar(R.id.layer_list_progress);
		layer_list_progress.setMax(120);
		layer_list_progress.setProgress(0);
		
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				for(int i = 0; i < 120; i++){
					layer_list_progress.setProgress(i); //===进度条可以在子线程里更新 
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		//==========拖动条改变图片透明度===========
		SeekBar seekbar = this.findSeekBar(R.id.seekbar);
		final ImageView image = this.findImageView(R.id.image_alpha_jumper);
		
		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					int alpha = seekBar.getProgress();  // int alpha = progress
					image.setAlpha(alpha);
				}
			}
		});
		
		
		///===================星级评分条=================
		RatingBar rating = this.findRatingBar(R.id.rating);
		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				if(fromUser){
					showToast("您给了卖家"+ratingBar.getRating());
				}
			}
		});
	}
}
