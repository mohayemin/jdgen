package edu.iitdu.jdgen.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		
		method = type.getMethod(methodName, ReflectionUtils.getTypes(arguments));
	}

	public MethodInvoker(Method method, Object... arguments) {
		this.method = method;
		this.arguments = arguments;
	}

	public Object invoke(Object invoker) throws IllegalAccessException,
		IllegalArgumentException, InvocationTargetException {
		return method.invoke(invoker, arguments);
	}
}
