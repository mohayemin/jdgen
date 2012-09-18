package edu.iitdu.jdgen.builder.implementation;

import java.util.LinkedList;
import java.util.List;

import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuildable;

public class ListBuilder<T> implements ConstrainableBuildable<List<T>> {
	private final int size;
	private BuilderConstraintsImpl<T> constraints;

	public ListBuilder(Class<T> type, int size) {
		this.size = size;
		constraints = new BuilderConstraintsImpl<>(type);
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

	@Override
	public ListBuilder<T> execute(String methodName,
		Object... arguments) {
		constraints.execute(methodName, arguments);
		return this;
	}

	@Override
	public ListBuilder<T> construct(Object... arguments) {
		constraints.construct(arguments);
		return this;
	}

	@Override
	public <U> ListBuilder<T> set(String setterName, U value) {
		constraints.set(setterName, value);
		return this;
	}

}
