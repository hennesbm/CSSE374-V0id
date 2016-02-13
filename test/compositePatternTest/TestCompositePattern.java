package compositePatternTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import compositePattern.CompositePattern;

public class TestCompositePattern {

	@Test
	public void test() throws IOException {
		CompositePattern p = new CompositePattern();
		assertEquals(true, p.isPassTest());
	}

}
