package edu.iitdu.jdgen.configuration;

import java.util.List;

import edu.iitdu.jdgen.reflection.ConstructorInvoker;
import edu.iitdu.jdgen.reflection.IConstructorInvoker;
import edu.iitdu.jdgen.reflection.IMethodInvoker;

public class Configuration<T> implements IConfiguration<T> {
	private IConstructorInvoker<T> constructor;
	private Class<T> type;
	
	public Configuration(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public IConfiguration<T> execute(String methodName, Object... arguments) {
		return null;
	}

	@Override
	public IConfiguration<T> construct(Object... arguments) {
		constructor = new ConstructorInvoker<T>(type, arguments);
		return this;
	}

	@Override
	public List<IMethodInvoker<T>> getMethods() {
		return null;
	}

	@Override
	public IConstructorInvoker<T> getConstructor() {
		if (constructor == null) {
			constructor = new ConstructorInvoker<T>(type); 
		}
		return constructor;
	}
}
