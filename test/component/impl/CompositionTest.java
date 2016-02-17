package component.impl;

import org.junit.Test;

public class CompositionTest {
	
	private String className;
	private String referenceName;
	
	Composition c;

	public CompositionTest() {
		className = "CSSE371";
		referenceName = "JuniorDesign";
		
		c = new Composition(className, referenceName);
	}
	
	@Test
	public void testGetType(){
		
	}

}
