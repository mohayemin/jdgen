package edu.iitdu.jdgen.reflection;

import java.lang.reflect.InvocationTargetException;

public interface IDefaultValueProvider {
	public <T> T getValueFor(Class<T> type) throws InstantiationException, IllegalAccessException, InvocationTargetException;
}
