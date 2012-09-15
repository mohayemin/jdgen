package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgen.util.MethodUtils;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements ConstrainableBuilder<T> {

	private Class<T> type;
	protected ConstructorInvoker<T> constructor;
	protected List<MethodInvoker<T>> methods;
	protected List<MethodInvoker<T>> setters;

	public ObjectBuilder(Class<T> type) {
		this.type = type;
		methods = new LinkedList<>();
		setters = new LinkedList<>();
	}

	protected ConstrainableBuilder<T> execute(MethodInvoker<T> method) {
		methods.add(method);
		return this;
	}

	@Override
	public ConstrainableBuilder<T> execute(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		try {
			MethodInvoker<T> invoker =
				new MethodInvoker<T>(type, methodName, arguments);
			return execute(invoker);
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected ConstrainableBuilder<T> construct(
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

	/**
	 * While building, first the object is instantiated.
	 * Then the setters are called.
	 * And then other methods are called.
	 */
	@Override
	public T build() {
		T object = construct();
		callSetters(object);
		callMethods(object);
		return object;
	}

	protected void callMethods(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> method : methods) {
				method.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected T construct() throws JDGenRuntimeException {
		try {
			if (constructor == null) {
				constructor = new ConstructorInvoker<T>(type.getConstructor());
			}
			T object = constructor.invoke();

			return object;
		} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected void callSetters(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> setter : setters) {
				setter.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}
}
