package com.example.views.tween;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.views.R;

public class TweenMaker extends Activity
{
	private ImageView iv_animate_shower;
	private ImageView iv_animate_shower2;
	private Spinner sp_types;
	private Spinner sp_inspects;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tween_maker);
		
		iv_animate_shower = (ImageView) this.findViewById(R.id.iv_animate_shower);
		iv_animate_shower2 = (ImageView) this.findViewById(R.id.iv_animate_shower2);
		
		//动画类型
		sp_types = (Spinner) findViewById(R.id.sp_types);
		String[] mItems = new String[]{AnimInfo.TRANSLATE, AnimInfo.SCALE, AnimInfo.ROTATE, AnimInfo.ALPHA};
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
		sp_types.setAdapter(_Adapter);
		sp_types.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        String str = parent.getItemAtPosition(position).toString();
		        changeUI(str);
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		sp_inspects = (Spinner) findViewById(R.id.sp_inspects);
		String[] mItems2 = new String[]{
			"Linear",
			"Accelerate",
			"Decelerate",
			"AccelerateDecelerate",
			"Cycle",
			"Anticipate",
			"Overshoot",
			"AnticipateOvershoot",
			"Bounce",
			};
		ArrayAdapter<String> _Adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems2);
		sp_inspects.setAdapter(_Adapter2);
		
		
		//位置关系
		setSpinner(R.id.sp_relatives_x);
		setSpinner(R.id.sp_relatives_y);
		setSpinner(R.id.sp_relatives_start_x);
		setSpinner(R.id.sp_relatives_start_y);
		setSpinner(R.id.sp_relatives_end_x);
		setSpinner(R.id.sp_relatives_end_y);
		
		//---看是否修改模式
		try {
			if(this.getIntent().hasExtra("anim")){
				isUpdate = true;
			}else{
				isUpdate = false;
			}
		} catch (Exception e) {
			isUpdate = false;
		}
		
		if(isUpdate){
			setUI((AnimInfo)this.getIntent().getSerializableExtra("anim"));
		}
	}
	
	private void setUI(AnimInfo serializableExtra) {
		
	}

	private boolean isUpdate = false;
	
	protected void changeUI(String animType) {
		if(animType.equals(AnimInfo.TRANSLATE)){
			show(R.id.ll_start_x);
			show(R.id.ll_start_y);
			show(R.id.ll_end_x);
			show(R.id.ll_end_y);
			
			hide(R.id.ll_range);
			hide(R.id.ll_center_x);
			hide(R.id.ll_center_y);
		}else if(animType.equals(AnimInfo.SCALE)){
			show(R.id.ll_start_x);
			show(R.id.ll_start_y);
			show(R.id.ll_end_x);
			show(R.id.ll_end_y);
			
			hide(R.id.ll_range);
			show(R.id.ll_center_x);
			show(R.id.ll_center_y);
			hide(R.id.sp_relatives_x);
			hide(R.id.sp_relatives_y);
		}else if(animType.equals(AnimInfo.ROTATE)){
			hide(R.id.ll_start_x);
			hide(R.id.ll_start_y);
			hide(R.id.ll_end_x);
			hide(R.id.ll_end_y);
			
			show(R.id.ll_range);
			show(R.id.ll_center_x);
			show(R.id.ll_center_y);
		}else if(animType.equals(AnimInfo.ALPHA)){
			hide(R.id.ll_start_x);
			hide(R.id.ll_start_y);
			hide(R.id.ll_end_x);
			hide(R.id.ll_end_y);
			
			show(R.id.ll_range);
			hide(R.id.ll_center_x);
			hide(R.id.ll_center_y);
		}
	}

	public void setSpinner(int resId){
		Spinner sp = (Spinner) findViewById(resId);
		String[] mItems = new String[]{AnimInfo.RELATIVE_TO_SELF, AnimInfo.RELATIVE_TO_PARENT, AnimInfo.ABSOLUTE};
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
		sp.setAdapter(_Adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        String str = parent.getItemAtPosition(position).toString();
//		        if(str.equals(AnimInfo.TRANSLATE)){
//		        	findViewById(R.id.ll_range).setVisibility(View.GONE);
//		        }
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
	}

	AnimInfo info = new AnimInfo();
	public void onShow(View v){
		inflateAnimInfo();
		Animation a = info.generateAnimation();
		if(a instanceof TranslateAnimation){
			a.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					iv_animate_shower2.setVisibility(View.VISIBLE); //动画开始之前，先让控件可见
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					
					
				}
			});
			iv_animate_shower2.startAnimation(a);
		}else{
			iv_animate_shower.startAnimation(a);
		}
	}
	public void onFinish(View v){
		inflateAnimInfo();
		Intent intent = new Intent();
		intent.putExtra("anim", info);
		if(isUpdate){
			setResult(200, intent);
		}else{
			setResult(100, intent);
		}
		finish();
	}
	private void inflateAnimInfo(){
		//---取数据
		//类型
		info.name = sp_types.getSelectedItem().toString();
		info.duration = getLongTextOf(R.id.et_duration);
		info.delay = getLongTextOf(R.id.et_delay);
		
		//alpha，scale，rotate都有个范围，和坐标无关
		info.from = getFloatTextOf(R.id.et_start);
		info.to = getFloatTextOf(R.id.et_end);
		
		//scale，rotate都有个远点，和坐标有关
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(AnimInfo.ABSOLUTE, Animation.ABSOLUTE);
		map.put(AnimInfo.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_PARENT);
		map.put(AnimInfo.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
		info.centerXType = map.get(getSpinnerSelection(R.id.sp_relatives_x).toString());
		info.centerYType = map.get(getSpinnerSelection(R.id.sp_relatives_y).toString());
		info.centerXValue = getFloatTextOf(R.id.et_center_x);
		info.centerYValue = getFloatTextOf(R.id.et_center_y);
		
		//translate起点和终点
		info.fromXType = map.get(getSpinnerSelection(R.id.sp_relatives_start_x).toString());
		info.fromYType = map.get(getSpinnerSelection(R.id.sp_relatives_start_y).toString());
		info.fromXValue = getFloatTextOf(R.id.et_start_x);
		info.fromYValue = getFloatTextOf(R.id.et_start_y);
		
		info.toXType = map.get(getSpinnerSelection(R.id.sp_relatives_end_x).toString());
		info.toYType = map.get(getSpinnerSelection(R.id.sp_relatives_end_y).toString());
		info.toXValue = getFloatTextOf(R.id.et_end_x);
		info.toYValue = getFloatTextOf(R.id.et_end_y);
		
		info.inspecter = sp_inspects.getSelectedItem().toString();
		info.isInfiniteRepeat = ((CheckBox)findViewById(R.id.cb_infinite)).isChecked();
		//---
	}
	
	public float getFloatTextOf(int resId){
		try {
			return Float.parseFloat(((TextView)findViewById(resId)).getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public long getLongTextOf(int resId){
		try {
			return Long.parseLong(((TextView)findViewById(resId)).getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public void show(int resId){
		try {
			findViewById(resId).setVisibility(View.VISIBLE);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void hide(int resId){
		try {
			findViewById(resId).setVisibility(View.GONE);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Object getSpinnerSelection(int resId){
		try {
			Spinner sp = (Spinner) findViewById(resId);
			return sp.getSelectedItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
}
