package com.sh.justcoffee.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * author： TongGuHermit
 * created on： 2019/3/8 10:46
 */
@DatabaseTable(tableName="tb_customer")
public class CustomerDTO {
	@DatabaseField(columnName="totalMoney")
	private int totalMoney;
	@DatabaseField(columnName="time")
	private String time;
	@DatabaseField(generatedId=true)
	private int id;

	public CustomerDTO(){

	}
	public CustomerDTO(String time,int totalMoney){
		setTime(time);
		setTotalMoney(totalMoney);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

}
