package edu.iitdu.jdgen.reflection.extension;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo<T> {
	private Class<T> type;
	private List<Method> setters;
	
	public ClassInfo(Class<T> type) {
		this.type = type;
	}
	
	public Iterable<Method> setters() {
		if (setters == null) {
			setters = new ArrayList<Method>();

			for (Method method : type.getMethods()) {
				
			}	
		}
		
		return setters;
	}

	public static boolean isSetter(Method method) {
		return false;
	}
}
