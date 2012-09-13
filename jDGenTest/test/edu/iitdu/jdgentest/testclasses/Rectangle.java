package edu.iitdu.jdgentest.testclasses;

/**
 * No methods in this class call other methods of this class.
 * This is intentional. This is done to easily trace method calls.
 * 
 * @author Mohayeminul Islam
 */
public class Rectangle {
	private Integer width;
	private Integer height;

	public Rectangle() {
		this.width = 0;
		this.height = 0;
		System.out.println("Rectangle()");
	}

	public Rectangle(Integer widthAndHeightOfSquareRect) {
		this.width = widthAndHeightOfSquareRect;
		this.height = widthAndHeightOfSquareRect;
		System.out.println("Rectangle(Integer)");
	}

	public Rectangle(Integer width, Integer height) {
		this.width = width;
		this.height = height;
		System.out.println("Rectangle(Integer, Integer)");
	}

	public void resize(Integer width, Integer height) {
		this.width = width;
		this.height = height;
		System.out.println("Resize");
	}

	public void scale(Double ratio) {
		this.width = (int) (width * ratio);
		this.height = (int) (height * ratio);
		System.out.println("scale");
	}

	public void setWidth(Integer width) {
		this.width = width;
		System.out.println("setWidth");
	}

	public void setHeight(Integer height) {
		this.height = height;
		System.out.println("setHeight");
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}
}
