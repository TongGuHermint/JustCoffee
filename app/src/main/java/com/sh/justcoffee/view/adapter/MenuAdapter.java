package com.sh.justcoffee.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sh.justcoffee.entity.MenuDTO;
import com.sh.justcoffee.R;
import com.sh.justcoffee.view.fragment.MenuFragment;

import java.util.List;

/**
 * author： TongGuHermit
 * created on： 2019/3/5 11:01
 */

public class MenuAdapter extends BaseQuickAdapter<MenuDTO,BaseViewHolder> {
	public MenuAdapter( @Nullable List<MenuDTO> data) {
		super(R.layout.item_menu, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, MenuDTO item) {
		helper.setText(R.id.item_menu_name,item.getName())
				.setText(R.id.item_menu_english,item.getEnglish())
				.setText(R.id.item_menu_price,"¥"+item.getPrice())
		.addOnClickListener(R.id.item_right)
		.addOnClickListener(R.id.item_left)
		.addOnClickListener(R.id.item_content);
	}

	}
