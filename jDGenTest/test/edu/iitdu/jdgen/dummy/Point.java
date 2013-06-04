package edu.iitdu.jdgen.dummy;

public class Point {
	private Integer x, y;

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return p.x == x && p.y == y;
		}

		return super.equals(obj);
	}
}
