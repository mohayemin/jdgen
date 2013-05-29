package edu.iitdu.jdgen.builder;

public class Builder<T> implements IBuilder<T> {
	private Class<T> type;
	
	public Builder(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public T Build() throws InstantiationException, IllegalAccessException {
		return type.newInstance();
	}
}
