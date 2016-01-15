# a simple MVP sample
    在MVP模式中：
    *	View 对应于Activity，负责View的绘制以及与用户交互
    *	Model 是业务逻辑和实体模型
    *	Presenter 负责完成View于Model间的交互
 
    对于View的接口，去观察功能上的操作，然后考虑:
    *	该操作需要什么？ 
    *	该操作的结果，对应的反馈？
    *	该操作过程中对应的友好的交互？

这个项目写的是根据一个人的id,拉取好友信息。对于这个项目来说，view提供的借口为：
```
  int getUserId();

  void refreshSuccess(List<Person> list);
	void loadMoreSuccess(List<Person> list);

	void isLoading();
	void loadEnd();
	void loadError();
```
model提供的接口为：
```
public void refresh(final int id, final CallListener listener); public void loadMore(final int id, final CallListener listener);
```
presentor提供的接口为：
```
public void loadData();
public void loadMore();
```
presentor的实现如下：
```
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
```
Presentor负责协调model层和view层，比如调用loadData方法时，首先让view界面开启进度条isLoading，调用factroy即model层获取好友信息factroy.refresh()，同时传给factory一个回调函数，当拉取成功后，view层调用loadMoreSuccess更新界面，同时停止进度条loadEnd，如果拉取失败，则用loadError更新界面，同时停止进度条loadEnd.
