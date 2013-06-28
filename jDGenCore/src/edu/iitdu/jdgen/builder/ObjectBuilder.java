package edu.iitdu.jdgen.builder;

import java.lang.reflect.InvocationTargetException;

import edu.iitdu.jdgen.reflection.DefaultValueProvider;
import edu.iitdu.jdgen.reflection.extension.ClassInfo;
import edu.iitdu.jdgen.reflection.extension.MethodInfo;

public class ObjectBuilder<T>  implements IObjectBuilder<T>{

	private Class<T> type;

	public ObjectBuilder(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public T buildObject() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		T instance = type.newInstance();
		DefaultValueProvider defaultValueProvider = new DefaultValueProvider();
		
		ClassInfo<T> classInfo = new ClassInfo<T>(type);
		for (MethodInfo setterInfo : classInfo.getSetters()) {
			setterInfo.getMethod().invoke(instance, defaultValueProvider.getValueFor(setterInfo.getFirstParameterType()));
		}
				
		return instance;
	}

}
