package com.example.views;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

public class GhostActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ThemeMainActivity.Pair pair = ThemeMainActivity.themes[G.theme];
		this.setTheme(pair.theme_id);
	}
	
	/**
	 * 显示一个吐司，LENGTH_SHORT
	 */
	protected void showToast(String content){
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 根据ID获取控件
	 */
	protected View findView(int id){ return this.findViewById(id); }
	
	protected TextView findTextView(int id){ return (TextView)this.findViewById(id); }
	protected Button findButton(int id){ return (Button)this.findViewById(id); }
	protected EditText findEditText(int id){ return (EditText)this.findViewById(id); }
	protected RadioGroup findRadioGroup(int id){ return (RadioGroup)this.findViewById(id); }
	protected RadioButton findRadioButton(int id){ return (RadioButton)this.findViewById(id); }
	protected CheckBox findCheckBox(int id){ return (CheckBox)this.findViewById(id); }
	protected ToggleButton findToggleButton(int id){ return (ToggleButton)this.findViewById(id); }	
	protected Switch findSwitch(int id){ return (Switch)this.findViewById(id); }	
	protected Chronometer findChronometer(int id){return (Chronometer)this.findViewById(id);}
	
	protected ImageView findImageView(int id){ return (ImageView)this.findViewById(id); }
	protected ZoomControls findZoomControls(int id){return (ZoomControls)this.findViewById(id);}
	protected ZoomButton findZoomButton(int id){return (ZoomButton)this.findViewById(id);}
	protected ImageButton findImageButton(int id){return (ImageButton)this.findViewById(id);}	
	
	protected SeekBar findSeekBar(int id){ return (SeekBar)this.findViewById(id); }
	protected RatingBar findRatingBar(int id){ return (RatingBar)this.findViewById(id); }
	protected ProgressBar findProgressBar(int id){ return (ProgressBar)this.findViewById(id); }
	
	protected ListView findListView(int id){ return (ListView)this.findViewById(id); }
	protected GridView findGridView(int id){ return (GridView)this.findViewById(id); }
	protected ExpandableListView findExpandableListView(int id){ return (ExpandableListView)this.findViewById(id); }
	
	protected LinearLayout findLinearLayout(int id){ return (LinearLayout)this.findViewById(id); }
	protected RelativeLayout findRelativeLayout(int id){ return (RelativeLayout)this.findViewById(id); }
	protected GridLayout findGridLayout(int id){ return (GridLayout)this.findViewById(id); }
	protected FrameLayout findFrameLayout(int id){ return (FrameLayout)this.findViewById(id); }
	protected AbsoluteLayout findAbsoluteLayout(int id){ return (AbsoluteLayout)this.findViewById(id); }
	protected TableLayout findTableLayout(int id){ return (TableLayout)this.findViewById(id); }	
	
	protected ViewAnimator findViewAnimator(int id){ return (ViewAnimator)this.findViewById(id); }
	protected ViewSwitcher findViewSwitcher(int id){ return (ViewSwitcher)this.findViewById(id); }
	protected ImageSwitcher findImageSwitcher(int id){ return (ImageSwitcher)this.findViewById(id); }
	protected TextSwitcher findTextSwitcher(int id){ return (TextSwitcher)this.findViewById(id); }
	protected ViewFlipper findViewFlipper(int id){ return (ViewFlipper)this.findViewById(id); }
	
	
}

