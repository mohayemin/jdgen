package edu.iitdu.jdgen.util;

import java.lang.reflect.Constructor;

public class ConstructorUtils {
	public static <T> Constructor<T> findBestMatch(Class<T> type,
		Class<?>... parameterTypes) throws NoSuchMethodException {
		Constructor<T> bestMatch;

		try {
			bestMatch = type.getConstructor(parameterTypes);
		} catch (NoSuchMethodException e) {
			try {
				bestMatch =
					type.getConstructor(WrapperUtils.toWrapperArray(parameterTypes));
			} catch (NoSuchMethodException e1) {
				try {
					bestMatch =
						type.getConstructor(WrapperUtils.toPrimitiveArray(parameterTypes));
				} catch (NoSuchMethodException e2) {
					throw e;
				}
			}
		}

		return bestMatch;
	}
}
