package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import edu.iitdu.jdgen.exception.JDGenRuntimeException;

/**
 * @author Mohayeminul Islam
 */
public class DefaultObjectBuilder<T> extends AbstractConstrainableBuilder<T> {

	public DefaultObjectBuilder(Class<T> type) {
		super(type);
	}

	@Override
	public T build() {
		T object = construct();

		return object;
	}

	@Override
	protected void callMethods(T object) {
		try {
			for (Method method : methods) {
				method.invoke(object);
			}
		} catch (IllegalAccessException e) {
			throw new JDGenRuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new JDGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	@Override
	protected T construct() {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		Object[] parameters = new Object[parameterTypes.length];

		try {
			for (int i = 0; i < parameters.length; i++) {
				parameters[i] = parameterTypes[i].newInstance();
			}

			T object = constructor.newInstance(parameters);

			return object;
		} catch (InstantiationException e) {
			throw new JDGenRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new JDGenRuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new JDGenRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}
}
