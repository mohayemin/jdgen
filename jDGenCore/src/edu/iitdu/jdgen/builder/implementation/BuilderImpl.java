package edu.iitdu.jdgen.builder.implementation;

public class BuilderImpl<T> {
	private BuilderConstraintsImpl<T> constraints;
	public BuilderImpl(BuilderConstraintsImpl<T> constraints) {
		this.constraints = constraints;
	}
	
	public T build(){
		return null;
	}
}
