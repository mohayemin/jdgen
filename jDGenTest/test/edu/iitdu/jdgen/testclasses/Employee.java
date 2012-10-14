package edu.iitdu.jdgen.testclasses;

public class Employee extends Person {
	private String department;

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setButNoParameter() {

	}

	public String getDepartment() {
		return department;
	}
}
