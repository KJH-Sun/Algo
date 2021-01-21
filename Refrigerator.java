package com.ssafy.hw;

public class Refrigerator {
	private int num;
	private String name;
	private int price;
	private int quan;
	private int vol;
	
	
	public Refrigerator() {
		this.num = 0;
		this.name = "no name";
		this.price = 0;
		this.quan = 0;
		this.vol = 0;
		
	}
	public Refrigerator(int num,String name,int price,int quan,int vol) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.quan = quan;
		this.vol = vol;

	}
	
	public String toString() {
		return (this.num + " | " + this.name + " | " + this.price + " | " + this.quan + " | " + this.vol);
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
	public int getVol() {
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
	}

}
