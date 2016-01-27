package com.quanta.i_guangwai.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.quanta.i_guangwai.activity.ContentActivity;
import com.quanta.i_guangwai.activity.SearchActivity;
import com.quanta.i_guangwai.async.QuantaBaseMessage;
import com.quanta.i_guangwai.async.QuantaAsync.OnQuantaAsyncListener;
import com.quanta.i_guangwai.dao.ArticleDao;
import com.quanta.i_guangwai.model.Article;
import com.quanta.i_guangwai.model.Comment;

/**
 * �첽http��������� ���������¼�
 */
public class AsyncCommentListener implements OnQuantaAsyncListener {

	private Activity activity;

	// ArrayList<Comment> commentList;

	public AsyncCommentListener(Activity activity) {
		this.activity = activity;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onComplete(int taskId, QuantaBaseMessage baseMessage) {
		// TODO Auto-generated method stub
		// Toast.makeText(activity, "���ڼ�������..", Toast.LENGTH_SHORT).show();
		// if (baseMessage.getStatus().equals("1")) {

		Log.v("myLog", "����data" + baseMessage.getData());
		if (baseMessage.getStatus().equals("1")) {
			try {
	
				ArrayList<Comment> temp = (ArrayList<Comment>) baseMessage
						.getDataList("Comment");
				ContentActivity.commentList = temp;
				ContentActivity.adapter.notifyDataSetChanged();// ����listview
				// Toast.makeText(activity, "���سɹ�..",
				// Toast.LENGTH_SHORT).show();

				// ArticleDao articleDao = new ArticleDao(activity);
				// articleDao.clearAllArticle();
				// articleDao.addArticle(articleList);
				// Toast.makeText(activity, "���سɹ�", Toast.LENGTH_SHORT).show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (baseMessage.getStatus().equals("0")) {
			Toast.makeText(activity, "��������", Toast.LENGTH_SHORT).show();
		}

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