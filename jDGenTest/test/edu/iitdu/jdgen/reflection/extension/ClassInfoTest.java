package edu.iitdu.jdgen.reflection.extension;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.iitdu.jdgen.dummy.MethodTestGuineaPig;

public class ClassInfoTest {
	
	@Test
	public void getSetters(){
		ClassInfo<MethodTestGuineaPig> classInfo = new ClassInfo<MethodTestGuineaPig>(MethodTestGuineaPig.class);
		
		Iterable<MethodInfo> setters = classInfo.getSetters();
		
		fail("not tested");		
	}
}
