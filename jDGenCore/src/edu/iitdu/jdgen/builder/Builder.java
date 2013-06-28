package edu.iitdu.jdgen.builder;

import java.util.List;

public class Builder<T> implements IBuilder<T> {
	private final IObjectBuilder<T> objectBuilder;
	private final IListBuilder<T> listBuilder;

	public Builder(Class<T> type) {
		this(new ObjectBuilder<T>(type), null);
	}
	
	public Builder(IObjectBuilder<T> objectBuilder, IListBuilder<T> listBuilder) {
		this.objectBuilder = objectBuilder;
		this.listBuilder = listBuilder;
	}

	@Override
	public T buildObject() {
		return objectBuilder.buildObject();
	}

	@Override
	public List<T> buildList() {
		return listBuilder.buildList();
	}
}
