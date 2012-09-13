package edu.iitdu.jdgen.builder.abstraction;

import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;

/**
 * @author Mohayeminul Islam
 */
public interface ConstrainableBuilder<T> extends Buildable<T> {
	
	public ConstrainableBuilder<T> execute(MethodInvoker<T> methodCall);
	
	public ConstrainableBuilder<T> execute(String methodName,
		Object... arguments);

	public ConstrainableBuilder<T> construct(ConstructorInvoker<T> constructorInvoker);
	
	public ConstrainableBuilder<T> construct(Object... arguments);
	
	public <U> ConstrainableBuilder<T> set(String setterName, U value);
}
