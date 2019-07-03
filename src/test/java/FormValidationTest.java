package test.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

/**
 * Form validation
 * 
 * @author PD
 *
 */
public class FormValidationTest {

	private static final int RESULT_COLUMN = 3;
	private static final int QUOTE_COLUMN = 2;
	private static final int AUTHOR_COLUMN = 1;
	// sheet name for test data and result
	private static final String SHEET_NAME = "Sheet1";
	public WebDriver driver;
	public ConfigFileReader cfReader;

	@BeforeMethod
	public void beforeMethod() {
		// Reading application url from properties file
		cfReader = new test.utility.ConfigFileReader();
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

	/**
	 * Test case to check required fields
	 * 
	 * Expectations: form should not be submitted if author name and quote is empty
	 * 
	 * @throws Exception
	 */
	@Test
	public void emptyInputTest() throws Exception {
		// Reading author and quote input values from test data sheet
		final String invalidAuthorName = ExcelUtils.getInstance().getCellData(1, AUTHOR_COLUMN, SHEET_NAME);
		final String invalidQuote = ExcelUtils.getInstance().getCellData(1, QUOTE_COLUMN, SHEET_NAME);

		// Find and click add quote button
		driver.findElement(By.id("show-modal")).click();

		// Input author and Quote data and save the data
		driver.findElement(By.id("autorInput")).sendKeys(invalidAuthorName);
		driver.findElement(By.id("quoteInput")).sendKeys(invalidQuote);

		// save quote button
		WebElement saveButton = driver
				.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));

		saveButton.click();
		//check if alert present
		boolean alertPresent = isAlertPresent();
		if (alertPresent) {
			//test passed
			ExcelUtils.getInstance().setCellData("Validation Pass", 1, RESULT_COLUMN, SHEET_NAME);
			} else {
			//test failed
			ExcelUtils.getInstance().setCellData("Validation Fail", 1, RESULT_COLUMN, SHEET_NAME);

			}
			// assert this test case
			Assert.assertTrue(alertPresent, "Author name or Quote text cannot be empty");

	}

	/**
	 * Test case to check required fields
	 * 
	 * Expectations: form should not be submitted if quote length is less than 10
	 * 
	 * @throws Exception
	 */
	@Test
	public void quoteMinLengthTest() throws Exception {
		// Reading author and quote input values from test data sheet
		final String invalidAuthorName = ExcelUtils.getInstance().getCellData(2, AUTHOR_COLUMN, SHEET_NAME);
		final String invalidQuote = ExcelUtils.getInstance().getCellData(2, QUOTE_COLUMN, SHEET_NAME);

		// checking with quote less than 10 characters
		Assert.assertTrue(invalidQuote.length() < 10, "Wrong text Data");

		// Find and click add quote button
		driver.findElement(By.id("show-modal")).click();

		// Input author and Quote data and save the data
		driver.findElement(By.id("autorInput")).sendKeys(invalidAuthorName);
		driver.findElement(By.id("quoteInput")).sendKeys(invalidQuote);
		// save quote button
		WebElement saveButton = driver
						.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));
		saveButton.click();


		//check if alert present
		boolean alertPresent = isAlertPresent();
				
		if (alertPresent) {
		//test passed
		ExcelUtils.getInstance().setCellData("Validation Pass", 2, RESULT_COLUMN, SHEET_NAME);
		} else {
		//test failed
		ExcelUtils.getInstance().setCellData("Validation Fail", 2, RESULT_COLUMN, SHEET_NAME);

		}
		// assert this test case
		Assert.assertTrue(alertPresent, "Quote length should be more than 8");

	}

	
	/**
	 * Test case to check required fields
	 * 
	 * Expectations: form should not be submitted if quote length is more than 200 chars
	 * 
	 * @throws Exception
	 */
	@Test
	public void quoteMaxLengthTest() throws Exception {
		// Reading author and quote input values from test data sheet
		final String invalidAuthorName = ExcelUtils.getInstance().getCellData(3, AUTHOR_COLUMN, SHEET_NAME);
		final String invalidQuote = ExcelUtils.getInstance().getCellData(3, QUOTE_COLUMN, SHEET_NAME);

		// checking with quote less than 10 characters
		Assert.assertTrue(invalidQuote.length() > 200, "Wrong text Data");

		// Find and click add quote button
		driver.findElement(By.id("show-modal")).click();

		// Input author and Quote data and save the data
		driver.findElement(By.id("autorInput")).sendKeys(invalidAuthorName);
		driver.findElement(By.id("quoteInput")).sendKeys(invalidQuote);
		
		// save quote button
		WebElement saveButton = driver
						.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));
		saveButton.click();

		//check if alert present
		boolean alertPresent = isAlertPresent();
		

		if (alertPresent) {
			//test passed
			ExcelUtils.getInstance().setCellData("Validation Pass", 3, RESULT_COLUMN, SHEET_NAME);
		} else {
			//test failed
			ExcelUtils.getInstance().setCellData("Validation Fail", 3, RESULT_COLUMN, SHEET_NAME);

		}
		// assert this test case
		Assert.assertTrue(alertPresent, "Quote length should be less than 200");
	}
	
	@Test
		public void cleanInputTest() throws Exception {
		// Reading author and quote input values from test data sheet
		final String invalidAuthorName = ExcelUtils.getInstance().getCellData(4, AUTHOR_COLUMN, SHEET_NAME);
		final String invalidQuote = ExcelUtils.getInstance().getCellData(1, QUOTE_COLUMN, SHEET_NAME);

		// Find and click add quote button
		driver.findElement(By.id("show-modal")).click();

		// Input author and Quote data and save the data
		driver.findElement(By.id("autorInput")).sendKeys(invalidAuthorName);
		driver.findElement(By.id("quoteInput")).sendKeys(invalidQuote);

		// save quote button
		WebElement saveButton = driver
				.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div[3]/button[1]"));

		saveButton.click();
		//check if alert present
		boolean alertPresent = isAlertPresent();
		if (alertPresent) {
			//test passed
			ExcelUtils.getInstance().setCellData("Validation Fail", 3, RESULT_COLUMN, SHEET_NAME);
		} else {
			//test failed
			ExcelUtils.getInstance().setCellData("Validation Pass", 3, RESULT_COLUMN, SHEET_NAME);

		}
		// assert this test case
		Assert.assertFalse(alertPresent, "Correct Input");

		ExcelUtils.getInstance().setCellData("Quote added", 4, RESULT_COLUMN, SHEET_NAME);
	}
	
	/**
	 * Check if alert present
	 * 
	 * @return
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException ex) {
			System.out.println("ERROR: " + ex.getLocalizedMessage());
			return false;
		}
	}
}
