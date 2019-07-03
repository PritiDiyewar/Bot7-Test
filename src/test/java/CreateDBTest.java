package test.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.utility.ConfigFileReader;
import test.utility.DriverUtil;
import test.utility.DriverUtil.DriverType;
import test.utility.ExcelUtils;

public class CreateDBTest {

	// url for quotes
	private static final String QUOTES_URL = "https://agoldoffish.wordpress.com/criminal-minds-opening-and-closing-quotes/";

	// sheet name for test data and result
	private static final String SHEET_NAME = "Sheet1";
	public WebDriver driver;
	public ConfigFileReader cfReader;

	@Test
	public void insertQuotesIntoDBTest() throws IOException {

		List<WebElement> quoteList = driver.findElements(By.xpath("//div[@class='entry-content']/p"));

		List<String> listOfQuotes = new ArrayList<>();

		for (WebElement webElement : quoteList) {
			String text = webElement.getText();
			if (text.contains("Gideon")) {
				String str = "Gideon: ";
				listOfQuotes.add(text.substring(str.length()));

			}
		}

		// should have some quotes
		Assert.assertTrue(listOfQuotes.size() > 0);

		// insert into sheets
		ExcelUtils.getInstance().setQuotes(listOfQuotes, SHEET_NAME);

	}

	@Test
	public void readQuotesFromDBTest() throws IOException {

		List<String> listOfQuotes = new ArrayList<>();

		// read from sheet
		listOfQuotes = ExcelUtils.getInstance().getQuotes(SHEET_NAME);

		// quotes from DB
		Assert.assertTrue(listOfQuotes.size() > 0);
	}

	@BeforeMethod
	public void beforeMethod() {
		// Instantiating driver object for chrome driver
		driver = DriverUtil.getInstance().setUpDriver(DriverType.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(QUOTES_URL);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		driver.quit();
	}

}
