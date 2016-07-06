package com.ayoview.sample.progress;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.lang.Lang;
import org.ayo.notify.Toaster;

public class V_ProgressBar extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		//========标题栏进度条=============//
//		getActivity().requestWindowFeature(Window.FEATURE_PROGRESS); //设置窗口特征：启用标题栏上的显示进度的进度条
//		getActivity().requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); //设置窗口特征：启用标题栏上的不显示进度的进度条
		setContentView(R.layout.sp_ac_progressbar);


		Button show_progress_on_titlebar = this.findViewById(R.id.btn_show_progress_on_titlebar);
		Button hide_progress_on_titlebar = this.findViewById(R.id.btn_hide_progress_on_titlebar);
		show_progress_on_titlebar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//===显示标题栏的带进度的进度条，并设置进度
				getActivity().setProgressBarVisibility(true);
				getActivity().setProgress(50);
				
				//===显示标题栏的不带进度的进度条
				getActivity().setProgressBarIndeterminateVisibility(true);
				
			}
		});
		
		hide_progress_on_titlebar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//===隐藏标题栏的带进度的进度条，并设置进度
				getActivity().setProgressBarVisibility(false);
				
				//===隐藏标题栏的不带进度的进度条
				getActivity().setProgressBarIndeterminateVisibility(false);
				
			}
		});
		
		
		//==================================//
		
		final ProgressBar progress = this.findViewById(R.id.progress);
		progress.setProgress(50);

		final ProgressBar pb_webview = this.findViewById(R.id.pb_webview);
		final ProgressBar pb_webview2 = this.findViewById(R.id.pb_webview2);
		pb_webview.setProgress(0);
		pb_webview2.setProgress(0);
		pb_webview.setSecondaryProgress(50);
		pb_webview2.setSecondaryProgress(70);
		Lang.run(new Runnable() {
			@Override
			public void run() {
				pb_webview.setProgress(pb_webview.getProgress() + 1);
				pb_webview2.setProgress(pb_webview2.getProgress() + 1);
			}
		}, 100, 300);

		final ProgressBar progress_custom = findViewById(R.id.progress_custom);
		progress_custom.setIndeterminateDrawable(new ProgressAnimationDrawable());
		progress_custom.setBackgroundColor(Color.BLUE);

		final ProgressBar layer_list_progress = this.findViewById(R.id.layer_list_progress);
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
		SeekBar seekbar = this.findViewById(R.id.seekbar);
		final ImageView image = this.findViewById(R.id.image_alpha_jumper);
		
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
		RatingBar rating = this.findViewById(R.id.rating);
		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				if(fromUser){
					Toaster.toastLong("您给了卖家"+ratingBar.getRating());
				}
			}
		});
	}
}
