package edu.iitdu.jdgen.reflection.extension;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.iitdu.jdgen.dummy.MethodTestGuineaPig;

public class ClassInfoTest {

	@Test
	public void getSetters() throws SecurityException, NoSuchMethodException {
		ClassInfo<MethodTestGuineaPig> classInfo = new ClassInfo<MethodTestGuineaPig>(
				MethodTestGuineaPig.class);

		Iterable<MethodInfo> setters = classInfo.getSetters();
		List<MethodInfo> list = new ArrayList<MethodInfo>();	
		for (MethodInfo methodInfo : setters) {
			list.add(methodInfo);
		}
		
		assertEquals(2, list.size());
		assertTrue(list.contains(new MethodInfo(MethodTestGuineaPig.class.getMethod("setVal", Integer.class))));
		assertTrue(list.contains(new MethodInfo(MethodTestGuineaPig.class.getMethod("setVal", Long.class))));
	}
}
