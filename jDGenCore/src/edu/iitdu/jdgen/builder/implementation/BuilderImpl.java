package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.Constrainable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.MethodInvoker;

public class BuilderImpl<T> implements Buildable<T>{
	private Constrainable<T> constraints;
	public BuilderImpl(Constrainable<T> constraints) {
		this.constraints = constraints;
	}
	
	public T build(){
		T object = create();
		callSetters(object);
		callMethods(object);
		return object;
	}

	protected void callMethods(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> method : constraints.getMethods()) {
				method.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected T create() throws JDGenRuntimeException {
		try {
			T object = constraints.getConstructor().invoke();

			return object;
		} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected void callSetters(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> setter : constraints.getSetters()) {
				setter.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}
}
