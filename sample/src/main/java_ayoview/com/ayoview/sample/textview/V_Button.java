package com.ayoview.sample.textview;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.notify.Toaster;

public class V_Button extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uistudy_button);
		
		Button btn_text = this.findViewById(R.id.btn_text);
		Button btn_ordinary = this.findViewById(R.id.btn_ordinary);
		Button btn_9path = this.findViewById(R.id.btn_9path);
		
		RadioGroup radiogroup = this.findViewById(R.id.radiogroup);
		RadioButton radio_button_female = this.findViewById(R.id.radio_button_female);
		RadioButton radio_button_male = this.findViewById(R.id.radio_button_male);
		
		CheckBox checkbox_green = this.findViewById(R.id.checkbox_green);
		CheckBox checkbox_red = this.findViewById(R.id.checkbox_red);
		CheckBox checkbox_blue = this.findViewById(R.id.checkbox_blue);
		
		ToggleButton toggle_button = this.findViewById(R.id.toggle_button);
		
		//Switch switcher = this.findSwitch(R.id.switcher);
		
		//===Button的点击事件
		btn_ordinary.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toaster.toastLong("OnClickListener.onClick(View)---按钮被点击");
			}
		});
		
		//===RadioGroup和RadioButton的选中和取消选中事件
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Toaster.toastLong("RadioGroup.OnCheckedChangeListener.onCheckedChanged()--"+(checkedId == R.id.radio_button_male ? "男人" : "女人"));
			}
		});
		
		//===CheckBox的选中事件
		checkbox_green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toaster.toastLong(isChecked ? "选中绿色" : "取消绿色");
			}
		});
		
		//===ToggleButton的选中事件
		toggle_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toaster.toastLong(isChecked ? "开" : "关");
			}
		});
		
		//===Switch的选中事件
//		switcher.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				showToast(isChecked ? "开" : "关");
//			}
//		});
		
	}
}
