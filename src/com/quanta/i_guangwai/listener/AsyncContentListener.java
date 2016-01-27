package com.quanta.i_guangwai.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.quanta.i_guangwai.activity.MainActivity;
import com.quanta.i_guangwai.activity.SearchActivity;
import com.quanta.i_guangwai.async.QuantaAsync.OnQuantaAsyncListener;
import com.quanta.i_guangwai.async.QuantaBaseMessage;
import com.quanta.i_guangwai.model.Article;
import com.quanta.i_guangwai.model.Content;

public class AsyncContentListener implements OnQuantaAsyncListener {
	private Activity activity;
	private int activityId;

	public AsyncContentListener(Activity activity, int activityId) {
		this.activity = activity;
		this.activityId = activityId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onComplete(int taskId, QuantaBaseMessage baseMessage) {
		// TODO Auto-generated method stub
		Toast.makeText(activity, "���ڼ���..", Toast.LENGTH_SHORT).show();
		// if (baseMessage.getStatus().equals("1")) {

		try {
			Content content = (Content) baseMessage.getData("Content");
			Article article = findArticle(content);// ͨ����content��id�ҵ���Ӧ��article,����content������set��article
			Message message = Message.obtain();
			message.obj = article;
			ItemClickListener.handler.sendMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * �ҵ���Ӧarticle
	 */

	private Article findArticle(Content content) {
		Article article = null;
		ArrayList<Article> articleList = null;
		if (activityId == 1) {
			articleList = MainActivity.articleList;
		}
		if (activityId == 2) {
			articleList = SearchActivity.articleList;
		}
		for (int i = 0; i < articleList.size(); i++) {

			article = articleList.get(i);
			Log.v("myLog", "id" + article.getId() + content.getContent());
			Log.v("myLog", "con_id" + content.getId());
			if (article.getId().equals(content.getId())) {
				article.setContent(content.getContent());
				break;
			}
		}
		return article;
	}

	@Override
	public void onComplete(int taskId) {

		Toast.makeText(activity, "����ɹ���", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNetWorkError(int taskId, String errorMsg) {

		Toast.makeText(activity, "�������", Toast.LENGTH_SHORT).show();
	}
}