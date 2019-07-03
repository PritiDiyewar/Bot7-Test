/**
 * 
 */
package dogs;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;	
/**
 * @author PD
 *
 */

public class DogTest {
	private Dog dog = null;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("SettingUP");
		dog = new Dog();
	}
	
	@After
	public void tearDown() throws Exception{
		System.out.println("TearDown");
		dog = null;
	}
	
	/**
	  * Test method for {@Link Dog#sayBow()}.
	  */
	@SuppressWarnings("deprecation")
	@Test
	 public void testSayBow() {
		//Check Not Null
		Assert.assertNotNull(dog);
		Assert.assertEquals("Bow Bow", dog.sayBow());
	} 
	
	@Ignore("Dog is not hungry")	
	@Test
	public void testFeedFood() {
		//check if dog is hungry.
		Assert.assertEquals((2*2), 4);
		System.out.println(dog.feedFood());
	}
}

