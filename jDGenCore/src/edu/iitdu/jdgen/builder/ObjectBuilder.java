package edu.iitdu.jdgen.builder;

import edu.iitdu.jdgen.exception.JDGenException;
import edu.iitdu.jdgen.reflection.DefaultValueProvider;
import edu.iitdu.jdgen.reflection.IDefaultValueProvider;
import edu.iitdu.jdgen.reflection.extension.ClassInfo;
import edu.iitdu.jdgen.reflection.extension.MethodInfo;

public class ObjectBuilder<T>  implements IObjectBuilder<T>{

	private ClassInfo<T> classInfo;
	private IDefaultValueProvider defaultValueProvider;

	public ObjectBuilder(Class<T> type) {
		classInfo = new ClassInfo<T>(type);
		defaultValueProvider = new DefaultValueProvider();
	}
	
	@Override
	public T buildObject() {
		T instance = instantiate();
		
		for (MethodInfo setterInfo : classInfo.getSetters()) {
			invokeMethod(instance, setterInfo);
		}

		return instance;
	}

	private void invokeMethod(T instance, MethodInfo setterInfo) {
		try {
			setterInfo.getMethod().invoke(instance, defaultValueProvider.getValueFor(setterInfo.getFirstParameterType()));
		} catch (Exception e) {
			throw new JDGenException("Exception occurred when invoking setter: " + setterInfo, e);
		}
	}

	private T instantiate() {
		T instance;
		try {
			instance = classInfo.newInstance();
			return instance;
		} catch (Exception e) {
			throw new JDGenException("Exception occurred when instantiating object", e);
		}		
	}
	
}
