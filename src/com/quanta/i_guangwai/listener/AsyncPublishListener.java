package com.quanta.i_guangwai.listener;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.quanta.i_guangwai.async.QuantaAsync.OnQuantaAsyncListener;
import com.quanta.i_guangwai.async.QuantaBaseMessage;

public class AsyncPublishListener implements OnQuantaAsyncListener {

	private Activity activity;

	public AsyncPublishListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onNetWorkError(int taskId, String errorMsg) {
		Toast.makeText(activity, "error:" + errorMsg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onComplete(int taskId) {
		Toast.makeText(activity, "请求成功", Toast.LENGTH_LONG).show();
		Log.i("fragment", "-----" + taskId);
	}

	@Override
	public void onComplete(int taskId, QuantaBaseMessage baseMessage) {


		if (baseMessage.getStatus().equals("1")) {
			Toast.makeText(activity, "发布成功", Toast.LENGTH_LONG).show();
		}
		if (baseMessage.getStatus().equals("0")) {
			Toast.makeText(activity, "发布失败", Toast.LENGTH_LONG).show();
		}
	}
}