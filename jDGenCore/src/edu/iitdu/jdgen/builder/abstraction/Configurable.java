package edu.iitdu.jdgen.builder.abstraction;

import java.util.List;

import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;

/**
 * @author Mohayeminul Islam
 */
public interface Configurable<T>  {
	public Configurable<T> execute(String methodName,
		Object... arguments);
	public Configurable<T> construct(Object... arguments);
	public <U> Configurable<T> set(String setterName, U value);
	
	public List<MethodInvoker<T>> getMethods();
	public ConstructorInvoker<T> getConstructor();
	public List<MethodInvoker<T>> getSetters();
}
