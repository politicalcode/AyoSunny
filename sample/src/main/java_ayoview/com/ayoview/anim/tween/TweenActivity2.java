package com.ayoview.anim.tween;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import java.util.ArrayList;
import java.util.List;


public class TweenActivity2 extends BaseActivity
{
	

	private ImageView iv_animate_shower;

	private ImageView iv_animate_shower2;
	
	private List<AnimateOneStep> anims;
	private AnimAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_animate2);
		
		iv_animate_shower = (ImageView) this.findViewById(R.id.iv_animate_shower);
		iv_animate_shower2 = (ImageView) this.findViewById(R.id.iv_animate_shower2);
		
		anims = new ArrayList<AnimateOneStep>();
		adapter = new AnimAdapter(anims);
		
		ListView lv_anims = (ListView) findViewById(R.id.lv_anims);
		lv_anims.setAdapter(adapter);
		
		lv_anims.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Animation a = anims.get(arg2).getInfo(0).generateAnimation();
				iv_animate_shower.startAnimation(a);
			}
		});
		
		lv_anims.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Intent intent = new Intent(getActivity(), TweenMaker.class);
				intent.putExtra("anim", anims.get(arg2).getInfo(0));
				index = arg2;
				getActivity().startActivityForResult(intent, 200);
				return true;
			}
		});
	}
	private void refreshList(){
		adapter.notifyDataSetChanged(anims);
	}
	
	public void click(View v){
		switch (v.getId()) {
		case R.id.btn_go:
			//开始动画
			startAnim();
			break;
		case R.id.btn_add_step:
			//开始添加
			isForNextStep = true;
			Intent intent = new Intent(getActivity(), TweenMaker.class);
			getActivity().startActivityForResult(intent, 100);
			break;

		default:
			break;
		}
	}
	
	public void startAnim(){
		if(this.anims.size() == 0) {
			Toast.makeText(getActivity(), "没选定任何动画", Toast.LENGTH_SHORT).show();
		}else{
			//开始根据AnimInfo制作动画--需要能输出xml和java代码
			AnimationSet as = new AnimationSet(false);
			for(int i = 0; i < anims.size(); i++){
				
				for(int j = 0; j < anims.get(i).size(); j++){
					Animation a = anims.get(i).getInfo(j).generateAnimation();
					as.addAnimation(a);
				}
				
			}
			iv_animate_shower.startAnimation(as);
		}
	}
	
	private boolean isForNextStep = false; //是添加到一个同时运行的动画里， 还是添加到下一步
	private int index;
	
	private void clickAddToSet(int posion){
		isForNextStep = false;
		index = posion;
		Intent intent = new Intent(getActivity(), TweenMaker.class);
		getActivity().startActivityForResult(intent, 100);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == 100){
			if(data == null || !data.hasExtra("anim")) return;
			AnimInfo info = (AnimInfo) data.getSerializableExtra("anim");
			if(info == null || info.name == null || info.name.equals("")) return;
			
			if(isForNextStep){
				//是新一步动画
				AnimateOneStep a = new AnimateOneStep();
				a.add(info);
				this.anims.add(a);
			}else{
				//是本来有的动画组合的添加
				this.anims.get(index).add(info);
			}
			System.out.println("新动画：" + info.name + "--" + info.inspecter);
			refreshList();
		}else if(requestCode == 200){
			//修改之后回来的，index就是修改的动画
			if(data == null || !data.hasExtra("anim")) return;
			AnimInfo info = (AnimInfo) data.getSerializableExtra("anim");
			if(info == null || info.name == null || info.name.equals("")) return;
			AnimateOneStep a = new AnimateOneStep();
			a.add(info);
			anims.add(index, a);
			refreshList();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private class AnimAdapter extends SBAdapter<AnimateOneStep>{

		public AnimAdapter(List<AnimateOneStep> datas) {
			super(datas);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final int p = position;
			convertView = View.inflate(parent.getContext(), R.layout.item_animate_set, null);
			LinearLayout root = (LinearLayout) convertView.findViewById(R.id.ll_set);
			Button btn_add_to_set = (Button) convertView.findViewById(R.id.btn_add_to_set);
			btn_add_to_set.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					clickAddToSet(p);
				}
				
			});
			
			AnimateOneStep a = this.getItem(position);
			System.out.println("数量：" + a.size());
			for(int i = 0; i < a.size(); i++){
				TextView v = createTextView(a.getInfo(i).name);
				v.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				root.addView(v);
			}
			
			return convertView;
		}
		
	}
	
	private TextView createTextView(String txt){
		TextView v = new TextView(getActivity());
		v.setText(txt);
		v.setTextSize(20);
		return v;
	}
}

