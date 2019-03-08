package com.sh.justcoffee.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.j256.ormlite.dao.Dao;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.CustomerDTO;
import com.sh.justcoffee.utils.CustomerHelper;
import com.sh.justcoffee.utils.DataBaseHelper;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.view.activity.AddItemActivity;
import com.sh.justcoffee.view.activity.MainActivity;
import com.sh.justcoffee.view.activity.TotalActivity;
import com.sh.justcoffee.view.adapter.MenuAdapter;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {


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
	private int OneBillMoney = 0;
	private MenuDTO mMenuDTO;

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
//		ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
//		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
//		itemTouchHelper.attachToRecyclerView(mRecycleView);
		mRecycleView.setAdapter(mAdapter);
//		mAdapter.enableSwipeItem();
		mAdapter.setOnItemClickListener(this);
		mAdapter.setOnItemChildClickListener(this);
//		mAdapter.setOnItemSwipeListener(this);

	}

	private void initOrmData() {
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			menuDAO.create(new MenuDTO("美式咖啡", "Cafe Americano", "18"));
			menuDAO.create(new MenuDTO("拿铁咖啡", "Cafe Latte", "22"));
			menuDAO.create(new MenuDTO("卡布奇诺", "Cappuccino", "22"));
			menuDAO.create(new MenuDTO("平白", "Falt White", "20"));
			menuDAO.create(new MenuDTO("风味拿铁", "Flavored Latte", "24"));
			menuDAO.create(new MenuDTO("焦糖玛奇朵", "Caramel Macchiato", "25"));
			menuDAO.create(new MenuDTO("摩卡咖啡", "Cafe Mocha", "27"));
			menuDAO.create(new MenuDTO("青柠冰咖", "Lime Ice Coffee", "24"));
			menuDAO.create(new MenuDTO("鸳鸯咖啡", "Black Tea Coffee", "24"));

			menuDAO.create(new MenuDTO("手冲", "Pour Over", "25"));
			menuDAO.create(new MenuDTO("冰滴冷萃", "Ice Drip Coffee", "30"));

			menuDAO.create(new MenuDTO("牛奶", "Milk", "18"));
			menuDAO.create(new MenuDTO("巧克力", "Chocolates", "22"));
			menuDAO.create(new MenuDTO("抹茶拿铁", "Matcha Latte", "22"));
			menuDAO.create(new MenuDTO("红丝绒拿铁", "Red velvet Latte", "22"));
			menuDAO.create(new MenuDTO("锡兰奶茶", "Original Milk Tea", "18"));

			menuDAO.create(new MenuDTO("巧克力沙冰", "Chocolate Ice", "22"));
			menuDAO.create(new MenuDTO("抹茶沙冰", "Matcha Ice", "22"));
			menuDAO.create(new MenuDTO("红丝绒沙冰", "Red velvet Ice", "22"));
			menuDAO.create(new MenuDTO("摩卡沙冰", "Mocha Ice", "25"));
			menuDAO.create(new MenuDTO("风味咖啡沙冰", "Flavored Coffee Ice", "25"));

			menuDAO.create(new MenuDTO("华夫饼", "Waffle", "18"));

			initData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initData() {
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			List<MenuDTO> menuDTOList  = menuDAO.queryForAll();
			if (menuDTOList.size() > 0){
				for (MenuDTO menuDTO : menuDTOList){
					Log.e("menuDTO",menuDTO.toString());
					mAdapter.addData(menuDTO);
				}
			}else {
				initOrmData();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@OnClick({R.id.iv_back, R.id.tv_righttext,R.id.ll_total,R.id.tv_clean})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				break;
			case R.id.tv_righttext:
				addItems();
				break;
			case R.id.ll_total:
				startActivity(new Intent(getActivity(), TotalActivity.class));
				break;
			case R.id.tv_clean:
				addCustomerData();
				SPUtils.getInstance().put("OneBillMoney",0);
				updateOneBillMoney();

				break;
		}
	}

	private void addCustomerData() {
		try {
			Dao<CustomerDTO,Integer> customerDao = getCustomerDao();
			customerDao.create(new CustomerDTO(TimeUtils.getNowString(),SPUtils.getInstance().getInt("OneBillMoney")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addItems() {
		startActivity(new Intent(getActivity(), AddItemActivity.class).putExtra("type","add"));
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//		MenuDTO dto = ((MenuDTO) adapter.getData().get(position));
//		showDialog(dto);
	}

	private void showDialog(final MenuDTO dto) {
		new QMUIDialog.MessageDialogBuilder(getActivity())
				.setMessage("点击确定将\t"+dto.getName()+"\t添加至今日账单")
				.addAction("取消", new QMUIDialogAction.ActionListener() {
					@Override
					public void onClick(QMUIDialog dialog, int index) {
						dialog.dismiss();
					}
				})
				.addAction("确定", new QMUIDialogAction.ActionListener() {
					@Override
					public void onClick(QMUIDialog dialog, int index) {
						Toast.makeText(getActivity(), dto.getName() +"\t已添加成功", Toast.LENGTH_SHORT).show();
						addCups(dto.getName());
						addPrice(dto.getPrice());
						dialog.dismiss();
					}
				})
				.show();
	}

	private void addPrice(String price) {
		//今日总账单
		totalMoney = totalMoney + Integer.valueOf(price) ;
		SPUtils.getInstance().put("totalMoney",totalMoney);
		//单个客户账单
		OneBillMoney = OneBillMoney + Integer.valueOf(price);
		SPUtils.getInstance().put("OneBillMoney",OneBillMoney);

		mTvTotal.setText("¥"+String.valueOf(OneBillMoney));
	}

	private void addCups(String name) {
		if (SPUtils.getInstance().getInt(name) > 0) {
			int num = SPUtils.getInstance().getInt(name) + 1;
			SPUtils.getInstance().put(name, num);
		} else {
			SPUtils.getInstance().put(name, 1);
		}
	}

	private  Dao<MenuDTO, Integer> mMenuDTODao;
	/**
	 * 获得MenuDTO
	 * @throws SQLException
	 */
	public Dao<MenuDTO,Integer> getMenuDAO() throws SQLException {

		if (mMenuDTODao == null)
		{
			mMenuDTODao = DataBaseHelper.getInstance(getActivity()).getDao(MenuDTO.class);
		}
		return mMenuDTODao;
	}

	private  Dao<CustomerDTO, Integer> mCustomerDao;
	/**
	 * 获取CustomerDTO
	 * @throws SQLException
	 */
	public Dao<CustomerDTO,Integer> getCustomerDao() throws SQLException {

		if (mCustomerDao == null)
		{
			mCustomerDao = CustomerHelper.getInstance(getActivity()).getDao(CustomerDTO.class);
		}
		return mCustomerDao;
	}

	@Override
	public void onResume() {
		super.onResume();
		totalMoney = SPUtils.getInstance().getInt("totalMoney");
		if (totalMoney < 0 ){
			totalMoney = 0;
		}else {
			totalMoney = SPUtils.getInstance().getInt("totalMoney");
		}
		mTvTotal.setText("¥"+String.valueOf(totalMoney));

		updateOneBillMoney();


		mAdapter.setNewData(null);
		initData();
	}

	private void updateOneBillMoney(){
		OneBillMoney = SPUtils.getInstance().getInt("OneBillMoney");
		if (OneBillMoney < 0 ){
			OneBillMoney = 0;
		}else {
			OneBillMoney = SPUtils.getInstance().getInt("OneBillMoney");
		}
		mTvTotal.setText("¥"+String.valueOf(OneBillMoney));
	}

	@Override
	public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
		final EasySwipeMenuLayout easySwipeMenuLayout = (EasySwipeMenuLayout)  adapter.getViewByPosition(mRecycleView,position,R.id.item_easy);
		switch (view.getId()){
			case R.id.item_right:
				Toast.makeText(getActivity(),"修改",Toast.LENGTH_SHORT).show();
				easySwipeMenuLayout.resetStatus();
				Log.e("change_position:",String.valueOf(position));
				change(position+1);
				break;
			case R.id.item_left:
				Toast.makeText(getActivity(),"删除",Toast.LENGTH_SHORT).show();
				easySwipeMenuLayout.resetStatus();
				delete(position+1);
				break;
			case R.id.item_content:
				MenuDTO dto = ((MenuDTO) adapter.getData().get(position));
				showDialog(dto);
				break;
		}
	}

	/**
	 * 删除
	 * @param id
	 */

	private void delete(int id) {
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			menuDAO.queryForId(id);
			Log.e("change_id:",String.valueOf(menuDAO.queryForId(id).getId()));
			Log.e("change_name:",menuDAO.queryForId(id).getName());
			Log.e("change_english:",menuDAO.queryForId(id).getEnglish());
			Log.e("change_price:",menuDAO.queryForId(id).getPrice());
			menuDAO.deleteById(id);
			mAdapter.setNewData(null);
			initData();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改
	 * @param id
	 */

	private void change(int id){
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			menuDAO.queryForId(id);
			Log.e("change_id:",String.valueOf(menuDAO.queryForId(id).getId()));
			Log.e("change_name:",menuDAO.queryForId(id).getName());
			Log.e("change_english:",menuDAO.queryForId(id).getEnglish());
			Log.e("change_price:",menuDAO.queryForId(id).getPrice());
			startActivity(new Intent(getActivity(), AddItemActivity.class)
					.putExtra("type","change")
					.putExtra("change_id",id)
					.putExtra("oldName",menuDAO.queryForId(id).getName()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
