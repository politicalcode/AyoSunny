package com.ayoview.sample.imageview;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.ayoview.sample.resource.SBViewUtils;
import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import java.util.HashMap;
import java.util.Map;

public class V_ImageView_ScaleType extends BaseActivity {

	private ImageView iv;

	static Map<String, String> map = new HashMap<String, String>();
	static{
		map.put("CENTER", "CENTER:原则是图片大小不变，居中，可被裁剪\n始终拿着图片真实大小对着中心往上贴，不会拉伸缩放");
		map.put("CENTER_CROP", "CENER_CROP：原则是图片始终充满ImageView且不变形，居中, 但可被裁剪\n--如果ImageView宽或高大于图片，则会放大，并且选择宽和高最大的缩放因子缩放，因此图片会被某一个方向被剪切，但不会变形\n--如果ImageView宽高正好等于图片宽高，则scaleType不起作用\n--如果ImageView宽高都小于或等于图片宽高，则两个方向都需要缩小，取缩小比例最小的缩小因子");
		map.put("CENTER_INSIDE", "CENTER_INSIDE：原则是图片始终全部显示，可以不填充，只会缩小，不会放大，居中\n--ImageView大于图片时，图片不缩放，居中显示");
		map.put("FIT_CENTER", "FIT_CENTER：原则是图片始终全部显示，某一方向可以不填充，居中，缩放但不变形\n--ImageView大于图片时，取最小的放大因子，填充某一方向，保持不变形\n--ImageView小于图片时，取最大的缩小因子，填充某一方向，保持不变形");
		map.put("FIT_END", "FIT_END：原则是和FIT_CENTER一样，但不是居中，而是处于未填充方向的终点");
		map.put("FIT_START", "FIT_START：原则是和FIT_CENTER一样，但不是居中，而是处于未填充方向的起点");
		map.put("FIT_XY", "FIT_XY：拉伸图片充满ImageView，会变形");
		map.put("MATRIX", "需要指定matrix，默认好像是FIT_END的表现");
	}
	
	String[] types = new String[]{
			"CENTER",
			"CENTER_CROP",
			"CENTER_INSIDE",
			"FIT_CENTER",
			"FIT_END",
			"FIT_START",
			"FIT_XY",
			"MATRIX",
//			ScaleType.CENTER,
//			ScaleType.CENTER_CROP,
//			ScaleType.CENTER_INSIDE,
//			ScaleType.FIT_CENTER,
//			ScaleType.FIT_END,
//			ScaleType.FIT_START,
//			ScaleType.FIT_XY,
//			ScaleType.MATRIX
	};

	private TextView et_w;

	private TextView tv_info;
	SeekBar seekBarW;
	SeekBar seekBarH;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp_ac_imageview_scaletype);
		
		iv = (ImageView) findViewById(R.id.iv);
		tv_info = (TextView) findViewById(R.id.tv_info);
		
		//---图片是180*255
		StringBuilder sb = new StringBuilder();
		sb.append("图片位于hdpi.animate_shower.png，原始大小180*255\n");
		int[] realSize = SBViewUtils.getRealSizeBecauseOfDpi(240, SBViewUtils.getDpi(getActivity()), 180, 255);
		sb.append("本机dpi是：" + SBViewUtils.getDpiType(getActivity()) + ", 所以图片实际大小是：" + realSize[0] + "," + realSize[1] +"\n");
		sb.append("---所以实际图片dp是：" + SBViewUtils.getDp(getActivity(), realSize[0]) +", " + SBViewUtils.getDp(getActivity(), realSize[1]) + ", 讲imageView设置成这么大则正好和图片契合，相当于wrap_content\n");
		sb.append("\n");
		tv_info.setText(sb.toString());
		
		int w = realSize[0] + 40;
		int h = realSize[1] + 40;
		LayoutParams lp = new LayoutParams(w, h);
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.topMargin = SBViewUtils.getPixel(getActivity(), 10);
		iv.setLayoutParams(lp);
		
		et_w = findViewById(R.id.et_w);


		et_w.setText("(" + w + ", " + h + ")");

		seekBarW = findViewById(R.id.seekBarW);
		seekBarH = findViewById(R.id.seekBarH);

		seekBarW.setMax(w*2);
		seekBarH.setMax(h*2);

		seekBarW.setProgress(w);
		seekBarH.setProgress(h);

		seekBarW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				changeSize();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		seekBarH.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				changeSize();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});


		//---scaleType
		
		final Spinner sp_type = (Spinner) findViewById(R.id.sp_type);
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, types);
		sp_type.setAdapter(_Adapter);
		sp_type.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        String str = types[position];
		        iv.setScaleType(ScaleType.valueOf(str));
		        TextView tv_explain = (TextView) findViewById(R.id.tv_explain);
		        tv_explain.setText(map.get(str));
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
	}

	protected void changeSize() {
		int w = seekBarW.getProgress();
		int h = seekBarH.getProgress();
		et_w.setText("(" + w + ", " + h + ")");
		LayoutParams lp = new LayoutParams(w, h);
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.topMargin = SBViewUtils.getPixel(getActivity(), 10);
		iv.setLayoutParams(lp);
		iv.invalidate();
	}


}
