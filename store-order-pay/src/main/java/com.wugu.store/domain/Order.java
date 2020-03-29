package com.wugu.store.domain;


public class Order {
	private String Id;
	private String name;
	private String commodity;
	private float price;
	private int number;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommaodity() {
		return commodity;
	}
	public void setCommaodity(String commodity) {
		this.commodity = commodity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getnumber() {
		return number;
	}
	public void setSum(int number) {
		this.number = number;
	}
	
	
}
