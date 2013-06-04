package edu.iitdu.jdgen.reflection;

public class ConstructorInvoker<T> implements IConstructorInvoker<T>{
	private Object[] arguments;
	private Class<?>[] argumentTypes;
	private Class<T> type;
	
	public ConstructorInvoker(Class<T> type, Object...arguments) {
		this.type = type;
		this.arguments = arguments;
		argumentTypes = new Class<?>[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			argumentTypes[i] = arguments[i].getClass();
		}
	}
	
	@Override
	public T construct() {
		
		return null;
	}
}
