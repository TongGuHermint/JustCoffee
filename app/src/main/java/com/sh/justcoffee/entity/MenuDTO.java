package com.sh.justcoffee.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * author： TongGuHermit
 * created on： 2019/3/5 11:02
 */
@DatabaseTable(tableName="tb_menu")  //声明表名

public class MenuDTO {

	public MenuDTO(){
	}

	public MenuDTO(String name,String english,String price){
		setName(name);
		setEnglish(english);
		setPrice(price);
	}

	public MenuDTO(String name,String english,String price,int id){
		setName(name);
		setEnglish(english);
		setPrice(price);
		setId(id);
	}

	@DatabaseField(columnName="name")
	private String name;
	@DatabaseField(columnName="english")
	private String english;
	@DatabaseField(columnName="price")  //属性名
	private String price;
	@DatabaseField(generatedId=true)  //表示id为主键且自动生成
	private int id;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
