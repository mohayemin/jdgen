package edu.iitdu.jdgen.reflection.extension;

import java.lang.reflect.Method;

public class MethodInfo {
	private Method method;
	private Boolean isSetter;

	public MethodInfo(Method method) {
		this.method = method;
	}

	public boolean isSetter() {
		if (isSetter == null) {
			isSetter = false;			

			if (method.getParameterCount() == 1) {				
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
}
