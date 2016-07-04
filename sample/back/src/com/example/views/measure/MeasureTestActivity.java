package com.example.views.measure;

import com.example.views.R;
import com.example.views.R.layout;
import com.example.views.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MeasureTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mesure_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mesure_test, menu);
		return true;
	}

}
