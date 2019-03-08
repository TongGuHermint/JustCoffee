package com.sh.justcoffee.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.j256.ormlite.dao.Dao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.BillDTO;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.utils.DataBaseHelper;
import com.sh.justcoffee.view.activity.MainActivity;
import com.sh.justcoffee.view.adapter.BillAdapter;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends BaseFragment implements OnRefreshListener {


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
	@BindView(R.id.swipe)
	SmartRefreshLayout mSwipe;
	@BindView(R.id.tv_total)
	TextView mTvTotal;



	private BillAdapter mAdapter;
	private BillDTO mBillDTO;
	private boolean isGetData = false;
	private MainActivity mMainActivity;
	private int totalMoney = 0;

	public BillFragment() {
		// Required empty public constructor
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mMainActivity = (MainActivity) context;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (/*!hasLoadDataFinished &&*/ isVisibleToUser) {
//			mSwipe.autoRefresh();
			mAdapter.setNewData(null);
			initData();
		}
	}


	@Override
	protected int getContentView() {
		return R.layout.fragment_bill;
	}

	@Override
	protected void initViews() {
		mTvTitle.setText("总账单");
		mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mAdapter = new BillAdapter(null);
		mSwipe.setOnRefreshListener(this);
		mRecycleView.setAdapter(mAdapter);
		mSwipe.setRefreshHeader(new ClassicsHeader(getActivity()));
		initData();
		mSwipe.autoRefresh();

	}

	private void updateTotalMoney() {
		totalMoney = SPUtils.getInstance().getInt("totalMoney");
		if (totalMoney < 0 ){
			totalMoney = 0;
		}else {
			totalMoney = SPUtils.getInstance().getInt("totalMoney");
		}
		mTvTotal.setText("¥"+String.valueOf(totalMoney));
	}

	private void initData() {
		mSwipe.finishRefresh();
		updateTotalMoney();
		/*mAdapter.addData(new BillDTO("美式咖啡", SPUtils.getInstance().getInt("美式咖啡") >0 ? SPUtils.getInstance().getInt("美式咖啡"):0));
		mAdapter.addData(new BillDTO("拿铁咖啡", SPUtils.getInstance().getInt("拿铁咖啡")>0 ? SPUtils.getInstance().getInt("拿铁咖啡") :0));
		mAdapter.addData(new BillDTO("卡布奇诺", SPUtils.getInstance().getInt("卡布奇诺")>0 ? SPUtils.getInstance().getInt("卡布奇诺"):0));
		mAdapter.addData(new BillDTO("平白", SPUtils.getInstance().getInt("平白")>0 ? SPUtils.getInstance().getInt("平白"):0));
		mAdapter.addData(new BillDTO("风味拿铁", SPUtils.getInstance().getInt("风味拿铁")>0 ? SPUtils.getInstance().getInt("风味拿铁"):0));
		mAdapter.addData(new BillDTO("焦糖玛奇朵", SPUtils.getInstance().getInt("焦糖玛奇朵")>0 ? SPUtils.getInstance().getInt("焦糖玛奇朵"):0));
		mAdapter.addData(new BillDTO("摩卡咖啡", SPUtils.getInstance().getInt("摩卡咖啡")>0 ?SPUtils.getInstance().getInt("摩卡咖啡"):0 ));
		mAdapter.addData(new BillDTO("青柠冰咖", SPUtils.getInstance().getInt("青柠冰咖")>0 ? SPUtils.getInstance().getInt("青柠冰咖"):0));
		mAdapter.addData(new BillDTO("鸳鸯咖啡", SPUtils.getInstance().getInt("鸳鸯咖啡")>0 ? SPUtils.getInstance().getInt("鸳鸯咖啡"):0));
		mAdapter.addData(new BillDTO("手冲", SPUtils.getInstance().getInt("手冲")>0 ? SPUtils.getInstance().getInt("手冲"):0));
		mAdapter.addData(new BillDTO("冰滴冷萃", SPUtils.getInstance().getInt("冰滴冷萃")>0 ? SPUtils.getInstance().getInt("冰滴冷萃"):0));
		mAdapter.addData(new BillDTO("牛奶", SPUtils.getInstance().getInt("牛奶")>0 ? SPUtils.getInstance().getInt("牛奶"):0));
		mAdapter.addData(new BillDTO("巧克力", SPUtils.getInstance().getInt("巧克力")>0 ?SPUtils.getInstance().getInt("巧克力"):0 ));
		mAdapter.addData(new BillDTO("抹茶拿铁", SPUtils.getInstance().getInt("抹茶拿铁")>0 ? SPUtils.getInstance().getInt("抹茶拿铁"):0));
		mAdapter.addData(new BillDTO("红丝绒拿铁", SPUtils.getInstance().getInt("红丝绒拿铁")>0 ? SPUtils.getInstance().getInt("红丝绒拿铁"):0));
		mAdapter.addData(new BillDTO("锡兰奶茶", SPUtils.getInstance().getInt("锡兰奶茶")>0 ? SPUtils.getInstance().getInt("锡兰奶茶"):0));
		mAdapter.addData(new BillDTO("巧克力沙冰", SPUtils.getInstance().getInt("巧克力沙冰")>0 ? SPUtils.getInstance().getInt("巧克力沙冰"):0));
		mAdapter.addData(new BillDTO("抹茶沙冰", SPUtils.getInstance().getInt("抹茶沙冰")>0 ? SPUtils.getInstance().getInt("抹茶沙冰"):0));
		mAdapter.addData(new BillDTO("红丝绒沙冰", SPUtils.getInstance().getInt("红丝绒沙冰")>0 ? SPUtils.getInstance().getInt("红丝绒沙冰"):0));
		mAdapter.addData(new BillDTO("摩卡沙冰", SPUtils.getInstance().getInt("摩卡沙冰")>0 ?  SPUtils.getInstance().getInt("摩卡沙冰"):0));
		mAdapter.addData(new BillDTO("风味咖啡沙冰", SPUtils.getInstance().getInt("风味咖啡沙冰")>0 ? SPUtils.getInstance().getInt("风味咖啡沙冰"):0));
		mAdapter.addData(new BillDTO("华夫饼", SPUtils.getInstance().getInt("华夫饼")>0 ? SPUtils.getInstance().getInt("华夫饼"):0));*/
		getMenuList();
	}


	@OnClick({R.id.iv_back, R.id.tv_righttext})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				break;
			case R.id.tv_righttext:
				break;
		}
	}


	@Override
	public void onRefresh(@NonNull RefreshLayout refreshLayout) {
		mAdapter.setNewData(null);
		initData();
	}


	private void getMenuList() {
		try {
			Dao<MenuDTO, Integer> menuDAO = getMenuDAO();
			List<MenuDTO> menuDTOList = menuDAO.queryForAll();
			if (menuDTOList.size() > 0) {
				for (MenuDTO menuDTO : menuDTOList) {
					Log.e("menuDTO", menuDTO.toString());
//					mAdapter.addData(new BillDTO(menuDTO.getName(), SPUtils.getInstance().getInt(menuDTO.getName()) > 0 ? SPUtils.getInstance().getInt(menuDTO.getName()) : 0));
					if (SPUtils.getInstance().getInt(menuDTO.getName()) > 0) {
						mAdapter.addData(new BillDTO(menuDTO.getName(), SPUtils.getInstance().getInt(menuDTO.getName()) > 0 ? SPUtils.getInstance().getInt(menuDTO.getName()) : 0));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
			mMenuDTODao = DataBaseHelper.getInstance(getActivity()).getDao(MenuDTO.class);
		}
		return mMenuDTODao;
	}
}
