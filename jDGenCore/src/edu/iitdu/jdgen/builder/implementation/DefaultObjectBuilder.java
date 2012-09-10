package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.MethodInvoker;

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
		callMethods(object);
		return object;
	}

	@Override
	protected void callMethods(T object) throws JDGenRuntimeException{
		try {
			for (MethodInvoker<T> method : methods) {
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
		try {
			T object = constructor.invoke();

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
