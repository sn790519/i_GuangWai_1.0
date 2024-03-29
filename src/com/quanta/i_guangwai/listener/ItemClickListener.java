package com.quanta.i_guangwai.listener;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.quanta.i_guangwai.activity.ContentActivity;
import com.quanta.i_guangwai.activity.MainActivity;
import com.quanta.i_guangwai.activity.SearchActivity;
import com.quanta.i_guangwai.app.AppConfig;
import com.quanta.i_guangwai.async.QuantaAsync;
import com.quanta.i_guangwai.model.Article;

public class ItemClickListener implements OnItemClickListener {
	QuantaAsync contentAsync;
	public static Handler handler = null;
	private Activity activity;
	private int ActivityId;// ActivityId用来标识不同的Acitvity

	public ItemClickListener(Activity activity, int ActivityId) {
		this.activity = activity;
		this.ActivityId = ActivityId;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Article article = null;
		if (ActivityId == 1) {
			article = MainActivity.articleList.get(position - 1);
		}
		if (ActivityId == 2) {
			article = SearchActivity.articleList.get(position);
		}
		// Toast.makeText(activity, "id" + article.getId(), Toast.LENGTH_SHORT)
		// .show();
		HashMap<String, String> taskArgs = new HashMap<String, String>();
		taskArgs.put("ContentId", article.getId());

		contentAsync = new QuantaAsync(activity);
		contentAsync.post(2, AppConfig.contentUrl, taskArgs);
		contentAsync.setQuantaAsyncListener(new AsyncContentListener(activity,ActivityId));

		// 设置handle等待AsyncContentListener返回有正文的article对象

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.obj != null) {
					Article article = (Article) msg.obj;
					Bundle bundle = new Bundle();
					bundle.putSerializable("article", article);// 传递article对象
					Intent intent = new Intent();
					intent.putExtras(bundle);
					intent.setClass(activity, ContentActivity.class);
					activity.startActivity(intent);
				}
			}

		};

	}
}
