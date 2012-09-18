package edu.iitdu.jdgen.builder.abstraction;

/**
 * @author Mohayeminul Islam
 */
public interface Constrainable<T>  {
	public Constrainable<T> execute(String methodName,
		Object... arguments);
	public Constrainable<T> construct(Object... arguments);
	public <U> Constrainable<T> set(String setterName, U value);
}
