package edu.iitdu.jdgen.builder.abstraction;

/**
 * @author Mohayeminul Islam
 */
public interface ConstrainableBuilder<T> extends Buildable<T> {
	
	public ConstrainableBuilder<T> execute(String methodName,
		Object... arguments);
	public ConstrainableBuilder<T> construct(Object... arguments);
	public <U> ConstrainableBuilder<T> set(String setterName, U value);
}
