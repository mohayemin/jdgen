package edu.iitdu.jdgen.builder;

import java.util.List;

public class Builder<T> implements IBuilder<T> {
	private Class<T> type;
	
	public Builder(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public T build() throws InstantiationException, IllegalAccessException {
		T instance =type.newInstance();
		
		
		
		return instance;
	}

	@Override
	public List<T> buildList() {
		return null;
	}
}
