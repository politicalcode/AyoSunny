package com.example.views.tween;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.views.R;

public class TweenActivity extends Activity implements OnClickListener, OnCheckedChangeListener
{
	private ImageView iv_animate_shower;
	private ImageView iv_animate_shower2;
	private Button btn_alpha;
	private Button btn_translate;
	private Button btn_scale;
	private Button btn_rotate;
	
	private RadioButton[] radios = new RadioButton[10];

	private Interpolator interpolater = null;
	private Interpolator interpolater_default = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_animate);
		
		iv_animate_shower = (ImageView) this.findViewById(R.id.iv_animate_shower);
		iv_animate_shower2 = (ImageView) this.findViewById(R.id.iv_animate_shower2);
		
		btn_alpha = (Button) this.findViewById(R.id.btn_alpha);
		btn_translate = (Button) this.findViewById(R.id.btn_translate);
		btn_scale = (Button) this.findViewById(R.id.btn_scale);
		btn_rotate = (Button) this.findViewById(R.id.btn_rotate);
		
		radios[0] = (RadioButton) this.findViewById(R.id.r1);
		radios[1] = (RadioButton) this.findViewById(R.id.r2);
		radios[2] = (RadioButton) this.findViewById(R.id.r3);
		radios[3] = (RadioButton) this.findViewById(R.id.r4);
		radios[4] = (RadioButton) this.findViewById(R.id.r5);
		radios[5] = (RadioButton) this.findViewById(R.id.r6);
		radios[6] = (RadioButton) this.findViewById(R.id.r7);
		radios[7] = (RadioButton) this.findViewById(R.id.r8);
		radios[8] = (RadioButton) this.findViewById(R.id.r9);
		radios[9] = (RadioButton) this.findViewById(R.id.r10);
		
		btn_alpha.setOnClickListener(this);
		btn_translate.setOnClickListener(this);
		btn_scale.setOnClickListener(this);
		btn_rotate.setOnClickListener(this);
		
		for(RadioButton radio : radios){
			radio.setOnCheckedChangeListener(this);
		}
	}

	@Override
	public void onClick(View v)
	{
		Animation a = null;
		int duration = 4000;
		switch(v.getId())
		{
		/**
		 * 透明度动画,渐变动画：
		 * fromAlpha：动画开始时的透明度（取值范围为0.0到1.0）；
    	 * toAlpha：动画结束时的透明度；
		 */
		case R.id.btn_alpha:  
			a = new AlphaAnimation(0.0f, 1.0f);
			break;
		/**
		 * 平移动画：
		 *  fromXDelta：动画开始的X坐标；
		 *  toXDelta：动画结束的X坐标；
		 *  fromYDelta：动画开始的Y坐标；
		 *  toYDelta：动画结束的Y坐标；
		 *  
		 *  默认相对于自己，(0,0)说的是左上角坐标，x<0，则说的是view原来位置的左边，y<0说的是上面
		 */
		case R.id.btn_translate:
			a = new TranslateAnimation(-100.0f, 100.0f, -50.0f, 50.0f);
			break;
		/**
		 * 缩放动画：
		 *   fromX:	     动画开始X坐标上的伸缩尺度；
		 *   toX：         动画结束X坐标上的伸缩尺度；
		 *   fromY：     动画开始Y坐标上的伸缩尺度；
		 *   toY：         动画结束Y坐标上的伸缩尺度；
		 *   pivotXType：  X坐标上的伸缩模式，
		 *   pivotXValue：X坐标上的伸缩值；
		 *   pivotYType：  Y坐标上的伸缩模式，
		 *   pivotYValue：Y坐标上的伸缩值；
		 *   
		 *   pivot的取值：Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT；
		 */
		case R.id.btn_scale:
			a = new ScaleAnimation(0.0f, 1.0f, 0.5f, 1.0f);
			break;
		/**
		 *	旋转动画：
		 *	  fromDegrees：旋转开始角度；
		 *    toDegrees：    旋转结束角度；
		 *    pivotXType, pivotXValue, pivotYType, pivotYValue与尺度变化动画ScaleAnimation类似；
		 */
		case R.id.btn_rotate:
			a = new RotateAnimation(0.0f, 270.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			break;
		}
		a.setDuration(duration);
		if(interpolater != null) a.setInterpolator(interpolater);
		iv_animate_shower.startAnimation(a);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		int id = buttonView.getId();
		Log.i("1111", buttonView.getText() + " is" + (isChecked ? " checked!" : " not checked!"));
		//先保证互斥吧
		if(isChecked)
		{
			for(RadioButton radio: radios)
			{
				if(radio.getId() == id)
				{
					radio.setChecked(true);
				}
				else
				{
					radio.setChecked(false);
				}
			}
		}
		
		//再判断逻辑
		if(isChecked)
		{
			switch(id)
			{
				case R.id.r1:
					interpolater = new AccelerateInterpolator();//动画从开始到结束，变化率是一个加速的过程
					break;
				case R.id.r2:
					interpolater = new DecelerateInterpolator();//动画从开始到结束，变化率是一个减速的过程
					break;
				case R.id.r3:
					interpolater = new CycleInterpolator(5);//变化率是循环给定次数的正弦曲线
					break;
				case R.id.r4:
					interpolater = new AccelerateDecelerateInterpolator();//变化率是先加速后减速的过程
					break;
				case R.id.r5:
					interpolater = new LinearInterpolator();//变化率是线性变化
					break;
				case R.id.r6:
					interpolater = new AnticipateInterpolator(); //冲刺：开始的时候反向来点动画
					break;
				case R.id.r7:
					interpolater = new OvershootInterpolator();  //惯性：结束的时候顺势来点动画
					break;
				case R.id.r8:
					interpolater = new AnticipateOvershootInterpolator(); // 开始和结束时都加效果
					break;
				case R.id.r9:
					interpolater = new BounceInterpolator(); //边界碰撞衰减
					break;
				case R.id.r10:
					interpolater = null;
					break;
			}
		}
	}
}

/**
 * 
 * 这里给一个简单的实现Interpolator接口的类：
import android.view.animation.Interpolator;
public class MyInterpolator implements Interpolator {
	private float mFactor;
	private int i;
	
	public MyInterpolator(int i){
		this.i = i;
	}
	@Override
	public float getInterpolation(float input) {
		switch(i){
			case 1:mFactor = input;
				break;
			case 2:mFactor = input*input*input;
				break;
		}
	return mFactor;
 
}
}
当初始变量为1的时候，mFactor = input，是一个线性函数，“变化率”是匀速的
当初始变量为2的时候，mFactor = input*input*input，是一个曲线函数，“变化率”呈三次方。

这里需要注意的是，input是一个0.0f~1.0f的浮点类型
从上面看来，自定义一个Interpolator就是用input根据所需构造一个函数出来

*/
