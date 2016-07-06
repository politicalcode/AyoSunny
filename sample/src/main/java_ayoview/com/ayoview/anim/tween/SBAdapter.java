package com.ayoview.anim.tween;

import android.widget.BaseAdapter;

import java.util.List;

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
