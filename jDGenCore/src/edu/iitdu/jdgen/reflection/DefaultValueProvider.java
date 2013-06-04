package edu.iitdu.jdgen.reflection;

import java.util.HashMap;
import java.util.Map;


public class DefaultValueProvider {
	private static Map<Class<?>, Object> defaultValues  = defaultValues();
	
	@SuppressWarnings("unchecked")
	public <T> T getValueFor(Class<T> type){
		return (T) defaultValues.get(type);
	}
	
	private static HashMap<Class<?>, Object> defaultValues(){
		HashMap<Class<?>, Object> defaultValues = new HashMap<Class<?>, Object>();
		
		defaultValues.put(Boolean.class, false);
		defaultValues.put(Byte.class, (byte)0);
		defaultValues.put(Character.class, 'A');
		defaultValues.put(Double.class, 0.0);
		defaultValues.put(Long.class, 0L);
		defaultValues.put(Float.class, 0.0F);
		defaultValues.put(Short.class, (short)0);
		defaultValues.put(Integer.class, 0);
		
		return defaultValues;
	}
}
