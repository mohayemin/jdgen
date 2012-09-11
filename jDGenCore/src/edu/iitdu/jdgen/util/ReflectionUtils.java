package edu.iitdu.jdgen.util;

import java.lang.reflect.Constructor;
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

	public static Method findBestMatchMethod(Class<?> type,
		String methodName, Object... arguments) throws NoSuchMethodException {
		Class<?>[] parameterTypes = getTypes(arguments);

		return MethodUtils.findBestMatch(type, methodName, parameterTypes);
	}

	public static <T> Constructor<T> findBestMatchConstructor(Class<T> type,
		Object... arguments) throws NoSuchMethodException {
		Class<?>[] parameterTypes = getTypes(arguments);

		return ConstructorUtils.findBestMatch(type, parameterTypes);
	}
}
