package edu.iitdu.jdgen.builder;

import edu.iitdu.jdgen.exception.JDGenException;
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
	public T buildObject() {
		T instance;
		try {
			instance = type.newInstance();
		} catch (Exception e) {
			throw new JDGenException("Exception occurred when instantiating object", e);
		}
		
		ClassInfo<T> classInfo = new ClassInfo<T>(type);
		for (MethodInfo setterInfo : classInfo.getSetters()) {
			try {
				setterInfo.getMethod().invoke(instance, defaultValueProvider.getValueFor(setterInfo.getFirstParameterType()));
			} catch (Exception e) {
				throw new JDGenException("Exception occurred when invoking setter: " + setterInfo, e);
			}
		}
				
		return instance;
	}

}
