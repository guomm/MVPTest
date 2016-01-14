package com.example.mvptest;

import java.util.List;

import com.model.Person;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private List<Person> list;
	private Context context;
	public MyAdapter(List<Person> list,Context context) {
		this.list = list;
		this.context=context;
	}
	
	public void addTolist(List<Person> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void changeList(List<Person> list){
		this.list.clear();
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(
				R.layout.list_item, null);
		TextView text=(TextView)view.findViewById(R.id.name);
		Person person=list.get(position);
		text.setText(person.getName());
		return view;
	}
	

}
