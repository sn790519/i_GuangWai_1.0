package com.quanta.i_guangwai.model;

import com.quanta.i_guangwai.async.QuantaBaseModel;

public class Comment extends QuantaBaseModel {
	/**
	 * 评论id
	 */
	private String id;
	/**
	 * 评论者的账号
	 */
	private String commentusername;
	/**
	 * 评论者的昵称
	 */
	private String commentnickname;
	/**
	 * 评论的内容
	 */
	private String comment;
	/**
	 * 评论者的昵称
	 */
	private String commentdate;
	/**
	 * 评论者的头像地址
	 */
	private String head;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentusername() {
		return commentusername;
	}

	public void setCommentusername(String commentusername) {
		this.commentusername = commentusername;
	}

	public String getCommentnickname() {
		return commentnickname;
	}

	public void setCommentnickname(String commentnickname) {
		this.commentnickname = commentnickname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
