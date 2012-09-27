package edu.iitdu.jdgen.builder;

import edu.iitdu.jdgen.configuration.Configurable;
import edu.iitdu.jdgen.configuration.Configuration;
import edu.iitdu.jdgen.exception.JDGenRuntimeException;

/**
 * @author Mohayeminul Islam
 */
public class ObjectBuilder<T> implements Buildable<T>{
	private Configurable<T> configuration;
	
	public ObjectBuilder(Class<T> type) {
		this(new Configuration<>(type));
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
		Builder<T> builder = new Builder<>(configuration);
		return builder.build();
	}
}
