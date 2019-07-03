package mytest;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import dataProvider.ConfigFileReader;
import mytest.DriverUtil.DriverType;
import utility.Constant;
import utility.ExcelUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class PracticeTest {

	public WebDriver driver;
	public ConfigFileReader cfReader;
	

	@Test
	public void main() throws Exception{			
		
		
		String currentURL = driver.getCurrentUrl();
		if(currentURL.contentEquals("https://www.linkedin.com/uas/login-submit")) {
		ExcelUtils.setCellData("Fail", 1, 3);
		}
		else {
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"extended-nav\"]/div/nav/ul"));
		
		for(WebElement ele:list){
			if(ele.getAttribute("id").contains("profile-nav-item")) {
				ele.click();
				Select logOut = new Select(ele);
				logOut.selectByVisibleText("Sign out");
				ExcelUtils.setCellData("Pass", 1, 3);
				break;
			}
		}
		}		
		
	}

	
	@BeforeMethod
	public void beforeMethod() throws Exception{
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		final String sUsername = ExcelUtils.getCellData(1, 1);
		final String sPassword = ExcelUtils.getCellData(1, 2);		
		cfReader = new ConfigFileReader();
		String baseUrl = cfReader.getApplicataionUrl();
		driver=	DriverUtil.getInstance().setUpDriver(DriverType.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.xpath("/html/body/nav/a[3]")).click();
			
		driver.findElement(By.id("username")).sendKeys(sUsername);
		driver.findElement(By.id("password")).sendKeys(sPassword);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		driver.findElement(By.xpath("/html/body/div/main/div/form/div[3]/button")).click();
		FileHandler.copy(src, new File("C:/Users/PD/eclipse-workspace/TestApp/src/screenshots/"
				+System.currentTimeMillis()+".png"));
		
	}

	@AfterMethod
	public void afterMethod()throws Exception {
		driver.quit();
	}

}
