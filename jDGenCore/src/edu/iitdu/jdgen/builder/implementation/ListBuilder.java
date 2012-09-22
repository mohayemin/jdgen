package edu.iitdu.jdgen.builder.implementation;

import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.Buildable;
import edu.iitdu.jdgen.builder.abstraction.Constrainable;

public class ListBuilder<T> implements Buildable<List<T>> {
	private final int size;
	private Constrainable<T> constraints;

	public ListBuilder(Class<T> type, int size) {
		this(new ConstraintsImpl<>(type), size);
	}
	
	public ListBuilder(Constrainable<T> constraints, int size) {
		this.constraints = constraints;
		this.size = size;
	}

	@Override
	public List<T> build() {
		List<T> objects = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			BuilderImpl<T> builder = new BuilderImpl<>(constraints);
			objects.add(builder.build());
		}

		return objects;
	}
	
	public ListBuilder<T> execute(String methodName,
		Object... arguments) {
		constraints.execute(methodName, arguments);
		return this;
	}

	public ListBuilder<T> construct(Object... arguments) {
		constraints.construct(arguments);
		return this;
	}

	public <U> ListBuilder<T> set(String setterName, U value) {
		constraints.set(setterName, value);
		return this;
	}
}
