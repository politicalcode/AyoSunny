package com.example.view.events;

import com.example.views.R;
import com.example.views.R.layout;
import com.example.views.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Draw2DActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw2_d);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.111111111111111
		getMenuInflater().inflate(R.menu.activity_draw2_d, menu);
		return true;
	}

}
