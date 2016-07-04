package com.example.views;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class V_Clock extends GhostActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_clock);
		
		final Button btn_tick = this.findButton(R.id.btn_tick);
		final Chronometer ticker = this.findChronometer(R.id.tick_didadida);
		
		//ticker.setFormat("");
		
		btn_tick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ticker.setBase(SystemClock.elapsedRealtime());
				ticker.start();
				btn_tick.setText("正在计时，等个5秒");
				btn_tick.setEnabled(false);
			}
		});
		
		ticker.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				long startTime = chronometer.getBase();
				long now = SystemClock.elapsedRealtime();
				
				long duration = now - startTime;
				if(duration >= 5 * 1000){
					chronometer.stop();
					btn_tick.setText("开始计时");
					showToast("时间到！淡定！");
					btn_tick.setEnabled(true);
				}
			}
		});
	}
}
