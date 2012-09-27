package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.configuration.Configurable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.MethodInvoker;

public class Builder<T> implements Buildable<T>{
	private Configurable<T> configuration;
	public Builder(Configurable<T> configuration) {
		this.configuration = configuration;
	}
	
	public T build(){
		T object = create();
		callSetters(object);
		callMethods(object);
		
		
		
		return object;
	}

	protected void callMethods(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> method : configuration.getMethods()) {
				method.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected T create() throws JDGenRuntimeException {
		try {
			T object = configuration.getConstructor().invoke();

			return object;
		} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected void callSetters(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> setter : configuration.getSetters()) {
				setter.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}
}
