package com.ayoview.sample.resource;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.app.base.ActivityAttacher;


public class Layout_Dp_Calculator extends BaseActivity {

	private TextView tv_container;
	private ImageView iv_container;
	private TextView tv_result;
	private EditText et_res_id;
	private SeekBar sb_size;
	private Button btn_add_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp_ac_res_calculator);
		self = this;
		tv_container = (TextView) findViewById(R.id.tv_container);
		iv_container = (ImageView) findViewById(R.id.iv_container);
		
		tv_result = (TextView) findViewById(R.id.tv_result);
		et_res_id = (EditText) findViewById(R.id.et_res_id);
		sb_size = (SeekBar) findViewById(R.id.sb_size);
		btn_add_id = (Button) findViewById(R.id.btn_add_id);
		
		sb_size.setMax(100);
		sb_size.setProgress(50);
		
		btn_add_id.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String resId = et_res_id.getText().toString(); //R.drawable.icon
					String[] ss = resId.split("\\.");
					int id = getResources().getIdentifier(ss[2], ss[1], resourceInfo.pkgName);
					System.out.println("----" + resId + " = " + id);
					if(id == 0){
						toast("id是错的吧？");
						return;
					}
					
					tv_container.setBackgroundResource(id);
					iv_container.setBackgroundResource(id);
					
					ViewGroup.LayoutParams lp = tv_container.getLayoutParams();
					lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
					lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
					tv_container.setLayoutParams(lp);
					sb_size.setProgress(50);
					orig_tv_w = 0;
					orig_tv_h = 0;
					tv_w = 0;
					tv_h = 0;
					tv_dpw = 0;
					tv_dph = 0;
					itsTimeToGetContainerSize();
					
				} catch (Exception e) {
					e.printStackTrace();
					toast("id是错的吧--" + e.getLocalizedMessage());
				}
				
			}
		});
		sb_size.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(!fromUser) return;
				// TODO Auto-generated method stub
				float scale = ((float)progress) / sb_size.getMax() + 0.5f;  //缩放范围0.5-1.5
				System.out.println("----SeekBar进度改变：" + progress);
				tv_w = (int) (orig_tv_w * scale);
				tv_h = (int) (orig_tv_h * scale);
				ViewGroup.LayoutParams lp = tv_container.getLayoutParams();
				lp.width = tv_w;
				lp.height = tv_h;
				tv_container.setLayoutParams(lp);
				tellHim();
				
			}
		});
		
		Button btn1 = (Button) findViewById(R.id.btn_pick_from_app);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DrawableScannerAndPicker.lookDrawable(self.getActivity(), getActivity().getApplicationContext().getPackageName(), "drawable", onResultCallBack);
			}
		});
		Button btn2 = (Button) findViewById(R.id.btn_pick_from_android);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DrawableScannerAndPicker.lookDrawable(self.getActivity(), "android", "drawable", onResultCallBack);
			}
		});
		Button btn3 = (Button) findViewById(R.id.btn_pick_from_others);
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DrawableScannerAndPicker.lookDrawable(self.getActivity(), getActivity().getApplicationContext().getPackageName(), "drawable", onResultCallBack);
			}
		});

		Button btn4 = (Button) findViewById(R.id.btn_add_xx);
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityAttacher.startActivity(getActivity(), Layout_Dp_Activity.class);
			}
		});
		
		itsTimeToGetContainerSize();
	}

	private DrawableScannerAndPicker.ResourceInfo resourceInfo;

	private OnResultCallBack onResultCallBack = new OnResultCallBack() {
		@Override
		public void onResult(Object t) {
			DrawableScannerAndPicker.ResourceInfo resultInfo = (DrawableScannerAndPicker.ResourceInfo) t;
			resourceInfo = resultInfo;
			String name = resultInfo.name;
			int id = resultInfo.id;
			et_res_id.setText(name);
		}
	};
	

	private void toast(String msg){
		Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
	}
	
	int orig_tv_w, orig_tv_h, tv_w, tv_h, tv_dpw, tv_dph;
	
	private void itsTimeToGetContainerSize(){
		final ViewTreeObserver vo = tv_container.getViewTreeObserver();
		vo.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				tv_container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				tellHim();
			}
		});
	}
	
	private void itsTimeToSetContainerSize(final float scale){
		final ViewTreeObserver vo = tv_container.getViewTreeObserver();
		vo.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				tv_container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	
	private BaseActivity self;
	
	private void tellHim(){
		tv_w = tv_container.getWidth();
		tv_h = tv_container.getHeight();
		if(orig_tv_w == 0 || orig_tv_h == 0){
			orig_tv_w = tv_w;
			orig_tv_h = tv_h;
		}
		tv_dpw = SBViewUtils.getDp(self.getActivity(), tv_w);
		tv_dph = SBViewUtils.getDp(self.getActivity(), tv_h);
		
		int dpi = SBViewUtils.getDpi(self.getActivity());
		
		StringBuilder sb = new StringBuilder();
		sb.append("在您的手机（" + SBViewUtils.getDpiType(self.getActivity()) + "）上：\n----显示的大小是：");
		sb.append(tv_dpw + "dp * " + tv_dph + "dp\n----对应的像素是：" + tv_w + " * " + tv_h);
		sb.append("\n");
		sb.append("如果您想在所有手机（标准）上，让“显示的大小看起来几乎一致”，您需要：");
		sb.append("\n");
		sb.append("----ldpi目录下：像素" + tv_w * 120.0 / dpi + " * " + tv_h * 120.0 / dpi + "\n");
		sb.append("----mdpi里目录下：像素" + tv_w * 160.0 / dpi + " * " + tv_h * 160.0 / dpi + "\n");
		sb.append("----hdpi里目录下：像素" + tv_w * 240.0 / dpi + " * " + tv_h * 240.0 / dpi + "\n");
		sb.append("----xhdpi里目录下：像素" + tv_w * 320.0 / dpi + " * " + tv_h * 320.0 / dpi + "\n");
		sb.append("----xxhdpi里目录下：像素" + tv_w * 480.0 / dpi + " * " + tv_h * 480.0 / dpi + "\n");
		sb.append("您有以下三种选择：\n");
		sb.append("1、如果您懒，则只放一张，并承担缩放模糊的风险（最好放xxhdpi里，那么就都会缩小），注意wrap_content\n");
		sb.append("2、如果您不懒，则各个像素都提供即可，还是wrap_content\n");
		sb.append("3、如果您根本不会切图，则直接使用固定大小吧（ndp*ndp），图片还是最好放在xxhdpi里，能缩小就不放大\n");
		
		tv_result.setText(sb.toString());
	}
}
