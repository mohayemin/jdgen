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
	
	public Iterable<MethodInfo> getSetters() {
		
		if (setters == null) {
			setters = new ArrayList<MethodInfo>();

			for (Method method : type.getMethods()) {
				
			}	
		}
		
		return setters;
	}	
}
