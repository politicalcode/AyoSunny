package com.example.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ZoomControls;

public class V_AdvanceButton extends GhostActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_advance_button);
		
		final ZoomControls zoomcontrols = this.findZoomControls(R.id.zoomcontrols);
		
		//===按下缩小按钮时
		zoomcontrols.setOnZoomOutClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast("缩小");
			}
		});
		//===按下放大按钮时
		zoomcontrols.setOnZoomInClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast("放大");
			}
		});
	}
}
