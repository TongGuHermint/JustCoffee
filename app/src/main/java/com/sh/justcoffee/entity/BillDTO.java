package com.sh.justcoffee.entity;

/**
 * author： TongGuHermit
 * created on： 2019/3/5 14:00
 */

public class BillDTO {
	private String name;
	private int num;
	public BillDTO(String name,int num){
		setName(name);
		setNum(num);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
