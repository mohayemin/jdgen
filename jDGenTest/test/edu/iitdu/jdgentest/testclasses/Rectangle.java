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
	}

	public Rectangle(Integer widthAndHeightOfSquareRect) {
		this.width = widthAndHeightOfSquareRect;
		this.height = widthAndHeightOfSquareRect;
	}

	public Rectangle(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}

	public void resize(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}

	public void scale(Double ratio) {
		this.width = (int) (width * ratio);
		this.height = (int) (height * ratio);
	}

	public void setWidth(Integer width) {
		this.width = width;
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

	public boolean equalToRectangle(Rectangle rect) {
		return rect.height == height && rect.width == width;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			return equalToRectangle((Rectangle) obj);
		} else {
			return super.equals(obj);
		}
	}
	
	@Override
	public String toString() {
		return width + " by " + height;
	}
}
