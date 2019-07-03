import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cats.CatTest;
import dogs.DogTest;

@RunWith(Suite.class)
@SuiteClasses({ DogTest.class, CatTest.class })
public class AllTests {

}
