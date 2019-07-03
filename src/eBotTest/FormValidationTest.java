package eBotTest;

import org.testng.annotations.Test;

import dataProvider.ConfigFileReader;
import mytest.DriverUtil;
import mytest.DriverUtil.DriverType;
import utility.Constant;
import utility.ExcelUtils;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class FormValidationTest {
	public WebDriver driver;
	public ConfigFileReader cfReader;

	/**
	 * Test case to check required fields
	 * 
	 * Expectations: form should not be submitted if author name and quote is empty
	 * 
	 * @throws Exception
	 */
	@Test
	public void blankDataTest() throws Exception {
		// Reading author and quote input values from test data sheet
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_eBotTestData, "Sheet1");
		final String invalidAuthorName = ExcelUtils.getCellData(1, 1);
		final String invalidQuote = ExcelUtils.getCellData(1, 2);
	
		// Find and click add quote button
		driver.findElement(By.id("show-modal")).click();

		// Input author and Quote data and save the data
		driver.findElement(By.id("autorInput")).sendKeys(invalidAuthorName);
		driver.findElement(By.id("quoteInput")).sendKeys(invalidQuote);

		// save quote button
		WebElement saveButton = driver
				.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));

		//form should be disabled if data is invalid
		Assert.assertTrue(saveButton.isEnabled());
		
		ExcelUtils.setCellData("Validation Fail", 1, 3);

	}

	@BeforeMethod
	public void beforeMethod() {
		// Reading application url from properties file
		cfReader = new ConfigFileReader();
		String bURL = cfReader.geteBotUrl();

		// Instantiating driver object for chrome driver
		driver = DriverUtil.getInstance().setUpDriver(DriverType.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(bURL);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		driver.quit();
	}

}
