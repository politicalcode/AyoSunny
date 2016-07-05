package com.example.views;

import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThemePlayerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		int pos = this.getIntent().getExtras().getInt("position");
		
		ThemeMainActivity.Pair pair = ThemeMainActivity.themes[pos];
		this.setTheme(pair.theme_id);
		
		RelativeLayout lay = new RelativeLayout(this);
		TextView v = new TextView(this);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		p.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		v.setLayoutParams(p);
		v.setText(pair.theme_name);
		v.setGravity(Gravity.CENTER);
		v.setMarqueeRepeatLimit(10);
		lay.addView(v);
		this.setContentView(lay);
	}
	
}
