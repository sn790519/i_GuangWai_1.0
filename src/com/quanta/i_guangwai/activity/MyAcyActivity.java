package com.quanta.i_guangwai.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.quanta.i_guangwai.R;
import com.quanta.i_guangwai.adapter.MyPagerAdapter;
/*
 * 我的动态
 */
public class MyAcyActivity extends Activity implements OnClickListener {

	ViewPager mViewPager;
	private LinearLayout linearLayout1;
	private TextView textView1, textView2, acy_back;
	private int currIndex = 0;// 当前页卡编号
	private ImageView imageView;// 页卡标题动画图片
	private int textViewW = 0;// 页卡标题的宽度
	private List<View> listViews;
	private View view1, view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_myacy);

		initControl();
		initViewPager();
		InitTextView();
		InitImageView();
	}

	private void initControl() {
		imageView = (ImageView) findViewById(R.id.cursor);
		acy_back = (TextView) findViewById(R.id.acy_back);
		linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		mViewPager = (ViewPager) findViewById(R.id.pvr_user_pager);
		mViewPager.setOffscreenPageLimit(2);/* 预加载页面 */
	}

	/* 初始化ViewPager */
	private void initViewPager() {
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		view1 = mInflater.inflate(R.layout.text, null);
		view2 = mInflater.inflate(R.layout.text, null);
		listViews.add(view1);
		listViews.add(view2);
		mViewPager.setAdapter(new MyPagerAdapter(listViews));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	/* 初始化页卡标题 */
	private void InitTextView() {
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		acy_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.acy_back:
			onBackPressed();// 调用返回键
			finish();

		}

	}

	/* 页卡切换监听 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			if (textViewW == 0) {
				textViewW = textView1.getWidth();
			}
			Animation animation = new TranslateAnimation(textViewW * currIndex,
					textViewW * arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);/* True:图片停在动画结束位置 */
			animation.setDuration(300);
			imageView.startAnimation(animation);
			// setTextTitleSelectedColor(arg0);
			setImageViewWidth(textViewW);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/* 设置图片宽度 */
	private void setImageViewWidth(int width) {
		if (width != imageView.getWidth()) {
			LayoutParams laParams = (LayoutParams) imageView.getLayoutParams();
			laParams.width = width;
			imageView.setLayoutParams(laParams);
		}
	}

	/* 标题点击监听 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			mViewPager.setCurrentItem(index);
		}
	}

	/* 初始化动画 */
	private void InitImageView() {
		Matrix matrix = new Matrix();
		matrix.postTranslate(0, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
	}

}
