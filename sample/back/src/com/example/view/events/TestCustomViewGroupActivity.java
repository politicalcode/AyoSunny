package com.example.view.events;

import com.example.views.R;
import com.example.views.R.layout;
import com.example.views.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TestCustomViewGroupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_custom_view_group);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test_custom_view_group, menu);
		return true;
	}

}
