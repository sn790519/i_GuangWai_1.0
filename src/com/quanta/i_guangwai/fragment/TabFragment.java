package com.quanta.i_guangwai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quanta.i_guangwai.R;
import com.quanta.i_guangwai.activity.MainActivity;
import com.quanta.i_guangwai.adapter.MainListViewAdapter;
import com.quanta.i_guangwai.listener.ItemClickListener;
import com.quanta.i_guangwai.listener.RefreshListener;

/** �ĸ���������fragment */
public class TabFragment extends Fragment {

	/** ����ˢ��listview */
	public PullToRefreshListView mPullRefreshListView;
	public static MainListViewAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_tab, null);
		mPullRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.pull_refresh_list);
		adapter = new MainListViewAdapter(getActivity());// new һ���Զ���adapter
		mPullRefreshListView.setAdapter(adapter);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListviewListener();
	}

	private void setListviewListener() {

		mPullRefreshListView.setOnRefreshListener(new RefreshListener(
				getActivity(), mPullRefreshListView, adapter));// ����ˢ�¼���
		mPullRefreshListView.setOnItemClickListener(new ItemClickListener(
				getActivity(), 1));// ����item����
	}

}
