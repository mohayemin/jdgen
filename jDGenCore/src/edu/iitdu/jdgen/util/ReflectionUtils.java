package edu.iitdu.jdgen.util;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohayeminul Islam
 */
public class ReflectionUtils {
	public static List<Method> getSetters(Class<?> type) {
		Method[] allMethods = type.getMethods();
		LinkedList<Method> setters = new LinkedList<Method>();

		for (Method method : allMethods) {
			if (isSetter(method)) {
				setters.add(method);
			}
		}

		return setters;
	}

	public static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && hasParameter(method);
	}
	
	public static boolean hasParameter(Method method){
		return method.getParameterTypes().length > 0;
	}
}
