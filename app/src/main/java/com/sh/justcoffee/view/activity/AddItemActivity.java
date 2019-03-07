package com.sh.justcoffee.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.j256.ormlite.dao.Dao;
import com.sh.justcoffee.R;
import com.sh.justcoffee.utils.DataBaseHelper;
import com.sh.justcoffee.entity.MenuDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddItemActivity extends BaseActivity {


	@BindView(R.id.et_add_name)
	EditText mEtAddName;
	@BindView(R.id.et_add_english)
	EditText mEtAddEnglish;
	@BindView(R.id.et_add_price)
	EditText mEtAddPrice;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.tv_righttext)
	TextView mTvRighttext;
	@BindView(R.id.btn)
	Button mBtn;
	@BindView(R.id.iv_back)
	LinearLayout mIvBack;

	private String name;
	private String englishName;
	private String price;
	private int change_id;
	private String type;
	private String oldName;

	@Override
	protected int getContentView() {
		return R.layout.activity_add_item;
	}

	@Override
	protected void initViews() {
		BarUtils.setStatusBarAlpha(this, 0);
		mIvBack.setVisibility(View.VISIBLE);
		type = getIntent().getStringExtra("type");
		if (type.equals("change")){
			mBtn.setText("确定");
			mTvTitle.setText("修改");
			oldName = getIntent().getStringExtra("oldName");
		}else if(type.equals("add")){
			mBtn.setText("确定");
			mTvTitle.setText("添加");
		}
	}


	@OnClick({R.id.iv_back, R.id.tv_righttext, R.id.btn})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				finish();
				break;
			case R.id.tv_righttext:
				break;
			case R.id.btn:
				name = mEtAddName.getText().toString();
				englishName = mEtAddEnglish.getText().toString();
				price = mEtAddPrice.getText().toString();
				if (isNotNull()) {
					if (type.equals("change")){
						changeItem();
						finish();
					}else {
						addItem();
					}

				}
				break;
		}
	}

	private void changeItem() {

		ArrayList<MenuDTO> list = null;
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			list = (ArrayList<MenuDTO>) menuDAO.queryForEq("name", oldName);
			if (list != null) {
				for (MenuDTO menuDTO : list) {
					menuDTO.setName(name);
					menuDTO.setEnglish(englishName);
					menuDTO.setPrice(price);
					menuDAO.update(menuDTO);
//					menuDAO.createOrUpdate(menuDTO);//和上一行的方法效果一样
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Boolean isNotNull() {
		if (StringUtils.isEmpty(name)) {
			Toast.makeText(this, "请输入菜品名", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtils.isEmpty(englishName)) {
			Toast.makeText(this, "请输入英文名", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtils.isEmpty(price)) {
			Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private void addItem() {
		try {
			Dao<MenuDTO,Integer> menuDAO = getMenuDAO();
			menuDAO.create(new MenuDTO(name, englishName, price));
			finish();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Dao<MenuDTO, Integer> mMenuDTODao;
	/**
	 * 获得MenuDTO
	 * @throws SQLException
	 */
	public Dao<MenuDTO,Integer> getMenuDAO() throws SQLException {

		if (mMenuDTODao == null)
		{
			mMenuDTODao = DataBaseHelper.getInstance(this).getDao(MenuDTO.class);
		}
		return mMenuDTODao;
	}
}
