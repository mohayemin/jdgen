package edu.iitdu.jdgen.builder.implementation;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.Configurable;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements Buildable<T>{
	private Configurable<T> configuration;
	
	public ObjectBuilder(Class<T> type) {
		this(new ConfigurationImpl<>(type));
	}

	public ObjectBuilder(Configurable<T> configuration) {
		this.configuration = configuration;
	}
	
	public ObjectBuilder<T> execute(String methodName,
		Object... arguments) throws JDGenRuntimeException {
		configuration.execute(methodName, arguments);
		return this;
	}

	public ObjectBuilder<T> construct(Object... arguments)
		throws JDGenRuntimeException {
		configuration.construct(arguments);
		return this;
	}

	public <U> ObjectBuilder<T> set(String setterVariableName, U value) {
		configuration.set(setterVariableName, value);
		return this;
	}

	/**
	 * While building, first the object is instantiated.
	 * Then the setters are called.
	 * And then other methods are called.
	 */
	@Override
	public T build() {
		BuilderImpl<T> builder = new BuilderImpl<>(configuration);
		return builder.build();
	}
}
