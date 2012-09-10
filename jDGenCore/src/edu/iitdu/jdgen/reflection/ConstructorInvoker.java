package edu.iitdu.jdgen.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.util.ReflectionUtils;

public class ConstructorInvoker<T> implements Invocable<T> {
	private Constructor<T> constructor;
	private Object[] arguments;

	public ConstructorInvoker(Class<T> type, Object... arguments)
		throws NoSuchMethodException, SecurityException {
		this(type.getConstructor(ReflectionUtils.getTypes(arguments)),
			arguments);
	}

	public ConstructorInvoker(Constructor<T> constructor, Object... arguments) {
		this.constructor = constructor;
		this.arguments = arguments;
	}

	public T invoke() throws InstantiationException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException {
		return constructor.newInstance(arguments);
	}
}
