package com.sh.justcoffee.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.sh.justcoffee.R;
import com.sh.justcoffee.utils.TimeUtil;
import com.sh.justcoffee.widget.ViewPagerFix;
import com.sh.justcoffee.view.fragment.BillFragment;
import com.sh.justcoffee.view.fragment.MenuFragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

	@BindView(R.id.viewPage)
	ViewPagerFix mViewPage;
	@BindView(R.id.bottomNavigationBar)
	BottomNavigationBar mBottomNavigationBar;
	private BillFragment mBillFragment;
	private MenuFragment mMenuFragment;
	private List<Fragment> mFragments = new ArrayList<>();
	private long exitTime = 0;
	private String name;
	private String englishName;
	private String price;


	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}

	@Override
	protected void initViews() {
		setSwipeBackEnable(false);
		BarUtils.setStatusBarAlpha(this, 0);
		mBillFragment = new BillFragment();
		mMenuFragment = new MenuFragment();
		mFragments.add(mMenuFragment);
		mFragments.add(mBillFragment);
		initNavigateBottom();
	}




	private void initNavigateBottom() {
		mViewPage.setScrollable(false);
		mViewPage.setOffscreenPageLimit(mFragments.size());
		mViewPage.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
		mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
				.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
				.setActiveColor(R.color.base)
				.setInActiveColor(R.color.color_dbdbdb)
				.setBarBackgroundColor(R.color.color_ffffff)
				.addItem(new BottomNavigationItem(R.drawable.icon_coffee_s, "咖啡").setInactiveIconResource(R.drawable.icon_coffee_n))
				.addItem(new BottomNavigationItem(R.drawable.icon_bill_s, "账单").setInactiveIconResource(R.drawable.icon_bill_n))
				.setFirstSelectedPosition(0)
				.initialise();
		mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
			@Override
			public void onTabSelected(int position) {
				mViewPage.setCurrentItem(position);
			}

			@Override
			public void onTabUnselected(int position) {

			}

			@Override
			public void onTabReselected(int position) {

			}
		});
	}

	/**
	 * adapter
	 */
	private class MainPagerAdapter extends FragmentPagerAdapter {

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}
	}

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(this, R.string.click_again_exit, Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	/*	try {
			if (TimeUtil.IsToday("2019-03-08")){
				Toast.makeText(MainActivity.this,"还是今天",Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(MainActivity.this,"新的一天",Toast.LENGTH_SHORT).show();
				SPUtils.getInstance().clear();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
	}
}
