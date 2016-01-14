package com.example.mvptest;

import java.util.List;

import com.model.Person;


public interface ActivityInterface {

	int getUserId();

	void isLoading();

	void loadEnd();

	void refreshSuccess(List<Person> list);

	void loadMoreSuccess(List<Person> list);

	void loadError();

}
