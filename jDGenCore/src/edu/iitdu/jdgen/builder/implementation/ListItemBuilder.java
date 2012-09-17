package edu.iitdu.jdgen.builder.implementation;

import edu.iitdu.jdgen.builder.abstraction.Buildable;

class ListItemBuilder<T> implements Buildable<T> {
	private BuilderConstraintsImpl<T> implementation;
	
	public ListItemBuilder(BuilderConstraintsImpl<T> implementation) {
		this.implementation = implementation;
	}
	
	@Override
	public T build() {
		return null;
	}
	
}
