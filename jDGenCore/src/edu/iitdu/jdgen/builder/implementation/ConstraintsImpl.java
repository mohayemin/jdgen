package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.Constrainable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.MethodInvoker;
import edu.iitdu.jdgen.util.MethodUtils;

public class ConstraintsImpl<T> implements Constrainable<T>{
	private ConstructorInvoker<T> constructor;
	private List<MethodInvoker<T>> methods;
	private List<MethodInvoker<T>> setters;

	private Class<T> type;

	public ConstraintsImpl(Class<T> type) {
		this.type = type;

		methods = new LinkedList<>();
		setters = new LinkedList<>();
	}

	@Override
	public Constrainable<T> construct(Object... arguments) {

		try {
			ConstructorInvoker<T> constructor =
				new ConstructorInvoker<T>(type, arguments);
			this.constructor = constructor;
			
			return this;
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	public Constrainable<T> execute(String methodName, Object... arguments) {
		try {
			MethodInvoker<T> invoker =
				new MethodInvoker<>(type, methodName, arguments);

			methods.add(invoker);
			return this;
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	public <U> Constrainable<T> set(String setterName, U value) {

		try {
			Method setter = MethodUtils.findSetter(type, setterName);
			MethodInvoker<T> setterInvoker = new MethodInvoker<>(setter, value);
			setters.add(setterInvoker);
			
			return this;
		} catch (NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	@Override
	public List<MethodInvoker<T>> getMethods() {
		return methods;
	}

	public ConstructorInvoker<T> getConstructor() {
		if (constructor == null) {
			try {
				constructor = new ConstructorInvoker<T>(type.getConstructor());
			} catch (NoSuchMethodException e) {
				throw new JDGenRuntimeException(e);
			}
		}
		
		return constructor;
	}

	public List<MethodInvoker<T>> getSetters() {
		return setters;
	}

	Class<T> getType() {
		return type;
	}
}
