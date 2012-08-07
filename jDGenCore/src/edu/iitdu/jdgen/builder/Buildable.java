package edu.iitdu.jdgen.builder;

import java.util.List;

/**
 * @author Mohayeminul Islam
 */
public interface Buildable<T> {
	public List<T> build();
}
