package com.sh.justcoffee.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sh.justcoffee.R;
import com.sh.justcoffee.entity.CustomerDTO;

import java.util.List;

/**
 * author： TongGuHermit
 * created on： 2019/3/8 11:12
 */

public class CustomerAdapter extends BaseQuickAdapter<CustomerDTO,BaseViewHolder> {
	public CustomerAdapter( @Nullable List<CustomerDTO> data) {
		super(R.layout.item_customer, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CustomerDTO item) {
		helper.setText(R.id.tv_c_id,"NO."+String.valueOf(item.getId()))
				.setText(R.id.tv_c_time,item.getTime())
				.setText(R.id.tv_c_total,"¥"+item.getTotalMoney());
	}
}
