package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import edu.iitdu.jdgen.reflection.DefaultValueProvider;
import edu.iitdu.jdgen.reflection.extension.MethodInfo;

public class Builder<T> implements IBuilder<T> {
	private Class<T> type;

	public Builder(Class<T> type) {
		this.type = type;
	}

	@Override
	public T build() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T instance = type.newInstance();
		DefaultValueProvider defaultValueProvider = new DefaultValueProvider();
		
		for (Method method : type.getMethods()) {
			MethodInfo methodInfo = new MethodInfo(method);
			if (methodInfo.isSetter()) {
				method.invoke(instance, defaultValueProvider.getValueFor(methodInfo.getFirstParameterType()));
			}
		}
		
		return instance;
	}

	@Override
	public List<T> buildList() {
		return null;
	}
}
