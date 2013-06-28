package edu.iitdu.jdgen.reflection.extension;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.iitdu.jdgen.dummy.MethodTestGuineaPig;

public class MethodInfoTest {
	private static Class<MethodTestGuineaPig> type;
	
	@BeforeClass
	public static void beforeClass(){
		type = MethodTestGuineaPig.class;
	}
	
	@Test
	public void isSetter_nameIsJustSet_no() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("set", Integer.class));
		
		assertFalse(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_nameDoesNotStartWithSet_no() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("notASetter", Integer.class));
		
		assertFalse(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_noParameter_no() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal"));
		
		assertFalse(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_moreThanOneParameter_no() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		
		assertFalse(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_suffixDoesNotStartWithCap_no() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setval", Integer.class));
		
		assertFalse(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_yes() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal", Integer.class));
		
		assertTrue(methodInfo.isSetter());
	}
	
	@Test
	public void isSetter_hasReturnType_yes() throws NoSuchMethodException, SecurityException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal", Long.class));
		
		assertTrue(methodInfo.isSetter());
	}
	
	@Test
	public void getFirstParameterType_hasOneParameter() throws SecurityException, NoSuchMethodException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal", Long.class));
		
		assertTrue(methodInfo.getFirstParameterType().equals(Long.class));
	}
	
	@Test
	public void getFirstParameterType_hasTwoParameters() throws SecurityException, NoSuchMethodException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		
		assertTrue(methodInfo.getFirstParameterType().equals(Integer.class));
	}
	
	@Test
	public void getFirstParameterType_hasNoParameter() throws SecurityException, NoSuchMethodException{
		MethodInfo methodInfo = new MethodInfo(type.getMethod("setVal"));
		
		assertTrue(methodInfo.getFirstParameterType().equals(Void.class));
	}
	
	@Test
	public void equalsAndHash_equal() throws SecurityException, NoSuchMethodException{
		MethodInfo methodInfo1 = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		MethodInfo methodInfo2 = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		
		assertTrue(methodInfo1.equals(methodInfo2));
		assertTrue(methodInfo1.hashCode() == methodInfo2.hashCode());
	}
	
	@Test
	public void equalsAndHash_unequal_sameType() throws SecurityException, NoSuchMethodException{
		MethodInfo methodInfo1 = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		MethodInfo methodInfo2 = new MethodInfo(type.getMethod("notASetter", Integer.class));
		
		assertFalse(methodInfo1.equals(methodInfo2));
		assertFalse(methodInfo1.hashCode() == methodInfo2.hashCode());
	}
	
	@Test
	public void equalsAndHash_unequal_differentType() throws SecurityException, NoSuchMethodException {
		MethodInfo methodInfo1 = new MethodInfo(type.getMethod("setVal", Integer.class, Long.class));
		String otherObject = "Hello World";
		
		assertFalse(methodInfo1.equals(otherObject));
		assertFalse(methodInfo1.hashCode() == otherObject.hashCode());
	}
}
