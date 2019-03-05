package com.sh.justcoffee.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.BillDTO;
import com.sh.justcoffee.entity.MenuDTO;

import java.util.List;

/**
 * author： TongGuHermit
 * created on： 2019/3/5 11:01
 */

public class BillAdapter extends BaseQuickAdapter<BillDTO,BaseViewHolder> {
	public BillAdapter(@Nullable List<BillDTO> data) {
		super(R.layout.item_bill, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, BillDTO item) {
		helper.setText(R.id.item_bill_name,item.getName())
				.setText(R.id.item_bill_num,String.valueOf(item.getNum()));
	}
}
