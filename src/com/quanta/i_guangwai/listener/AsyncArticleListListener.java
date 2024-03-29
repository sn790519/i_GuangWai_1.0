package com.quanta.i_guangwai.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.quanta.i_guangwai.activity.MainActivity;
import com.quanta.i_guangwai.async.QuantaBaseMessage;
import com.quanta.i_guangwai.async.QuantaAsync.OnQuantaAsyncListener;
import com.quanta.i_guangwai.dao.ArticleDao;
import com.quanta.i_guangwai.model.Article;

/**
 * 异步http请求监听器 监听请求事件
 */
public class AsyncArticleListListener implements OnQuantaAsyncListener {

	private Activity activity;

	// ArrayList<Article> articleList;

	public AsyncArticleListListener(Activity activity) {
		this.activity = activity;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onComplete(int taskId, QuantaBaseMessage baseMessage) {
		// TODO Auto-generated method stub
		Toast.makeText(activity, "正在加载..", Toast.LENGTH_SHORT).show();
		// if (baseMessage.getStatus().equals("1")) {
	
		try {

			MainActivity.articleList = (ArrayList<Article>) baseMessage
					.getDataList("Article");
	
			// 保存到数据库

			ArticleDao articleDao = new ArticleDao(activity);
			articleDao.clearAllArticle();
			articleDao.addArticle(MainActivity.articleList);

			// Toast.makeText(activity, "返回成功", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onComplete(int taskId) {

		Toast.makeText(activity, "请求成功！", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNetWorkError(int taskId, String errorMsg) {

		Toast.makeText(activity, "网络错误！", Toast.LENGTH_SHORT).show();
	}
}