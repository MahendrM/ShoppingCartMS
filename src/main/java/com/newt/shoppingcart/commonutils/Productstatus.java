package com.newt.shoppingcart.commonutils;

public enum Productstatus {

	PENDING("Pending"), PROCESSING("Processing"), CANCELLED("Cancelled"), SHIPPED("Shipped"), DELIVERED("Delivered");

	private final String text;

	private Productstatus(final String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}
}
