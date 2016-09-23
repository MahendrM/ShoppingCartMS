package com.newt.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;

@Entity
public class OrdersItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderItemId;

	private int orderId;

	private String productTypeId;

	private String productId;

	private int quantity;

	private float price;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrdersItems [orderItemId=" + orderItemId + ", orderId=" + orderId + ", productTypeId=" + productTypeId
				+ ", productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
