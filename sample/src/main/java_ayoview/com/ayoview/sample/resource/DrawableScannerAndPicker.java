package com.ayoview.sample.resource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.app.base.ActivityAttacher;
import org.ayo.app.base.SimpleBundle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DrawableScannerAndPicker extends BaseActivity{

	public static final int REQEUST_CODE = 1527;
	public static void lookDrawable(Activity activity, String pkgName, String type, OnResultCallBack callBack){

		SimpleBundle sb = new SimpleBundle();
		sb.putExtra("pkgName", pkgName);
		sb.putExtra("type", type);
		ActivityAttacher.startActivity(activity, DrawableScannerAndPicker.class, sb, callBack);

	}

	public static class ResultInfo{
		public String name;
		public String id;
	}
	
	private List<ResourceInfo> resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp_ac_res_picker);
		
		ListView lv_resources = (ListView) findViewById(R.id.lv_resources);
		TextView tv_pkg = (TextView) findViewById(R.id.tv_pkg);
		
		String pkgName = getIntent().getExtra("pkgName");//"com.example.views";
		String type = getIntent().getExtra("type");//"drawable";
		//String pkgName = "android";
		tv_pkg.setText(pkgName);
		resources = scanDrawableResources(pkgName, type);
		lv_resources.setAdapter(new ResourceAdapter());
		
		lv_resources.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ResourceInfo r = resources.get(arg2);
				if(hasResultCallback()){
					getResultCallback().onResult(r);
				}
				finish();
				return true;
			}
		});
	}
	
	class ResourceAdapter extends BaseAdapter {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null || !(convertView instanceof LinearLayout)){
				convertView = View.inflate(parent.getContext(), R.layout.item_resource_info, null);
			}
			
			TextView tv_drawable = (TextView) convertView.findViewById(R.id.tv_drawable);
			TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			
			ResourceInfo r = resources.get(position);
			tv_drawable.setBackgroundResource(r.id);
			tv_name.setText(r.name);
			
			tv_drawable.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int x = 1 + 1;
				}
			});
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public Object getItem(int arg0) {
			return resources == null ? null : resources.get(arg0);
		}
		
		@Override
		public int getCount() {
			return resources == null ? 0 : resources.size();
		}
	}
	
	/**
	 * 扫描pkgName下的所有资源文件，假设pkgName = com.example.view，
	 * 则找出com.example.view.R.drawable下的所有id，即所有的drawable
	 * 
	 * 还可以扫描所有color，anim，这些都是可以直接拿来在界面上显示的
	 * 
	 * @param pkgName
	 * @param type   drawable, anim, color等
	 * @return
	 */
	public static List<ResourceInfo> scanDrawableResources(String pkgName, String type){
		List<ResourceInfo> list = new ArrayList<ResourceInfo>();
		
		//--反射R类
		String rPath = pkgName + ".R";
		Class<?> rClass = null;
		try {
			rClass = Class.forName(rPath);
		} catch (ClassNotFoundException e) {
			System.out.println("---没有：" + rPath + "这个类！");
			return null;
		}
		
		//--反射drawable类
		Class[] resouceClasses = rClass.getDeclaredClasses();
		if(resouceClasses == null || resouceClasses.length == 0){
			System.out.println("---R文件里没有定义任何资源内部类");
			return null;
		}
		Class neededResourceClass = null;
		for(Class c: resouceClasses){
			if(c.getSimpleName().equals(type)){
				neededResourceClass = c;
			}
		}
		if(neededResourceClass == null){
			System.out.println("---R文件里没有定义" + type + "资源内部类");
			return null;
		}
		
		//--开始反射R.drawable
		Field[] ids = neededResourceClass.getDeclaredFields();
		if(ids == null || ids.length == 0){
			System.out.println("---R." + type + "里没有定义任何资源id");
			return null;
		}
		for(Field f: ids){
			int id = 0;
			try {
				Object value = f.get(null);
				id = ((Integer)value);
			} catch (IllegalArgumentException e) {
				System.out.println("---R." + type + "." + f.getName() + "获取方式错误！");
				continue;
			} catch (IllegalAccessException e) {
				System.out.println("---R." + type + "." + f.getName() + "访问权限不够！");
				continue;
			}
			if(id == 0){
				System.out.println("---R." + type + "." + f.getName() + "值为0，肯定是哪儿出问题了！");
				continue;
			}
			list.add(new ResourceInfo(id, "R." + type + "." + f.getName(), pkgName));
		}
		return list;
		
	}
	
	public static class ResourceInfo{
		public int id;//4454
		public String name;//R.drawable.icon
		public String pkgName;
		
		public ResourceInfo(int id, String name, String pkgName){
			this.id = id;
			this.name = name;
			this.pkgName = pkgName;
		}
	}
	
}
