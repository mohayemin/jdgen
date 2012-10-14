package edu.iitdu.jdgen.testclasses;

public class IntegerValue {
	private int value;

	public IntegerValue() {
		this(0);
	}
	
	public IntegerValue(int value) {
		this.value = value;
	}

	public void increase() {
		value++;
	}

	public int getValue() {
		return value;
	}
	
	public void reset(){
		value = 0;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
