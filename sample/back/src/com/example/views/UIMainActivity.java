package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.events.TestCustomViewGroupActivity;
import com.example.view.events.TextViewActivity;
import com.example.views.dp.Layout_Dp_Activity;
import com.example.views.dp.Layout_Dp_Calculator;
import com.example.views.measure.MeasureTestActivity;
import com.example.views.touch.TouchActivity;
import com.example.views.tween.TweenActivity;
import com.example.views.tween.TweenActivity2;

public class UIMainActivity extends GhostActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.uistudy_activity_main);
		
		//==上下文菜单：ContextView
		TextView context_menu = this.findTextView(R.id.context_menu);
		this.registerForContextMenu(context_menu);
	}
	
	public void click(View view){
		int id = view.getId();
		switch(id){
		case R.id.btn_button:
			startActivity(new Intent(this, V_Button.class));
			break;
		case R.id.btn_clock:
			startActivity(new Intent(this, V_Clock.class));
			break;
		case R.id.btn_imageview:
			startActivity(new Intent(this, V_ImageView.class));
			break;
		case R.id.btn_advancebutton:
			startActivity(new Intent(this, V_AdvanceButton.class));
			break;
		case R.id.btn_progressbar:
			startActivity(new Intent(this, V_ProgressBar.class));
			break;
		case R.id.btn_viewswitcher:
			startActivity(new Intent(this, V_ViewSwitcher.class));
			break;
		case R.id.btn_image_switcher:
			startActivity(new Intent(this, V_ImageSwitcher_TextSwitcher.class));
			break;
		case R.id.btn_webview:
			startActivity(new Intent(this, MyWebViewActivity.class));
			break;
		case R.id.btn_tween:
			startActivity(new Intent(this, TweenActivity.class));
			break;
		case R.id.btn_tween2:
			startActivity(new Intent(this, TweenActivity2.class));
			break;
		case R.id.btn_property:
			//startActivity(new Intent(this, TweenActivity.class));
			break;
		case R.id.btn_imageview_scale:
			startActivity(new Intent(this, V_ImageView_ScaleType.class));
			//toast("ImageView scaleType研究");
			break;
		case R.id.btn_animation_drawable:
			//startActivity(new Intent(this, TweenActivity.class));
			toast("AnimationDrawable");
			break;
		case R.id.btn_bitmap_drawable:
			startActivity(new Intent(this, D_BitmapDrawable.class));
			break;
		case R.id.btn_ninepatch_drawable:
			toast("NinePatchDrawable：xml就是使用一个bitmap，还是要使用工具来画个.9.png图片，左和上指定拉伸区域，右和下指定内容显示区域");
			break;
		case R.id.btn_layer_drawable:
			startActivity(new Intent(this, D_LayerDrawable.class));
			break;
		case R.id.btn_statelist_drawable:
			startActivity(new Intent(this, D_StateListDrawable.class));
			break;
		case R.id.btn_level_drawable:
			startActivity(new Intent(this, D_LevelDrawable.class));
			break;
		case R.id.btn_transition_drawable:
			startActivity(new Intent(this, D_TransitionDrawable.class));
			break;
		case R.id.btn_inset_drawable:
			startActivity(new Intent(this, D_InsetDrawable.class));
			break;
		case R.id.btn_clip_drawable:
			startActivity(new Intent(this, D_ClipDrawable.class));
			break;
		case R.id.btn_scale_drawable:
			startActivity(new Intent(this, D_ScaleDrawable.class));
			break;
		case R.id.btn_rotate_drawable:
			startActivity(new Intent(this, D_RotateDrawable.class));
			break;
		case R.id.btn_gradient_drawable:
			startActivity(new Intent(this, D_GradientDrawable.class));
			break;
		case R.id.btn_color_drawable:
			toast("定义个颜色：\n<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
					"    <color xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
					"    android:color=\"#00FF00\">\n " +
					"</color>");
			break;
		
		case R.id.btn_screen_adapte1:
			//startActivity(new Intent(this, TweenActivity.class));
			toast("屏幕适配研究1");
			break;
		case R.id.btn_screen_adapte2:
			startActivity(new Intent(this, Layout_Dp_Activity.class));
			break;
		case R.id.btn_screen_calculator:
			startActivity(new Intent(this, Layout_Dp_Calculator.class));
			break;
		case R.id.btn_measure:
			startActivity(new Intent(this, MeasureTestActivity.class));
			break;
		case R.id.btn_listeners:
			startActivity(new Intent(this, TextViewActivity.class));
			break;
		case R.id.btn_diy_viewgroup_1:
			startActivity(new Intent(this, TestCustomViewGroupActivity.class));
			break;
		case R.id.btn_draw_2d:
			startActivity(new Intent(this, TestCustomViewGroupActivity.class));
			break;
		case R.id.btn_touch:
			startActivity(new Intent(this, TouchActivity.class));
			break;
			
		default:			
			break;
		}
	}
	private void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 定义字体大小的菜单项标识
	 */
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	
	/**
	 * 定义普通菜单项的标识
	 */
	final int PLAIN_ITEM = 0x11b;
	
	/**
	 * 定义字体颜色菜单项的标识
	 */
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;
	
	/**
	 * 定义性别标识
	 */
	final int MALE = 0x119;
	final int FEMALE = 0x11a;
	
	/**
	 * 定义喜爱的颜色的标识
	 */
	final int RED = 0x11b;
	final int BLUE = 0x11c;
	final int GREEN = 0x11d;
	
	/**
	 * 创建菜单：
	 * ——这也是个回调，当用户按下菜单键的时候就会调用
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		//参数：就是要显示的菜单
		this.showToast("这是要显示菜单啊！");
		
		//------------向menu中添加字体大小的子菜单---------------------
		SubMenu fontMenu = menu.addSubMenu("字体大小");
		fontMenu.setIcon(R.drawable.red);//设置菜单的图标
		fontMenu.setHeaderIcon(R.drawable.red);//设置菜单头的图标
		fontMenu.setHeaderTitle("选择字体大小"); //设置菜单头的标题
		fontMenu.add(0, FONT_10, 0, "10号字体");
		fontMenu.add(0, FONT_12, 0, "12号字体");
		fontMenu.add(0, FONT_14, 0, "14号字体");
		fontMenu.add(0, FONT_16, 0, "16号字体");
		fontMenu.add(0, FONT_18, 0, "18号字体");
		
		//------------向menu中添加普通菜单项---------------------
		MenuItem item = menu.add(0, PLAIN_ITEM, 0, "普通菜单项");
		item.setIcon(R.drawable.red);
		
		//------------向menu中添加文字颜色的子菜单---------------
		SubMenu colorMenu = menu.addSubMenu("字体颜色");
		colorMenu.setIcon(R.drawable.purple); //设置菜单标题
		colorMenu.setHeaderIcon(R.drawable.purple);  //设置菜单头的图标
		colorMenu.setHeaderTitle("选择文字颜色");
		colorMenu.add(0,FONT_RED,0,"红色");
		colorMenu.add(0,FONT_GREEN,0,"绿色");
		colorMenu.add(0,FONT_BLUE,0,"蓝色");	
		
		//-----------单选菜单项--------------------------
		SubMenu genderMenu = menu.addSubMenu("你的性别");
		genderMenu.setHeaderTitle("选择你是男是女");
		genderMenu.add(0, MALE, 0, "男的");
		genderMenu.add(0, FEMALE, 0, "女的");
		//设置这里边的0组菜单项为单选菜单项
		//===参数1：哪组被设置
		//===参数2：是否可选
		//===参数3：true为单选，false为多选
		//===勾选之后界面的变化还要通过消息响应的代码来设置
		genderMenu.setGroupCheckable(0, true, true);

		//-----------多选菜单项--------------------------
		SubMenu favColorMenu = menu.addSubMenu("你喜欢的颜色");
		favColorMenu.setHeaderTitle("选择你喜欢的颜色");
		favColorMenu.add(0, RED, 0, "红色").setCheckable(true);
		favColorMenu.add(0, BLUE, 0, "蓝色").setCheckable(true);
		favColorMenu.add(0, GREEN, 0, "绿色").setCheckable(true);
		
		//-----------启动新的Activity--------------------------
		//===需要配合菜单响应中的item.getIntent()来startActivity()
		MenuItem launcher = menu.add(0, 200, 0,"启动新Activity");
		launcher.setIntent(new Intent(this, V_Clock.class));
		
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * 菜单项被单击后的回调方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case FONT_10:
			this.showToast("字体大小：10");
			break;
		case FONT_12:
			this.showToast("字体大小：12");
			break;
		case FONT_14:
			this.showToast("字体大小：14");
			break;
		case FONT_16:
			this.showToast("字体大小：16");
			break;
		case FONT_18:
			this.showToast("字体大小：18");
			break;
		case FONT_RED:
			this.showToast("字体颜色：红色");
			break;
		case FONT_GREEN:
			this.showToast("字体颜色：绿色");
			break;
		case FONT_BLUE:
			this.showToast("字体颜色：蓝色");
			break;
		
		//====MenuItem是单选的子菜单
		case MALE:
			item.setChecked(true);
			break;
		case FEMALE:
			item.setChecked(true);
			break;
		
		//====MenuItem是多选的子菜单
		case RED:
			item.setChecked(!item.isChecked());
			break;
		case BLUE:
			item.setChecked(!item.isChecked());
			break;
		case GREEN:
			item.setChecked(!item.isChecked());
			break;
		
		//===启动新Activity
		case 200:
			startActivity(item.getIntent());
			break;
		}
		
		return true;
	}
	
	/**
	 * 上下文菜单：ContextMenu
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) 
	{
		menu.add(0, 500, 0, "红色");
		menu.add(0, 501, 0, "绿色");
		menu.add(0, 502, 0, "蓝色");
		menu.setGroupCheckable(0,  true, true);
		menu.setHeaderIcon(R.drawable.red);
		menu.setHeaderTitle("选择背景色");
		
	}
	
	/**
	 * 上下文菜单的消息响应
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		item.setChecked(true);
		//int id = item.getItemId()
		return super.onContextItemSelected(item);
	}
}

