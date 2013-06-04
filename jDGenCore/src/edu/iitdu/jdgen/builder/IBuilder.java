package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 
 * @author Mohayeminul Islam
 */
public interface IBuilder<T> {
	public T build() throws InstantiationException, IllegalAccessException, InvocationTargetException;
	
	public List<T> buildList();
}
