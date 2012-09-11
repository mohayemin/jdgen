package edu.iitdu.jdgen.util;

import java.lang.reflect.Method;

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
		return method.getName().startsWith("set") && hasParameter(method);
	}

}
