package edu.iitdu.jdgen.reflection.extension;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo<T> {
	private Class<T> type;
	private List<MethodInfo> setters;
	
	public ClassInfo(Class<T> type) {
		this.type = type;
	}
	
	public T newInstance() throws InstantiationException, IllegalAccessException {
		return type.newInstance();
	}
	
	public Iterable<MethodInfo> getSetters() {		
		if (setters == null) {
			generateSetters();
		}
		
		return setters;
	}

	private void generateSetters() {
		setters = new ArrayList<MethodInfo>();

		for (Method method : type.getMethods()) {
			MethodInfo methodInfo = new MethodInfo(method);
			if (methodInfo.isSetter()) {
				setters.add(methodInfo);
			}
		}
	}	
}
