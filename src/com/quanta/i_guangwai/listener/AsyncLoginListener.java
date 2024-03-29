package com.quanta.i_guangwai.listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.quanta.i_guangwai.activity.MainActivity;
import com.quanta.i_guangwai.async.QuantaAsync.OnQuantaAsyncListener;
import com.quanta.i_guangwai.async.QuantaBaseMessage;
import com.quanta.i_guangwai.model.User;

public class AsyncLoginListener implements OnQuantaAsyncListener {

	private Activity activity;

	public AsyncLoginListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onNetWorkError(int taskId, String errorMsg) {
		Toast.makeText(activity, "error:" + errorMsg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onComplete(int taskId) {
		Toast.makeText(activity, "请求成功", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onComplete(int taskId, QuantaBaseMessage baseMessage) {
//
//		Toast.makeText(activity, baseMessage.getStatus(), Toast.LENGTH_LONG)
//				.show();
		//
		if (baseMessage.getStatus().equals("1")) {
			Toast.makeText(activity, "登录成功", Toast.LENGTH_LONG).show();
//			Toast.makeText(activity, baseMessage.getData(), Toast.LENGTH_LONG).show();

			try {
				User user = (User) baseMessage.getData("User");
				Log.v("myLog", "-----user");
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("user", user);// 传递对象
				
				intent.putExtras(bundle);
				intent.setClass(activity, MainActivity.class);
				activity.startActivity(intent);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (baseMessage.getStatus().equals("0")) {
			Toast.makeText(activity, "用户名或密码错误", Toast.LENGTH_LONG).show();
		}
	}
}