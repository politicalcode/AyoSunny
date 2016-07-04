package com.example.views;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ThemeMainActivity extends Activity {

	ListView lv = null;
	
	static class Pair{
		public String theme_name;
		public int theme_id;
		public Pair(String name, int id)
		{
			this.theme_name = name;
			this.theme_id = id;
		}
		public Pair(){}
		
	}
	
	
	
	public static Pair[] themes = new Pair[]{
			new Pair("@android:style/Theme",android.R.style.Theme),
			
			new Pair("@android:style/Theme_Black",android.R.style.Theme_Black),  //什么也不显示
			new Pair("@android:style/Theme_Black_NoTitleBar", android.R.style.Theme_Black_NoTitleBar),
			new Pair("@android:style/Theme_Black_NoTitleBar_Fullscreen", android.R.style.Theme_Black_NoTitleBar_Fullscreen),
			
			new Pair("@android:style/Theme_Light_NoTitleBar", android.R.style.Theme_Light_NoTitleBar),
			new Pair("@android:style/Theme_Light", android.R.style.Theme_Light),
			new Pair("@android:style/Theme_Light_NoTitleBar_Fullscreen", android.R.style.Theme_Light_NoTitleBar_Fullscreen),
			new Pair("@android:style/Theme_Light_Panel", android.R.style.Theme_Light_Panel),
			new Pair("@android:style/Theme_Light_WallpaperSettings", android.R.style.Theme_Light_WallpaperSettings),
			
			new Pair("@android:style/Theme_NoDisplay", android.R.style.Theme_NoDisplay),
			new Pair("@android:style/Theme_NoTitleBar", android.R.style.Theme_NoTitleBar),
			new Pair("@android:style/Theme_NoTitleBar_Fullscreen", android.R.style.Theme_NoTitleBar_Fullscreen),
			new Pair("@android:style/Theme_NoTitleBar_OverlayActionModes", android.R.style.Theme_NoTitleBar_OverlayActionModes),
			
			new Pair("@android:style/Theme_Translucent", android.R.style.Theme_Translucent),
			new Pair("@android:style/Theme_Translucent_NoTitleBar", android.R.style.Theme_Translucent_NoTitleBar),
			new Pair("@android:style/Theme_Translucent_NoTitleBar_Fullscreen", android.R.style.Theme_Translucent_NoTitleBar_Fullscreen),
			
			
			new Pair("@android:style/Theme_Wallpaper", android.R.style.Theme_Wallpaper),
			new Pair("@android:style/Theme_Wallpaper_NoTitleBar", android.R.style.Theme_Wallpaper_NoTitleBar),
			new Pair("@android:style/Theme_Wallpaper_NoTitleBar", android.R.style.Theme_Wallpaper_NoTitleBar),
			new Pair("@android:style/Theme_Wallpaper_NoTitleBar_Fullscreen", android.R.style.Theme_Wallpaper_NoTitleBar_Fullscreen),
			new Pair("@android:style/Theme_WallpaperSettings", android.R.style.Theme_WallpaperSettings),
			
			new Pair("@android:style/Theme_InputMethod", android.R.style.Theme_InputMethod),
			new Pair("@android:style/Theme_Dialog", android.R.style.Theme_Dialog),
			new Pair("@android:style/Theme_Panel", android.R.style.Theme_Panel),
			new Pair("@android:style/Theme_WithActionBar", android.R.style.Theme_WithActionBar),
			
			new Pair("@android:style/Theme_Holo", android.R.style.Theme_Holo),
			new Pair("@android:style/Theme_Holo_Dialog", android.R.style.Theme_Holo_Dialog),
			new Pair("@android:style/Theme_Holo_Dialog_MinWidth", android.R.style.Theme_Holo_Dialog_MinWidth),
			new Pair("@android:style/Theme_Holo_Dialog_NoActionBar", android.R.style.Theme_Holo_Dialog_NoActionBar),
			new Pair("@android:style/Theme_Holo_Dialog_NoActionBar_MinWidth", android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth),
			new Pair("@android:style/Theme_Holo_DialogWhenLarge", android.R.style.Theme_Holo_DialogWhenLarge),
			new Pair("@android:style/Theme_Holo_DialogWhenLarge_NoActionBar", android.R.style.Theme_Holo_DialogWhenLarge_NoActionBar),
			
			new Pair("@android:style/Theme_Holo_Light", android.R.style.Theme_Holo_Light),
			new Pair("@android:style/Theme_Holo_Light_NoActionBar", android.R.style.Theme_Holo_Light_NoActionBar),
			new Pair("@android:style/Theme_Holo_Light_NoActionBar_Fullscreen", android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen),
			new Pair("@android:style/Theme_Holo_Light_DarkActionBar", android.R.style.Theme_Holo_Light_DarkActionBar),
			new Pair("@android:style/Theme_Holo_Light_Panel", android.R.style.Theme_Holo_Light_Panel),
			new Pair("@android:style/Theme_Holo_NoActionBar", android.R.style.Theme_Holo_NoActionBar),
			new Pair("@android:style/Theme_Holo_NoActionBar_Fullscreen", android.R.style.Theme_Holo_NoActionBar_Fullscreen),
			
			

			new Pair("@android:style/Theme_Holo_Light_Dialog_MinWidth", android.R.style.Theme_Holo_Light_Dialog_MinWidth),
			new Pair("@android:style/Theme_Holo_Light_Dialog", android.R.style.Theme_Holo_Light_Dialog),
			new Pair("@android:style/Theme_Holo_Light_Dialog_NoActionBar", android.R.style.Theme_Holo_Light_Dialog_NoActionBar),
			new Pair("@android:style/Theme_Holo_Light_Dialog_NoActionBar_MinWidth", android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth),
			new Pair("@android:style/Theme_Holo_Light_DialogWhenLarge", android.R.style.Theme_Holo_Light_DialogWhenLarge),
			new Pair("@android:style/Theme_Holo_Light_DialogWhenLarge_NoActionBar", android.R.style.Theme_Holo_Light_DialogWhenLarge_NoActionBar),
			new Pair("@android:style/Theme_Holo_InputMethod", android.R.style.Theme_Holo_InputMethod),
			new Pair("@android:style/Theme_Holo_Panel", android.R.style.Theme_Holo_Panel),
			new Pair("@android:style/Theme_Holo_Wallpaper", android.R.style.Theme_Holo_Wallpaper),
			new Pair("@android:style/Theme_Holo_Wallpaper_NoTitleBar", android.R.style.Theme_Holo_Wallpaper_NoTitleBar),
			
			
			new Pair("@android:style/Theme_DeviceDefault", android.R.style.Theme_DeviceDefault),
			new Pair("@android:style/Theme_DeviceDefault_Dialog", android.R.style.Theme_DeviceDefault_Dialog),
			new Pair("@android:style/Theme_DeviceDefault_Dialog_MinWidth", android.R.style.Theme_DeviceDefault_Dialog_MinWidth),
			new Pair("@android:style/Theme_DeviceDefault_Panel", android.R.style.Theme_DeviceDefault_Panel),
			new Pair("@android:style/Theme_DeviceDefault_NoActionBar_Fullscreen", android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen),
			new Pair("@android:style/Theme_DeviceDefault_NoActionBar", android.R.style.Theme_DeviceDefault_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_Panel", android.R.style.Theme_DeviceDefault_Light_Panel),
			new Pair("@android:style/Theme_DeviceDefault_Wallpaper", android.R.style.Theme_DeviceDefault_Wallpaper),
			new Pair("@android:style/Theme_DeviceDefault_Wallpaper_NoTitleBar", android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_NoActionBar_Fullscreen", android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen),
			new Pair("@android:style/Theme_DeviceDefault_Light_NoActionBar", android.R.style.Theme_DeviceDefault_Light_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar", android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_DialogWhenLarge", android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge),
			new Pair("@android:style/Theme_DeviceDefault_Dialog_NoActionBar", android.R.style.Theme_DeviceDefault_Dialog_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Dialog_NoActionBar_MinWidth", android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth),
			new Pair("@android:style/Theme_DeviceDefault_DialogWhenLarge", android.R.style.Theme_DeviceDefault_DialogWhenLarge),
			new Pair("@android:style/Theme_DeviceDefault_DialogWhenLarge_NoActionBar", android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_InputMethod", android.R.style.Theme_DeviceDefault_InputMethod),
			new Pair("@android:style/Theme_DeviceDefault_Light", android.R.style.Theme_DeviceDefault_Light),
			new Pair("@android:style/Theme_DeviceDefault_Light", android.R.style.Theme_DeviceDefault_Light),
			new Pair("@android:style/Theme_DeviceDefault_Light_DarkActionBar", android.R.style.Theme_DeviceDefault_Light_DarkActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_Dialog", android.R.style.Theme_DeviceDefault_Light_Dialog),
			new Pair("@android:style/Theme_DeviceDefault_Light_Dialog_MinWidth", android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth),
			new Pair("@android:style/Theme_DeviceDefault_Light_Dialog_NoActionBar", android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar),
			new Pair("@android:style/Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth", android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth),
			
			
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		lv = new ListView(this);
		lv.setAdapter(new MyAdapter());
		this.setContentView(lv);
	}
	
	class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return themes.length;
		}

		@Override
		public Object getItem(int position) {
			return themes[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			Button btn;
			if(convertView == null) btn = new Button(ThemeMainActivity.this);
			else btn = (Button)convertView;
			
			final String theme = themes[position].theme_name;
			final int pos = position;
			btn.setText(theme);
			btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), UIMainActivity.class);
					G.theme = pos;
					startActivity(intent);
				}
			});
			
			btn.setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), ThemePlayerActivity.class);
					intent.putExtra("position", pos);
					startActivity(intent);
					return true;
				}
			});
			
			return btn;
		}
		
	}
}
