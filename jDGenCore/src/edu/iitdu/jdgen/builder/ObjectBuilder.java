package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.reflection.DefaultValueProvider;
import edu.iitdu.jdgen.reflection.IDefaultValueProvider;
import edu.iitdu.jdgen.reflection.extension.ClassInfo;
import edu.iitdu.jdgen.reflection.extension.MethodInfo;

public class ObjectBuilder<T>  implements IObjectBuilder<T>{

	private Class<T> type;
	private IDefaultValueProvider defaultValueProvider;

	public ObjectBuilder(Class<T> type) {
		this.type = type;
		defaultValueProvider = new DefaultValueProvider();
	}
	
	@Override
	public T buildObject() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		T instance = type.newInstance();
		
		ClassInfo<T> classInfo = new ClassInfo<T>(type);
		for (MethodInfo setterInfo : classInfo.getSetters()) {
			setterInfo.getMethod().invoke(instance, defaultValueProvider.getValueFor(setterInfo.getFirstParameterType()));
		}
				
		return instance;
	}

}
