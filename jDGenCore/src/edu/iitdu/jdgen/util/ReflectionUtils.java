package edu.iitdu.jdgen.util;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohayeminul Islam
 */
public class ReflectionUtils {

	public static Class<?>[] getPrimitiveTypes(Object[] wrapperValues) {
		Class<?>[] wrapperTypes = getTypes(wrapperValues);
		
		return WrapperUtils.toPrimitiveArray(wrapperTypes);
	}

	public static List<Method> getSetters(Class<?> type) {
		Method[] allMethods = type.getMethods();
		LinkedList<Method> setters = new LinkedList<Method>();

		for (Method method : allMethods) {
			if (MethodUtils.isSetter(method)) {
				setters.add(method);
			}
		}

		return setters;
	}

	public static Class<?>[] getTypes(Object[] objects) {
		Class<?>[] types = new Class<?>[objects.length];

		for (int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}

		return types;
	}

	public static Method searchBestMatchMethod(Class<?> type,
		String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {

		return MethodUtils.findBestMatch(type, methodName, parameterTypes);
	}
}
