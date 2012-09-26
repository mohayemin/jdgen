package edu.iitdu.jdgen.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodUtils {
	public static Method findBestMatch(Class<?> type, String name,
		Class<?>... parameterTypes)
		throws NoSuchMethodException {
		Method bestMatch;

		try {
			bestMatch = type.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			try {
				bestMatch =
					type.getMethod(name,
						WrapperUtils.toWrapperArray(parameterTypes));
			} catch (NoSuchMethodException e1) {
				try {
					bestMatch =
						type.getMethod(name,
							WrapperUtils.toPrimitiveArray(parameterTypes));
				} catch (NoSuchMethodException e2) {
					throw e;
				}
			}
		}

		return bestMatch;
	}

	public static boolean hasParameter(Method method) {
		return method.getParameterTypes().length > 0;
	}

	public static boolean isSetter(Method method) {
		return isLikeSetter(method.getName())
				&& hasParameter(method);
	}

	private static boolean isLikeSetter(String methodName) {
		return methodName.startsWith("set")
				&& Character.isUpperCase(methodName.charAt(3));
	}

	public static <U> Method findSetter(Class<?> type,
		String setterVariableName)
		throws NoSuchMethodException {
		String setterName =
			"set" + Character.toUpperCase(setterVariableName.charAt(0))
				+ setterVariableName.substring(1);

		Method setter = null;
		for (Method method : type.getMethods()) {
			if (method.getName().equals(setterName)) {
				setter = method;
				break;
			}
		}
		if (setter == null) {
			throw new NoSuchMethodException(setterName);
		}
		
		return setter;
	}

	public static Map<String, Method> getSetters(Class<?> type){
		Map<String, Method> setters = new HashMap<>();
		
		return setters;
	}
}
