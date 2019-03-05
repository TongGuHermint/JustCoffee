package com.sh.justcoffee.entity;

/**
 * author： TongGuHermit
 * created on： 2019/3/5 11:02
 */

public class MenuDTO {
	public MenuDTO(String name,String english,String price){
		setName(name);
		setEnglish(english);
		setPrice(price);
	}
	private String name;
	private String english;
	private String price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
