package edu.iitdu.jdgentest.testclasses;

public class Product {
	private int productId;
	private boolean sold;

	public void sell() {
		sold = true;
	}

	public boolean isSold() {
		return sold;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
