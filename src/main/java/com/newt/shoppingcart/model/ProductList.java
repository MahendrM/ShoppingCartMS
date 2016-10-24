package com.newt.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newt.shoppingcart.commonutils.Productstatus;

public class ProductList {

	private int productcheckoutId;

	private int productId;

	private String productName;

	private long customerId;

	private int checkoutOrderId;

	private Productstatus productStatus;

	private int shoppingcartId;

	public int getProductcheckoutId() {
		return productcheckoutId;
	}

	public void setProductcheckoutId(int productcheckoutId) {
		this.productcheckoutId = productcheckoutId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Productstatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Productstatus productStatus) {
		this.productStatus = productStatus;
	}

	public int getCheckoutOrderId() {
		return checkoutOrderId;
	}

	public void setCheckoutOrderId(int checkoutOrderId) {
		this.checkoutOrderId = checkoutOrderId;
	}

	public int getShoppingcartId() {
		return shoppingcartId;
	}

	public void setShoppingcartId(int shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}

	@Override
	public String toString() {
		return "ProductList [productcheckoutId=" + productcheckoutId + ", productId=" + productId + ", productName="
				+ productName + ", customerId=" + customerId + ", checkoutOrderId=" + checkoutOrderId
				+ ", productStatus=" + productStatus + ", shoppingcartId=" + shoppingcartId + "]";
	}
}