package edu.iitdu.jdgen.builder.abstraction;

import java.util.List;

import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;

/**
 * @author Mohayeminul Islam
 */
public interface Constrainable<T>  {
	public Constrainable<T> execute(String methodName,
		Object... arguments);
	public Constrainable<T> construct(Object... arguments);
	public <U> Constrainable<T> set(String setterName, U value);
	
	public List<MethodInvoker<T>> getMethods();
	public ConstructorInvoker<T> getConstructor();
	public List<MethodInvoker<T>> getSetters();
}
