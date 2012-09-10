package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;

public abstract class AbstractConstrainableBuilder<T> implements
	ConstrainableBuilder<T> {

	private Class<T> type;
	protected ConstructorInvoker<T> constructor;
	protected List<MethodInvoker<T>> methods;

	public AbstractConstrainableBuilder(Class<T> type) {
		this.type = type;
		try {
			constructor = new ConstructorInvoker<T>(type.getConstructor());
		} catch (Exception e) {
			constructor = null;
		}

		methods = new ArrayList<MethodInvoker<T>>();
	}

	@Override
	public ConstrainableBuilder<T> with(MethodInvoker<T> method) {
		methods.add(method);

		return this;
	}

	@Override
	public ConstrainableBuilder<T> with(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		try {
			MethodInvoker<T> invoker =
				new MethodInvoker<T>(type, methodName, arguments);
			return with(invoker);
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		} catch (SecurityException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	@Override
	public ConstrainableBuilder<T> withConstructor(
		ConstructorInvoker<T> constructor) {
		this.constructor = constructor;
		return this;
	}

	@Override
	public ConstrainableBuilder<T> withConstructor(Object... arguments)
		throws JDGenRuntimeException {
		try {
			ConstructorInvoker<T> constructor =
				new ConstructorInvoker<T>(type, arguments);
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
