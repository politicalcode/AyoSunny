package com.example.views.tween;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SBAdapter<T> extends BaseAdapter{

	protected List<T> datas;
	
	public SBAdapter(List<T> datas){
		this.datas = datas;
	}
	
	public void notifyDataSetChanged(List<T> datas){
		this.datas = datas;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas == null ? 0 : datas.size();
	}

	@Override
	public T getItem(int position) {
		return datas == null ? null : datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


}
