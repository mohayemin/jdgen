package edu.iitdu.jdgen.builder;

import java.util.List;

/**
 * 
 * @author Mohayeminul Islam
 */
public interface IBuilder<T> {
	public T build() throws InstantiationException, IllegalAccessException;
	
	public List<T> buildList();
}
