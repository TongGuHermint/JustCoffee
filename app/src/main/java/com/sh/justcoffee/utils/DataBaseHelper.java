package com.sh.justcoffee.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sh.justcoffee.entity.MenuDTO;

import java.sql.SQLException;

/**
 * author： TongGuHermit
 * created on： 2019/3/6 17:10
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper{
	private MenuDTO mMenuDTO;
	private static DataBaseHelper mDataBaseHelper = null;

	private DataBaseHelper(Context context) {
		super(context, "tb_menu", null, 1);
	}

	public static DataBaseHelper getInstance(Context context){
		if (mDataBaseHelper == null){
			mDataBaseHelper = new DataBaseHelper(context);
		}
		return mDataBaseHelper;
	}


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, MenuDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
		try {
			TableUtils.dropTable(connectionSource, MenuDTO.class, true);
			onCreate(sqLiteDatabase, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		mMenuDTO = null;
	}
}
