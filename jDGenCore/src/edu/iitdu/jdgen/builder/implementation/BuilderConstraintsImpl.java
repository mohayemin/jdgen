package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgen.util.MethodUtils;

public class BuilderConstraintsImpl<T> {
	private ConstructorInvoker<T> constructor;
	private List<MethodInvoker<T>> methods;
	private List<MethodInvoker<T>> setters;

	private Class<T> type;

	public BuilderConstraintsImpl(Class<T> type) {
		this.type = type;

		methods = new LinkedList<>();
		setters = new LinkedList<>();
	}

	public void construct(Object... arguments) {

		try {
			ConstructorInvoker<T> constructor =
				new ConstructorInvoker<T>(type, arguments);
			this.constructor = constructor;
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	public void execute(String methodName, Object... arguments) {
		try {
			MethodInvoker<T> invoker =
				new MethodInvoker<T>(type, methodName, arguments);

			methods.add(invoker);
		} catch (NoSuchMethodException e) {
			new JDGenRuntimeException(e);
		}

	}

	public <U> void set(String setterName, U value) {

		try {
			Method setter = MethodUtils.findSetter(type, setterName);
			MethodInvoker<T> setterInvoker = new MethodInvoker<>(setter, value);
			setters.add(setterInvoker);
		} catch (NoSuchMethodException e) {
			new JDGenRuntimeException(e);
		}
	}

	List<MethodInvoker<T>> getMethods() {
		return methods;
	}

	ConstructorInvoker<T> getConstructor() throws NoSuchMethodException {
		if (constructor == null) {
			constructor = new ConstructorInvoker<T>(type.getConstructor());
		}
		return constructor;
	}

	List<MethodInvoker<T>> getSetters() {
		return setters;
	}

	Class<T> getType() {
		return type;
	}
}
