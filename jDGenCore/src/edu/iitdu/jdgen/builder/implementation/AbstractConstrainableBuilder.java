package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgen.util.MethodUtils;

public abstract class AbstractConstrainableBuilder<T> implements
	ConstrainableBuilder<T> {

	private Class<T> type;
	protected ConstructorInvoker<T> constructor;
	protected List<MethodInvoker<T>> methods;
	protected List<MethodInvoker<T>> setters;

	public AbstractConstrainableBuilder(Class<T> type) {
		this.type = type;
		try {
			constructor = new ConstructorInvoker<T>(type.getConstructor());
		} catch (Exception e) {
			constructor = null;
		}

		methods = new LinkedList<>();
		setters = new LinkedList<>();
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
	public ConstrainableBuilder<T> construct(
		ConstructorInvoker<T> constructor) {
		this.constructor = constructor;
		return this;
	}

	@Override
	public ConstrainableBuilder<T> construct(Object... arguments)
		throws JDGenRuntimeException {
		try {
			ConstructorInvoker<T> constructor =
				new ConstructorInvoker<T>(type, arguments);
			return construct(constructor);
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}
	
	@Override
	public <U> ConstrainableBuilder<T> set(String setterVariableName, U value) {
		try {
			Method setter = MethodUtils.findSetter(type, setterVariableName);
			MethodInvoker<T> setterInvoker = new MethodInvoker<>(setter, value);
			setters.add(setterInvoker);
			return this;
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
		
	}

	protected abstract void callMethods(T object);

	protected abstract T construct();
	
	protected abstract void callSetters(T object);
	
}
