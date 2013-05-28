package edu.iitdu.jdgen.configuration;

import java.util.List;

import edu.iitdu.jdgen.reflection.IConstructorInvoker;
import edu.iitdu.jdgen.reflection.IMethodInvoker;

/**
 * 
 * @author Mohayeminul Islam
 */
public interface IConfiguration<T> {
	public IConfiguration<T> execute(String methodName, Object... arguments);
	public IConfiguration<T> construct(Object... arguments);
	
	public List<IMethodInvoker<T>> getMethods();
	public List<IConstructorInvoker<T>> getConstructors();
}
