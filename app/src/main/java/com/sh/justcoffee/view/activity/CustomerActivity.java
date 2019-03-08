package com.sh.justcoffee.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.Dao;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.BillDTO;
import com.sh.justcoffee.entity.CustomerDTO;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.utils.CustomerHelper;
import com.sh.justcoffee.utils.DataBaseHelper;
import com.sh.justcoffee.view.adapter.CustomerAdapter;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerActivity extends BaseActivity {


	@BindView(R.id.iv_back)
	LinearLayout mIvBack;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.tv_righttext)
	TextView mTvRighttext;
	@BindView(R.id.rl_actionbar)
	RelativeLayout mRlActionbar;
	@BindView(R.id.recycleView)
	RecyclerView mRecycleView;

	private CustomerAdapter mAdapter;

	@Override
	protected int getContentView() {
		return R.layout.activity_customer;
	}

	@Override
	protected void initViews() {
		BarUtils.setStatusBarAlpha(this, 0);
		mIvBack.setVisibility(View.VISIBLE);
		mTvTitle.setText("历史帐单");
		mRecycleView.setLayoutManager(new LinearLayoutManager(this));
		mAdapter = new CustomerAdapter(null);
		getCustomerData();
		mRecycleView.setAdapter(mAdapter);
	}


	@OnClick({R.id.iv_back, R.id.tv_righttext})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				finish();
				break;
			case R.id.tv_righttext:
				break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void getCustomerData() {
		try {
			Dao<CustomerDTO, Integer> customerDao = getCustomerDao();
			List<CustomerDTO> customerDTOList = customerDao.queryForAll();
			if (customerDTOList.size() > 0) {
				for (CustomerDTO customerDTO : customerDTOList) {
					Log.e("customerDTO", customerDTO.toString());
					mAdapter.addData(customerDTO);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private Dao<CustomerDTO, Integer> mCustomerDao;
	/**
	 * 获取CustomerDTO
	 * @throws SQLException
	 */
	public Dao<CustomerDTO,Integer> getCustomerDao() throws SQLException {

		if (mCustomerDao == null)
		{
			mCustomerDao = CustomerHelper.getInstance(this).getDao(CustomerDTO.class);
		}
		return mCustomerDao;
	}
}
