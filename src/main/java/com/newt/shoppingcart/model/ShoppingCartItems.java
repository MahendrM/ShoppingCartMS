package com.newt.shoppingcart.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shoppingcartitems")
public class ShoppingCartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shoppingcartitemsId;

	private int shoppingcartId;

	private String productName;
	
	private int customerId;
	
	private String status;

	private int productId;

	private float price;

	private String productDesc;

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getShoppingcartId() {
		return shoppingcartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getShoppingcartitemsId() {
		return shoppingcartitemsId;
	}

	public void setShoppingcartitemsId(int shoppingcartitemsId) {
		this.shoppingcartitemsId = shoppingcartitemsId;
	}

	@Override
	public String toString() {
		return "ShoppingCartItems [shoppingcartitemsId=" + shoppingcartitemsId + ", shoppingcartId=" + shoppingcartId
				+ ", productName=" + productName + ", customerId=" + customerId + ", status=" + status + ", productId="
				+ productId + ", price=" + price + ", productDesc=" + productDesc + "]";
	}

	public void setShoppingcartId(int shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}
}
