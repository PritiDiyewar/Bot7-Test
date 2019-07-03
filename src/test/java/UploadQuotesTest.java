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

public class UploadQuotesTest {

	// url for quotes
	private static final String BOT_7_URL = "https://www-5d108af8ed188621a8230dcb.recruit.eb7.io/";

	// sheet name for test data and result
	private static final String SHEET_NAME = "Sheet1";
	public WebDriver driver;
	public ConfigFileReader cfReader;

	@Test
	public void uploadQuotesFromDBTest() throws IOException {

		List<String> listOfQuotes = new ArrayList<>();

		// read from sheet
		listOfQuotes = ExcelUtils.getInstance().getQuotes(SHEET_NAME);

		// quotes from DB
		Assert.assertTrue(listOfQuotes.size() > 0);

		for (String quoteToUpload : listOfQuotes) {

			// Find and click add quote button
			driver.findElement(By.id("show-modal")).click();

			// Input author and Quote data and save the data
			driver.findElement(By.id("autorInput")).sendKeys("Gideon");
			driver.findElement(By.id("quoteInput")).sendKeys(quoteToUpload);

			// save quote button
			WebElement saveButton = driver
					.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));

			saveButton.click();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@BeforeMethod
	public void beforeMethod() {
		// Instantiating driver object for chrome driver
		driver = DriverUtil.getInstance().setUpDriver(DriverType.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(BOT_7_URL);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		driver.quit();
	}

}
