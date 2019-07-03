/**
 * 
 */
package mytest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author PD
 *
 */
public class DriverUtil {

	private static DriverUtil driverUtilInstance;

	// private constructor.
	private DriverUtil() {

		// Prevent form the reflection api.
		if (driverUtilInstance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public synchronized static DriverUtil getInstance() {
		 // if there is no instance available... create new one
		if (driverUtilInstance == null) {
			driverUtilInstance = new DriverUtil();
		}
		return driverUtilInstance;
	}

	public enum DriverType {
		CHROME, FIRE_FOX
	}

	/**
	 * 
	 * @param driver
	 *            type
	 * @return web driver
	 */
	public WebDriver setUpDriver(DriverType driver) {
		switch (driver) {
		case FIRE_FOX:
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\PD\\Documents\\Setup\\Gecko\\geckodriver.exe");
			return new FirefoxDriver();

		case CHROME:
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\PD\\Documents\\Setup\\Chrome\\chromedriver.exe");
			return new ChromeDriver();
		default:
			return null;
		}
	}

}
