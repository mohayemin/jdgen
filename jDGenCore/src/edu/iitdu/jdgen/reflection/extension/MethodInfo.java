package edu.iitdu.jdgen.reflection.extension;

import java.lang.reflect.Method;

public class MethodInfo {
	private Method method;
	private Boolean isSetter;
	private Integer parameterCount;

	public MethodInfo(Method method) {
		this.method = method;
	}

	public boolean isSetter() {
		if (isSetter == null) {
			isSetter = false;

			if (getParameterCount() == 1) {
				String name = method.getName();
				if (name.length() >= 4) {
					if (name.startsWith("set")) {
						String suffix = name.substring(3);
						if (Character.isUpperCase(suffix.charAt(0))) {
							isSetter = true;
						}
					}
				}
			}
		}

		return isSetter;
	}

	private int getParameterCount() {
		parameterCount =
			parameterCount != null ? parameterCount : method
				.getParameterTypes().length;

		return parameterCount;
	}

	public Class<?> getFirstParameterType() {
		Class<?> paramType =
			getParameterCount() > 0 ? method.getParameterTypes()[0]
				: Void.class;

		return paramType;
	}
}
