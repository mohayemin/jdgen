package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;

public interface IObjectBuilder<T> {

	public abstract T buildObject() throws InstantiationException,
			IllegalAccessException, InvocationTargetException;

}