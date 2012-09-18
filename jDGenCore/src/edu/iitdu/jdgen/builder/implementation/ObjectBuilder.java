package edu.iitdu.jdgen.builder.implementation;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuildable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements ConstrainableBuildable<T>{
	private BuilderConstraintsImpl<T> constraints;
	private BuilderImpl<T> builder;

	public ObjectBuilder(Class<T> type) {
		constraints = new BuilderConstraintsImpl<>(type);
		builder = new BuilderImpl<>(constraints);
	}

	@Override
	public ObjectBuilder<T> execute(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		constraints.execute(methodName, arguments);
		return this;
	}

	@Override
	public ObjectBuilder<T> construct(Object... arguments)
		throws JDGenRuntimeException {
		constraints.construct(arguments);
		return this;
	}

	@Override
	public <U> ObjectBuilder<T> set(String setterVariableName, U value) {
		constraints.set(setterVariableName, value);
		return this;
	}

	/**
	 * While building, first the object is instantiated.
	 * Then the setters are called.
	 * And then other methods are called.
	 */
	@Override
	public T build() {
		return builder.build();
	}

}
