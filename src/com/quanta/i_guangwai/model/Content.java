package com.quanta.i_guangwai.model;

import com.quanta.i_guangwai.async.QuantaBaseModel;

public class Content extends QuantaBaseModel {
	/** ����id */
	private String id;
	/** �������� */
	private String content;

	public Content() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
