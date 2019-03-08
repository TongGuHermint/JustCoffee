package com.sh.justcoffee.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.Dao;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.BillDTO;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.utils.DataBaseHelper;
import com.sh.justcoffee.view.adapter.BillAdapter;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TotalActivity extends BaseActivity {


	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.recycleView)
	RecyclerView mRecycleView;
	@BindView(R.id.btn)
	Button mBtn;
	@BindView(R.id.iv_back)
	LinearLayout mIvBack;
	@BindView(R.id.tv_total)
	TextView mTvTotal;
	@BindView(R.id.tv_righttext)
	TextView mTvRighttext;

	private BillAdapter mAdapter;
	private int totalMoney = 0;

	@Override
	protected int getContentView() {
		return R.layout.activity_total;
	}

	@Override
	protected void initViews() {
		BarUtils.setStatusBarAlpha(this, 0);
		mIvBack.setVisibility(View.VISIBLE);
		mTvTitle.setText("总帐单");
		mBtn.setText("清空今日数据");
		mTvRighttext.setText("历史账单");
		mRecycleView.setLayoutManager(new LinearLayoutManager(this));
		mAdapter = new BillAdapter(null);
		mRecycleView.setAdapter(mAdapter);
		totalMoney = SPUtils.getInstance().getInt("totalMoney");
		if (totalMoney < 0) {
			totalMoney = 0;
		} else {
			totalMoney = SPUtils.getInstance().getInt("totalMoney");
		}
		mTvTotal.setText("¥" + String.valueOf(totalMoney));
	}

	@OnClick({R.id.iv_back, R.id.btn,R.id.tv_righttext})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				finish();
				break;
			case R.id.btn:
				SPUtils.getInstance().clear();
				finish();
				break;
			case R.id.tv_righttext:
				startActivity(new Intent(TotalActivity.this,CustomerActivity.class));
				break;
		}
	}


	private Dao<MenuDTO, Integer> mMenuDTODao;

	/**
	 * 获得MenuDTO
	 *
	 * @throws SQLException
	 */
	public Dao<MenuDTO, Integer> getMenuDAO() throws SQLException {

		if (mMenuDTODao == null) {
			mMenuDTODao = DataBaseHelper.getInstance(this).getDao(MenuDTO.class);
		}
		return mMenuDTODao;
	}

	private void getMenuList() {
		try {
			Dao<MenuDTO, Integer> menuDAO = getMenuDAO();
			List<MenuDTO> menuDTOList = menuDAO.queryForAll();
			if (menuDTOList.size() > 0) {
				for (MenuDTO menuDTO : menuDTOList) {
					Log.e("menuDTO", menuDTO.toString());
					if (SPUtils.getInstance().getInt(menuDTO.getName()) > 0) {
						mAdapter.addData(new BillDTO(menuDTO.getName(), SPUtils.getInstance().getInt(menuDTO.getName()) > 0 ? SPUtils.getInstance().getInt(menuDTO.getName()) : 0));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.setNewData(null);
		getMenuList();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
