package com.model;

import java.util.ArrayList;
import java.util.List;

public class DataFacotry {

	public void refresh(final int id, final CallListener listener) {
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<Person> list = new ArrayList<Person>();
				Person person = new Person();
				person.setId(1);
				person.setName(id + "a");
				list.add(person);

				Person person1 = new Person();
				person1.setId(2);
				person1.setName(id + "b");
				list.add(person1);
				listener.loadSucess(list);
			}

		}.start();
	}

	public void loadMore(final int id, final CallListener listener) {
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<Person> list = new ArrayList<Person>();
				Person person = new Person();
				person.setId(3);
				person.setName(id + "c");
				list.add(person);

				Person person1 = new Person();
				person1.setId(4);
				person1.setName(id + "d");
				list.add(person1);
				listener.loadSucess(list);
			}

		}.start();

	}
}
