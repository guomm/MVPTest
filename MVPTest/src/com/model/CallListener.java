package com.model;

import java.util.List;

public interface CallListener<T> {
	void loadSucess(List<T> list);
	void loadError();
}
