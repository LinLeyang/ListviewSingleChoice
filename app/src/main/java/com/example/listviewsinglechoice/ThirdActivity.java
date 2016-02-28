package com.example.listviewsinglechoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ThirdActivity extends Activity {
	private List<HashMap<String, Object>> list;
	private ListView listview;
	private ImageView[] btnArr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) findViewById(R.id.listview1);
		list = new ArrayList<HashMap<String, Object>>();
		setData();
		MyAdapter adapter = new MyAdapter(this);
		listview.setAdapter(adapter);
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
		private Context myContent = null;

		public MyAdapter(Context context) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.myContent = context;
			setData();
		}

		private void setData() {
			btnArr = new ImageView[list.size()];
			for (int i = 0; i < list.size(); i++) {
				btnArr[i] = new ImageView(myContent);
				btnArr[i].setTag(i);
			}
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
			viewHold.tv1.setText("姓名: jack" + hashMap.get("name").toString());
			viewHold.tv2.setText("年龄: " + hashMap.get("age").toString());
			viewHold.img1
					.setBackgroundResource((Integer) (hashMap.get("image")));
			btnArr[position] = viewHold.img2;
			btnArr[position].setTag(position);
			convertView.setTag(viewHold);
			viewHold.img2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < list.size(); i++) {
						if (v.getTag().equals(i)) {
							btnArr[i]
									.setBackgroundResource(R.drawable.list_child_sel_chech);
						} else {
							btnArr[i]
									.setBackgroundResource(R.drawable.list_child_sel_nocheck);
						}
					}
				}
			});
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
