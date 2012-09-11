package edu.iitdu.jdgen.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import edu.iitdu.jdgen.util.ReflectionUtils;

/**
 * 
 * @author Mohayeminul Islam
 */
public class MethodInvoker<T> implements Invocable<T> {
	private Method method;
	private Object[] arguments;

	public MethodInvoker(Class<T> type, String methodName,
		Object... arguments) throws NoSuchMethodException, SecurityException {
		this(ReflectionUtils.findBestMatchMethod(type, methodName, arguments), arguments);
	}

	public MethodInvoker(Method method, Object... arguments) {
		this.method = method;
		this.arguments = arguments;
	}

	public Object invoke(Object invoker) throws IllegalAccessException,
		IllegalArgumentException, InvocationTargetException {
		return method.invoke(invoker, arguments);
	}
	
	@Override
	public String toString() {
		return "Name: " + method.getName() + " Arguments: " + Arrays.toString(arguments);
	}
}
