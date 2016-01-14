package com.presentor;

import java.util.List;

import android.os.Handler;

import com.example.mvptest.ActivityInterface;
import com.model.CallListener;
import com.model.DataFacotry;
import com.model.Person;

public class Presentor {
	private ActivityInterface ainterface;
	private DataFacotry facotry;
	private Handler handler = new Handler();

	public Presentor(ActivityInterface ainterface) {
		this.ainterface = ainterface;
		facotry = new DataFacotry();
	}

	public void loadData() {
		ainterface.isLoading();
		facotry.refresh(ainterface.getUserId(), new CallListener<Person>() {

			@Override
			public void loadSucess(final List<Person> list) {
				handler.post(new Runnable() {

					@Override
					public void run() {
						ainterface.refreshSuccess(list);
						ainterface.loadEnd();
					}

				});

			}

			@Override
			public void loadError() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						ainterface.loadError();
						ainterface.loadEnd();
					}

				});

			}
		});
	}

	public void loadMore() {
		ainterface.isLoading();
		facotry.loadMore(ainterface.getUserId(), new CallListener<Person>() {

			@Override
			public void loadSucess(final List<Person> list) {
				handler.post(new Runnable() {

					@Override
					public void run() {
						ainterface.loadMoreSuccess(list);
						ainterface.loadEnd();
					}

				});

			}

			@Override
			public void loadError() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						ainterface.loadError();
						ainterface.loadEnd();
					}

				});
			}
		});
	}
}