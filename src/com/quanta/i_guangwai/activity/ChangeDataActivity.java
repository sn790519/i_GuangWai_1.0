package com.quanta.i_guangwai.activity;

import java.util.HashMap;

import com.quanta.i_guangwai.R;
import com.quanta.i_guangwai.app.AppConfig;
import com.quanta.i_guangwai.async.QuantaAsync;
import com.quanta.i_guangwai.listener.AsyncCommentListener;
import com.quanta.i_guangwai.listener.AsyncUserDataListener;
import com.quanta.i_guangwai.model.User;
import com.quanta.i_guangwai.util.BaseTools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeDataActivity extends Activity implements OnClickListener {
	User user;
	private TextView back;
	private EditText et_nickname, et_academy, et_major, et_signature;
	private QuantaAsync sendUserDataAsync;
	private LinearLayout save_ly;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changedata);
		user = (User) getIntent().getSerializableExtra("user");
		init();
		show();
	}

	private void init() {
		// TODO Auto-generated method stub
		back = (TextView) findViewById(R.id.change_back);
		et_nickname = (EditText) findViewById(R.id.et_nickname);
		et_academy = (EditText) findViewById(R.id.et_academy);
		et_major = (EditText) findViewById(R.id.et_major);
		et_signature = (EditText) findViewById(R.id.et_signature);
		save_ly = (LinearLayout) this.findViewById(R.id.save_ly);
		save_ly.setOnClickListener(this);
		back.setOnClickListener(this);
		sendUserDataAsync = new QuantaAsync(ChangeDataActivity.this);
		sendUserDataAsync
				.setQuantaAsyncListener(new AsyncUserDataListener(this));

	}

	private void show() {
		// TODO Auto-generated method stub
		et_nickname.setHint(user.getName());
		et_signature.setHint(user.getSignature());
		et_academy.setHint(user.getAcademy());
		et_major.setHint(user.getMajor());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.change_back:
			onBackPressed();// ���÷��ؼ�
			finish();
			break;

		case R.id.save_ly:
			
			if (!et_nickname.equals("") || !et_signature.equals("")) {
				if (!user.getUsername().equals("")) {
					MainActivity.user.setName(et_nickname.getText().toString());
					MainActivity.user.setSignature(et_signature.getText()
							.toString());

					HashMap<String, String> taskArgs2 = new HashMap<String, String>();
					taskArgs2.put("username", user.getUsername());
					taskArgs2.put("nickname", et_nickname.getText().toString());
					taskArgs2.put("signature", et_signature.getText()
							.toString());
					sendUserDataAsync.post(2, AppConfig.sendUserDataUrl,
							taskArgs2);

				} else {
					Toast.makeText(this, "���ȵ�¼...", Toast.LENGTH_LONG).show();
				}

			}
			break;

		}
	}
}
