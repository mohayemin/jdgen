//package edu.iitdu.jdgen.builder.implementation;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import edu.iitdu.jdgen.builder.abstraction.ConstrainableBuilder;
//import edu.iitdu.jdgen.exception.JDGenRuntimeException;
//import edu.iitdu.jdgen.reflection.MethodInvoker;
//
//public class ListBuilder<T> implements ConstrainableBuilder<List<T>> {
//	private final int size;
//	private BuilderConstraintsImpl<T> implementation;
//
//	public ListBuilder(Class<T> type, int size) {
//		this.size = size;
//		implementation = new BuilderConstraintsImpl<>(type);
//	}
//
//	@Override
//	public List<T> build() {
//		for (int i = 0; i < size; i++) {
//			
//		}
//		
//		ObjectBuilder<T> objectBuilder = new ObjectBuilder<>(implementation.getType());
//		objectBuilder.construct(implementation.getConstructor());
//		for (MethodInvoker<T> method : methods) {
//			objectBuilder.execute(method);
//		}
//
//		for (MethodInvoker<T> setter : setters) {
//			objectBuilder.set(setter);
//		}
//
//		List<T> objects = new LinkedList<>();
//		for (int i = 0; i < size; i++) {
//			objects.add(objectBuilder.build());
//		}
//		
//		return objects;
//	}
//
//	@Override
//	public ConstrainableBuilder<List<T>> execute(String methodName,
//		Object... arguments) {
//		try {
//			implementation.execute(methodName, arguments);
//			return this;
//		} catch (NoSuchMethodException e) {
//			throw new JDGenRuntimeException(e);
//		}
//	}
//
//	@Override
//	public ConstrainableBuilder<List<T>> construct(Object... arguments) {
//		try {
//			implementation.construct(arguments);
//			return this;
//		} catch (NoSuchMethodException e) {
//			throw new JDGenRuntimeException(e);
//		}
//	}
//
//	@Override
//	public <U> ConstrainableBuilder<List<T>> set(String setterName, U value) {
//		try {
//			implementation.set(setterName, value);
//			return this;
//		} catch (NoSuchMethodException e) {
//			throw new JDGenRuntimeException(e);
//		}
//	}
//
//}
