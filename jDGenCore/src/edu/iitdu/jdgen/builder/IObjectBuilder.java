package edu.iitdu.jdgen.builder;

import edu.iitdu.jdgen.exception.JDGenException;

public interface IObjectBuilder<T> {

	public abstract T buildObject() throws JDGenException;

}