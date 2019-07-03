/**
 * 
 */
package collections;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author PD
 *
 */
public class CollectionsTest {


	private Collections collection = null;
	
	@Before
	public void setUp() {
		System.out.println("Settling up");
		collection = new Collections();
		// list = new ArrayList<String>();
	}

	@After
	public void tearDown() {
		System.out.println("Tearing Down");
		collection = null;
	}

	@Test
	public void testArrayEmpty() {
		assertNotNull(collection.getList());
		assertEquals(0, collection.getList().size());
		assertTrue(collection.getList().isEmpty());
	}



	@Test
	public void testArrayOneItem() {

		assertEquals(0, collection.getList().size());
		collection.arrayMethod("Test1");

		assertEquals("Test1", collection.getList().get(0));
		assertEquals(1, collection.getList().size());

	}

	@Test
	
	public void testArrayTwoItems() {

		assertEquals(0, collection.getList().size());

		collection.arrayMethod("Test1");
		assertEquals("Test1", collection.getList().get(0));
		assertEquals(1, collection.getList().size());

		collection.arrayMethod("Test2");
		assertEquals("Test2", collection.getList().get(1));
		assertEquals(2, collection.getList().size());
	}

	@Test
	
	public void testOverFlow() {
		assertEquals("Test1", collection.getList().get(0));
	}
}
