package com.quanta.i_guangwai.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanta.i_guangwai.R;
import com.quanta.i_guangwai.activity.ContentActivity;
import com.quanta.i_guangwai.model.Comment;
import com.quanta.i_guangwai.util.Bitmaploader;

public class CommentListviewAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater myInflater;
	AllViewHolder holder;
	Bitmaploader bitmapTools;

	// ArrayList<Article> articleList;

	public CommentListviewAdapter(Context context) {
		this.context = context;
		// this.articleList=articleList;
		myInflater = LayoutInflater.from(context);
		this.bitmapTools = new Bitmaploader(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.head));

	}

	@Override
	public int getCount() {
		return ContentActivity.commentList.size();
	}

	@Override
	public Object getItem(int position) {
		return ContentActivity.commentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Comment comment = ContentActivity.commentList.get(position);
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.comment_list_item, null);
			holder = new AllViewHolder();
			holder.commentHead = (ImageView) convertView
					.findViewById(R.id.commentHead);
			holder.nickname = (TextView) convertView
					.findViewById(R.id.nickname);
			holder.comment = (TextView) convertView.findViewById(R.id.comment);
			convertView.setTag(holder);

		} else {

			holder = (AllViewHolder) convertView.getTag();
		}
		// holder.title_tv.setText(mSchool.getSchoolId());
		// holder.desc_tv.setText("" + mSchool.getSchoolName());

		holder.nickname.setText(comment.getCommentnickname());
		holder.comment.setText(comment.getComment());
		String imgURL = comment.getHead();

		bitmapTools
				.loadBitmap(this, imgURL, holder.commentHead, 100, 100, true);

		return convertView;
	}

	class AllViewHolder {
		ImageView commentHead;
		TextView nickname;
		TextView comment;
	}
}
