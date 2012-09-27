package edu.iitdu.jdgen.builder;

import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.configuration.Configurable;
import edu.iitdu.jdgen.configuration.Configuration;

public class ListBuilder<T> implements Buildable<List<T>> {
	private final int size;
	private Configurable<T> configuration;

	public ListBuilder(Class<T> type, int size) {
		this(new Configuration<>(type), size);
	}
	
	public ListBuilder(Configurable<T> configuration, int size) {
		this.configuration = configuration;
		this.size = size;
	}

	@Override
	public List<T> build() {
		List<T> objects = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			Builder<T> builder = new Builder<>(configuration);
			objects.add(builder.build());
		}

		return objects;
	}
	
	public ListBuilder<T> execute(String methodName,
		Object... arguments) {
		configuration.execute(methodName, arguments);
		return this;
	}

	public ListBuilder<T> construct(Object... arguments) {
		configuration.construct(arguments);
		return this;
	}

	public <U> ListBuilder<T> set(String setterName, U value) {
		configuration.set(setterName, value);
		return this;
	}
}
