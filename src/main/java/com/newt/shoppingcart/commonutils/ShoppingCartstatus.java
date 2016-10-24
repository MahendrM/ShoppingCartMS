package com.newt.shoppingcart.commonutils;

public enum ShoppingCartstatus {

	ACTIVE("Active"), PROCESSING("Processing");

	private final String text;

	private ShoppingCartstatus(final String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}
}
