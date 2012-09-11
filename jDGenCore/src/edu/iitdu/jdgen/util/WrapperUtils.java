package edu.iitdu.jdgen.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WrapperUtils {
	private static Map<Class<?>, Class<?>> wrapperToPrimitive =
		new HashMap<>();

	private static Map<Class<?>, Class<?>> primitiveToWrapper = new HashMap<>();
	static {
		wrapperToPrimitive.put(Integer.class, int.class);
		wrapperToPrimitive.put(Byte.class, byte.class);
		wrapperToPrimitive.put(Short.class, short.class);
		wrapperToPrimitive.put(Long.class, long.class);
		wrapperToPrimitive.put(Double.class, double.class);
		wrapperToPrimitive.put(Float.class, float.class);
		wrapperToPrimitive.put(Character.class, char.class);
		wrapperToPrimitive.put(Boolean.class, boolean.class);

		for (Entry<Class<?>, Class<?>> entry : wrapperToPrimitive.entrySet()) {
			primitiveToWrapper.put(entry.getValue(), entry.getKey());
		}
	}

	/**
	 * 
	 * @param wrapperType
	 *            the type to convert
	 * @return primitive equivalent if <code>boxedType</code> is wrapper. The
	 *         same as <code>boxedType</code> otherwise
	 */
	public static Class<?> toPrimitive(Class<?> wrapperType) {
		Class<?> primitive = wrapperToPrimitive.get(wrapperType);
		return primitive == null ? wrapperType : primitive;
	}

	/**
	 * 
	 * @param primitiveType
	 *            the type to convert
	 * @return wrapper equivalent if <code>primitiveType</code> is primitive.
	 *         The same as <code>primitiveType</code> otherwise
	 */
	public static Class<?> toWrapper(Class<?> primitiveType) {
		Class<?> wrapper = primitiveToWrapper.get(primitiveType);

		return wrapper == null ? primitiveType : wrapper;
	}

	/**
	 * Uses {@link #toPrimitive(Class)} to convert an array of types
	 * 
	 * @param types
	 * @return
	 */
	public static Class<?>[] toPrimitiveArray(Class<?>... types) {
		Class<?> toPrimitive[] = new Class<?>[types.length];

		for (int i = 0; i < types.length; i++) {
			toPrimitive[i] = toPrimitive(types[i]);
		}

		return toPrimitive;
	}

	/**
	 * Uses {@link #toWrapper(Class)} to convert an array of types
	 * 
	 * @param parameterTypes
	 * @return
	 */
	public static Class<?>[] toWrapperArray(Class<?>... parameterTypes) {
		Class<?> toWrapper[] = new Class<?>[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			toWrapper[i] = toWrapper(parameterTypes[i]);
		}

		return toWrapper;
	}
}
