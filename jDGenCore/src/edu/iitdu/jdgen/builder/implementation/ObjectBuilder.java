package edu.iitdu.jdgen.builder.implementation;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;
import edu.iitdu.jdgen.reflection.MethodInvoker;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements ConstrainableBuilder<T> {
	private BuilderConstraintsImpl<T> implemantation;

	public ObjectBuilder(Class<T> type) {
		implemantation = new BuilderConstraintsImpl<>(type);
	}

	@Override
	public ConstrainableBuilder<T> execute(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		implemantation.execute(methodName, arguments);
		return this;
	}

	@Override
	public ConstrainableBuilder<T> construct(Object... arguments)
		throws JDGenRuntimeException {
		implemantation.construct(arguments);
		return this;
	}

	@Override
	public <U> ConstrainableBuilder<T> set(String setterVariableName, U value) {
		implemantation.set(setterVariableName, value);
		return this;
	}

	/**
	 * While building, first the object is instantiated.
	 * Then the setters are called.
	 * And then other methods are called.
	 */
	@Override
	public T build() {
		T object = create();
		callSetters(object);
		callMethods(object);
		return object;
	}

	protected void callMethods(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> method : implemantation.getMethods()) {
				method.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected T create() throws JDGenRuntimeException {
		try {
			T object = implemantation.getConstructor().invoke();

			return object;
		} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException e) {
			throw new JDGenRuntimeException(e);
		}
	}

	protected void callSetters(T object) throws JDGenRuntimeException {
		try {
			for (MethodInvoker<T> setter : implemantation.getSetters()) {
				setter.invoke(object);
			}
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			throw new JDGenRuntimeException(e);
		}
	}
}
