package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;

public abstract class AbstractConstrainableBuilder<T> implements
	ConstrainableBuilder<T> {

	private Class<T> type;
	protected Constructor<T> constructor;
	protected List<Method> methods;

	public AbstractConstrainableBuilder(Class<T> type) {
		this.type = type;
		try {
			constructor = type.getConstructor();
		} catch (Exception e) {
		}

		methods = new ArrayList<Method>();
	}

	@Override
	public ConstrainableBuilder<T> withConstructor(Constructor<T> constructor) {
		this.constructor = constructor;
		return this;
	}

	@Override
	public ConstrainableBuilder<T> with(Method method) {
		methods.add(method);
		return this;
	}

	@Override
	public ConstrainableBuilder<T> with(String methodName,
		Class<?>... parameterTypes) {
		try {
			Method method = type.getMethod(methodName, parameterTypes);

			return with(method);
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		} catch (SecurityException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	@Override
	public ConstrainableBuilder<T> withConstructor(Class<?>... parameterTypes) {
		try {
			Constructor<T> constructor = type.getConstructor(parameterTypes);
			return withConstructor(constructor);
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		} catch (SecurityException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected abstract void callMethods(T obj) throws IllegalAccessException,
		IllegalArgumentException, InvocationTargetException;

	protected abstract T construct() throws InstantiationException,
		IllegalAccessException, IllegalArgumentException,
		InvocationTargetException;
}
