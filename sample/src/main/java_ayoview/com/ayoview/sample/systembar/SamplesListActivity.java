/*
 * Copyright (C) 2013 readyState Software Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ayoview.sample.systembar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.ayo.app.base.ActivityAttacher;

import java.util.ArrayList;
import java.util.List;


public class SamplesListActivity extends ListActivity {

	private IntentAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new IntentAdapter();
		setListAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//startActivity(mAdapter.getItem(position));
		if(position == 0){
			ActivityAttacher.startActivity(this, ColorActivity.class);
		}
		if(position == 1){
			ActivityAttacher.startActivity(this, DefaultActivity.class);
		}
		if(position == 2){
			ActivityAttacher.startActivity(this, MatchActionBarActivity.class);
		}
	}

	private class IntentAdapter extends BaseAdapter {
		private final List<CharSequence> mNames;

		IntentAdapter() {
			mNames = new ArrayList<CharSequence>();

			mNames.add("ColorActivity");
			mNames.add("DefaultActivity");
			mNames.add("MatchActionBarActivity");
		}

//		void refresh() {
//			mNames.clear();
//			mIntents.clear();
//
//			final Intent mainIntent = new Intent(ACTION_MAIN, null);
//			mainIntent.addCategory("com.readystatesoftware.systembartint.SAMPLE");
//
//			PackageManager pm = getPackageManager();
//			final List<ResolveInfo> matches = pm.queryIntentActivities(
//					mainIntent, 0);
//			for (ResolveInfo match : matches) {
//				Intent intent = new Intent();
//				intent.setClassName(match.activityInfo.packageName,
//						match.activityInfo.name);
//				final CharSequence name = match.loadLabel(pm);
//				mNames.add(name);
//				mIntents.put(name, intent);
//			}
//
//			notifyDataSetChanged();
//		}

		@Override
		public int getCount() {
			return mNames.size();
		}

		@Override
		public Intent getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = (TextView) convertView;
			if (convertView == null) {
				tv = (TextView) LayoutInflater.from(SamplesListActivity.this)
						.inflate(android.R.layout.simple_list_item_1, parent,
								false);
			}
			tv.setText(mNames.get(position));
			return tv;
		}
	}

}
