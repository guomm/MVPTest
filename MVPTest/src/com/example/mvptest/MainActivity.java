package com.example.mvptest;

import java.util.ArrayList;
import java.util.List;

import com.model.Person;
import com.presentor.Presentor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements ActivityInterface {

	private Button refresh, loadMore;
	private ListView listView;
	private ProgressBar bar;
	private int userId;
	private MyAdapter adapter;
	private Presentor presentor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		refresh = (Button) findViewById(R.id.refresh);
		loadMore = (Button) findViewById(R.id.loadMore);
		listView = (ListView) findViewById(R.id.listView);
		bar = (ProgressBar) findViewById(R.id.bar);

		userId = 1;
		List<Person> list=new ArrayList<Person>();
		list.add(new Person(0,"o"));
		adapter = new MyAdapter(list,this);
		presentor = new Presentor(this);
		listView.setAdapter(adapter);
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				presentor.loadData();
			}

		});

		loadMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				presentor.loadMore();
			}

		});
	}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}


	@Override
	public void isLoading() {
		// TODO Auto-generated method stub
		bar.setVisibility(View.VISIBLE);
	}

	@Override
	public void loadEnd() {
		// TODO Auto-generated method stub
		bar.setVisibility(View.GONE);
	}

	@Override
	public void loadError() {
		Toast.makeText(this, "load error", Toast.LENGTH_LONG).show();
	}

	@Override
	public void refreshSuccess(List<Person> list) {
		// TODO Auto-generated method stub
		adapter.changeList(list);
	}

	@Override
	public void loadMoreSuccess(List<Person> list) {
		// TODO Auto-generated method stub
		adapter.addTolist(list);
	}
}
