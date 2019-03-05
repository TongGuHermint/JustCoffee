package com.sh.justcoffee.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.view.activity.MainActivity;
import com.sh.justcoffee.view.adapter.MenuAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


	@BindView(R.id.recycleView)
	RecyclerView mRecycleView;
	@BindView(R.id.iv_back)
	LinearLayout mIvBack;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.tv_righttext)
	TextView mTvRighttext;
	@BindView(R.id.rl_actionbar)
	RelativeLayout mRlActionbar;
	@BindView(R.id.tv_total)
	TextView mTvTotal;
	private MenuAdapter mAdapter;
	private MainActivity mMainActivity;
	private int totalMoney = 0;

	public MenuFragment() {
		// Required empty public constructor
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mMainActivity = (MainActivity) context;
	}

	@Override
	protected int getContentView() {
		return R.layout.fragment_menu;
	}

	@Override
	protected void initViews() {
		mTvTitle.setText("菜单");
		mTvRighttext.setText("添加");
		mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mAdapter = new MenuAdapter(null);
		initData();
		mRecycleView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(this);
		mTvTotal.setText("¥"+String.valueOf(SPUtils.getInstance().getInt("totalMoney")));
	}

	private void initData() {
		mAdapter.addData(new MenuDTO("美式咖啡", "Cafe Americano", "18"));
		mAdapter.addData(new MenuDTO("拿铁咖啡", "Cafe Latte", "22"));
		mAdapter.addData(new MenuDTO("卡布奇诺", "Cappuccino", "22"));
		mAdapter.addData(new MenuDTO("平白", "Falt White", "20"));
		mAdapter.addData(new MenuDTO("风味拿铁", "Flavored Latte", "24"));
		mAdapter.addData(new MenuDTO("焦糖玛奇朵", "Caramel Macchiato", "25"));
		mAdapter.addData(new MenuDTO("摩卡咖啡", "Cafe Mocha", "27"));
		mAdapter.addData(new MenuDTO("青柠冰咖", "Lime Ice Coffee", "24"));
		mAdapter.addData(new MenuDTO("鸳鸯咖啡", "Black Tea Coffee", "24"));

		mAdapter.addData(new MenuDTO("手冲", "Pour Over", "25"));
		mAdapter.addData(new MenuDTO("冰滴冷萃", "Ice Drip Coffee", "30"));

		mAdapter.addData(new MenuDTO("牛奶", "Milk", "18"));
		mAdapter.addData(new MenuDTO("巧克力", "Chocolates", "22"));
		mAdapter.addData(new MenuDTO("抹茶拿铁", "Matcha Latte", "22"));
		mAdapter.addData(new MenuDTO("红丝绒拿铁", "Red velvet Latte", "22"));
		mAdapter.addData(new MenuDTO("锡兰奶茶", "Original Milk Tea", "18"));


		mAdapter.addData(new MenuDTO("巧克力沙冰", "Chocolate Ice", "22"));
		mAdapter.addData(new MenuDTO("抹茶沙冰", "Matcha Ice", "22"));
		mAdapter.addData(new MenuDTO("红丝绒沙冰", "Red velvet Ice", "22"));
		mAdapter.addData(new MenuDTO("摩卡沙冰", "Mocha Ice", "25"));
		mAdapter.addData(new MenuDTO("风味咖啡沙冰", "Flavored Coffee Ice", "25"));

		mAdapter.addData(new MenuDTO("华夫饼", "Waffle", "18"));


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
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		MenuDTO dto = ((MenuDTO) adapter.getData().get(position));
		showDialog(dto);

	}

	private void showDialog(final MenuDTO dto) {
		new QMUIDialog.MessageDialogBuilder(getActivity())
				.setMessage("点击确定添加至今日账单")
				.addAction("取消", new QMUIDialogAction.ActionListener() {
					@Override
					public void onClick(QMUIDialog dialog, int index) {
						dialog.dismiss();
					}
				})
				.addAction("确定", new QMUIDialogAction.ActionListener() {
					@Override
					public void onClick(QMUIDialog dialog, int index) {
						Toast.makeText(getActivity(), dto.getName() + dto.getEnglish() + dto.getPrice(), Toast.LENGTH_SHORT).show();
						addCups(dto.getName());
						addPrice(dto.getPrice());
						dialog.dismiss();
					}
				})
				.show();
	}

	private void addPrice(String price) {
		totalMoney = totalMoney + Integer.valueOf(price);
		SPUtils.getInstance().put("totalMoney",totalMoney);
		mTvTotal.setText("¥"+String.valueOf(totalMoney));
	}

	private void addCups(String name) {
		if (SPUtils.getInstance().getInt(name) > 0) {
			int num = SPUtils.getInstance().getInt(name) + 1;
			SPUtils.getInstance().put(name, num);
		} else {
			SPUtils.getInstance().put(name, 1);
		}
	}

}
