package com.example.listviewsinglechoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends Activity {
	private int cur_pos = -1;
	private List<HashMap<String, Object>> list;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) findViewById(R.id.listview1);
		list = new ArrayList<HashMap<String, Object>>();
		setData();
		final MyAdapter adapter = new MyAdapter(this);
		listview.setAdapter(adapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				cur_pos = position;
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void setData() {
		HashMap<String, Object> hashMap;
		for (int i = 0; i < 20; i++) {
			hashMap = new HashMap<String, Object>();
			hashMap.put("name", "" + i);
			hashMap.put("age", " " + i);
			hashMap.put("image", R.drawable.ic_launcher);
			list.add(hashMap);
		}
	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.list_child, null, false);
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap = list.get(position);
			ViewHold viewHold = new ViewHold();
			viewHold.img1 = (ImageView) convertView
					.findViewById(R.id.list_child_im1);
			viewHold.img2 = (ImageView) convertView
					.findViewById(R.id.list_child_im2);
			viewHold.tv1 = (TextView) convertView
					.findViewById(R.id.list_child_tv1);
			viewHold.tv2 = (TextView) convertView
					.findViewById(R.id.list_child_tv2);
			viewHold.tv1.setText("姓名: jack"+hashMap.get("name").toString());
			viewHold.tv2.setText("年龄: "+hashMap.get("age").toString());
			viewHold.img1
					.setBackgroundResource((Integer) (hashMap.get("image")));
			if (position == cur_pos) {
				viewHold.img2
						.setBackgroundResource(R.drawable.list_child_sel_chech);
			}
			return convertView;
		}

		class ViewHold {
			private ImageView img1;
			private ImageView img2;
			private TextView tv1;
			private TextView tv2;
		}
	}

}
