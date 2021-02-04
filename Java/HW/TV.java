package com.ssafy.hw;

public class TV {

	private int num;
	private String name;
	private int price;
	private int quan;
	private int inch;
	private String type;
	
	
	
	public TV() {
		this.num = 0;
		this.name = "no name";
		this.price = 0;
		this.quan = 0;
		this.inch = 0;
		this.type = "no type";
	}
	public TV(int num,String name,int price,int quan,int inch,String type) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.quan = quan;
		this.inch = inch;
		this.type = type;
	}
	
	public String toString() {
		return (this.num + " | " + this.name + " | " + this.price + " | " + this.quan + " | " + this.inch + " | " + this.type);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public int getQuan() {
		return quan;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	


}
