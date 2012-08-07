package edu.iitdu.jdgen.builder.abstraction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Mohayeminul Islam
 */
public interface ConstrainableBuilder<T> extends Buildable<T> {
	public ConstrainableBuilder<T> with(Method method);

	public ConstrainableBuilder<T> with(String methodName,
		Class<?>... parameterTypes) throws NoSuchMethodException,
		SecurityException;

	public ConstrainableBuilder<T> withConstructor(Constructor<T> constructor);

	public ConstrainableBuilder<T> withConstructor(Class<?>... parameterTypes)
		throws NoSuchMethodException, SecurityException;

	// IObjectBuilder<T> Do(Action<T> action);
	// IObjectBuilder<T> DoMultiple<TAction>(Action<T, TAction> action,
	// IList<TAction> list);
	// IObjectBuilder<T> WithPropertyNamer(IPropertyNamer propertyNamer);
	// void CallFunctions(T obj);
	//
	
	//
	// T Name(T obj);
}
