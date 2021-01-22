package com.ssafy.hw0122;

public class Product {
	private int pNum;
	private String name;
	private int price;
	private int quan;
	private String desc;

//	private static Product instance;
//
//	public static Product getInstance() {
//		if (instance == null) {
//			instance = new Product();
//		}
//		return instance;
//	}
//
//	public static Product getInstance(int pNum, String name, int price, int quan, String desc) {
//		if (instance == null) {
//			instance = new Product(pNum, name, price, quan, desc);
//		}
//		return instance;
//	}

	public Product() {
		this.pNum = 0;
		this.name = "no name";
		this.price = 0;
		this.quan = 0;
		this.desc = "no desc";
	}

	public Product(int pNum, String name, int price, int quan, String desc) {
		this.pNum = pNum;
		this.name = name;
		this.price = price;
		this.quan = quan;
		this.desc = desc;
	}

	public String toString() {
		return (this.pNum + "\t| " + this.name + "\t| " + this.price + "\t| " + this.quan + "\t| " + this.desc + "\n");
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
