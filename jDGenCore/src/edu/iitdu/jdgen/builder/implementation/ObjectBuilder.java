package edu.iitdu.jdgen.builder.implementation;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.Constrainable;
import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuildable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements Buildable<T>{
	private Constrainable<T> constraints;
	
	public ObjectBuilder(Class<T> type) {
		this(new ConstraintsImpl<>(type));
	}

	public ObjectBuilder(Constrainable<T> constraints) {
		this.constraints = constraints;
	}
	
	public ObjectBuilder<T> execute(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		constraints.execute(methodName, arguments);
		return this;
	}

	public ObjectBuilder<T> construct(Object... arguments)
		throws JDGenRuntimeException {
		constraints.construct(arguments);
		return this;
	}

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
		BuilderImpl<T> builder = new BuilderImpl<>(constraints);
		return builder.build();
	}
}
