package cats;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author PD
 *
 */
public class CatTest {

	private Cat cat = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("SettingUP");
		cat = new Cat();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("Tear DOWN");
		cat = null;
	}

	/**
	 * Test method for {@link Cat#sayMeow()}.
	 */
	@Test
	public void testSayMeow() {
		
		//Check not null
		Assert.assertNotNull(cat);
		
		//check not true
		Assert.assertTrue(true);
		
		// check if say meow
		System.out.println("Test Meow");
		Assert.assertEquals("Meow Meow", cat.sayMeow());
	}

	
	/**
	 * Test method for {@link Cat#sayPurr()}.
	 */
	@Test
	public void testSayPurr() {
		System.out.println("Test Purr");
		Assert.assertEquals("Purr", cat.sayPurr());
	}
}
