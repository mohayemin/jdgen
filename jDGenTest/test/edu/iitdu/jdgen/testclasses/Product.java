package edu.iitdu.jdgen.testclasses;

public class Product {
	private int productId;
	private boolean sold;

	public Product() {
		this(0);
	}
	
	public Product(int productId) {
		setProductId(productId);
	}
	
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
