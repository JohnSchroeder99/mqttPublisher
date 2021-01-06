import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class SimpleTest {

	@Test
	void test() {
		assertTrue(true); 
	}
	
	@Test 
	void failTest() {
		assertTrue(false); 
	}

}
