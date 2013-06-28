package edu.iitdu.jdgen.reflection;

import edu.iitdu.jdgen.exception.JDGenException;


public interface IDefaultValueProvider {
	public <T> T getValueFor(Class<T> type) throws JDGenException;
}
