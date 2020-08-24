package com.wellsfargo.cpk.model;

public class KitDetail {

	private int id;
	private double cost;
	private int productId;
	private int quantity;
	private double amount;
	
	public KitDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public KitDetail(int id, double cost, int productId, int quantity, double amount) {
		super();
		this.id = id;
		this.cost = cost;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
