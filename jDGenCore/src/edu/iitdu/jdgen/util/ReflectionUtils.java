package edu.iitdu.jdgen.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Mohayeminul Islam
 */
public class ReflectionUtils {
	private static Map<Class<?>, Class<?>> wrapperToPrimitive =
		new HashMap<Class<?>, Class<?>>();

	static {
		wrapperToPrimitive.put(Integer.class, int.class);
		wrapperToPrimitive.put(Byte.class, byte.class);
		wrapperToPrimitive.put(Short.class, short.class);
		wrapperToPrimitive.put(Long.class, long.class);
		wrapperToPrimitive.put(Double.class, double.class);
		wrapperToPrimitive.put(Float.class, float.class);
		wrapperToPrimitive.put(Character.class, char.class);
		wrapperToPrimitive.put(Boolean.class, boolean.class);
	}

	public static Class<?>[] getPrimitiveTypes(Object[] wrapperValues) {
		Class<?>[] primitiveTypes = new Class<?>[wrapperValues.length];

		for (int i = 0; i < primitiveTypes.length; i++) {
			primitiveTypes[i] = unbox(wrapperValues[i].getClass());
		}

		return primitiveTypes;
	}

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

	public static Class<?>[] getTypes(Object[] objects) {
		Class<?>[] types = new Class<?>[objects.length];

		for (int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}

		return types;
	}

	public static Class<?>[] getTypesWithPrimitiveWrapper(Object[] objects) {
		Class<?>[] types = new Class<?>[objects.length];

		for (int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}

		return types;
	}

	public static boolean hasParameter(Method method) {
		return method.getParameterTypes().length > 0;
	}

	public static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && hasParameter(method);
	}

	public static Method resolveMethodWithParameterTypeDifference(
		List<Method> methods, Object... parameters)
		throws NoSuchMethodException {
		Class<?>[] parameterTypes = getTypes(parameters);

		for (Method method : methods) {
			if (Arrays
				.equals(method.getGenericParameterTypes(), parameterTypes)) {
				return method;
			}
		}

		throw new NoSuchMethodException();
	}

	public static Method searchMethod(Class<?> type, String name,
		Object... parameters) throws NoSuchMethodException {
		List<Method> matchingMethods = new LinkedList<Method>();
		int parameterLength = parameters.length;

		for (Method method : type.getMethods()) {
			if (method.getName().equals(name)
				&& method.getGenericParameterTypes().length == parameterLength) {
				matchingMethods.add(method);
			}
		}

		if (matchingMethods.size() == 1) {
			return matchingMethods.get(0);
		} else if (matchingMethods.size() == 0) {
			throw new NoSuchMethodException();
		} else {
			return resolveMethodWithParameterTypeDifference(matchingMethods,
				parameters);
		}
	}

	/**
	 * 
	 * @param boxedType
	 * @return primitive equivalent if <code>boxedType</code> is wrapper. The
	 *         same as <code>boxedType</code> otherwise
	 */
	public static Class<?> unbox(Class<?> boxedType) {
		Class<?> primitive = wrapperToPrimitive.get(boxedType);
		return primitive != null ? primitive : boxedType;
	}
}
