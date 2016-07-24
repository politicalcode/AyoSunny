package com.ayoview.sample.tmpl_listview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;

import org.ayo.app.tmpl.FragmentContainerActivityAttacher;

public class TmplListActivity extends FragmentContainerActivityAttacher {
	
	public static void start(Context c, String p){
		Intent i = new Intent(c, TmplListActivity.class);
		i.putExtra("p", p);
		c.startActivity(i);
	}

	private TmplFragment fragment;

	@Override
	protected void initFragment(FragmentManager fragmentManager, View root) {
		fragment = new TmplFragment(); // (TmplFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment1);
		fragment.isFirstPage(true);
		fragmentManager.beginTransaction().replace(root.getId(), fragment).commit();
	}


}
