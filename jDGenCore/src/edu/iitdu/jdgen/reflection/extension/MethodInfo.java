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
			isSetter = hasExactlyOneParameter() & nameLengthAtLeastFour()
					& nameStartsWithSet() & suffixStartsWithUpperCase();
		}

		return isSetter;
	}

	private boolean suffixStartsWithUpperCase() {
		String suffix = method.getName().substring(3);
		return Character.isUpperCase(suffix.charAt(0));
	}

	private boolean nameStartsWithSet() {
		return method.getName().startsWith("set");
	}

	private boolean nameLengthAtLeastFour() {
		return method.getName().length() >= 4;
	}

	private boolean hasExactlyOneParameter() {
		return getParameterCount() == 1;
	}

	private int getParameterCount() {
		parameterCount = parameterCount != null ? parameterCount : method
				.getParameterTypes().length;

		return parameterCount;
	}

	public Class<?> getFirstParameterType() {
		Class<?> paramType = getParameterCount() > 0 ? method
				.getParameterTypes()[0] : Void.class;

		return paramType;
	}

	public Method getMethod() {
		return method;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals;
		if (this == obj) {
			equals = true;
		} else if (obj == null || getClass() != obj.getClass()) {
			equals = false;
		} else if (equals((MethodInfo) obj)) {
			equals = true;
		} else {
			equals = super.equals(obj);
		}

		return equals;
	}

	public boolean equals(MethodInfo anotherMethodInfo) {
		return anotherMethodInfo != null
				&& this.method.equals(anotherMethodInfo.method);
	}

	@Override
	public int hashCode() {
		return method.hashCode();
	}
}
