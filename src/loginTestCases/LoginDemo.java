package loginTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginDemo {
	
	WebDriver driver;
	String[][] data1 =null;
	@DataProvider(name ="loginData")
	
	public String[][] logindata() throws BiffException, IOException {
		data1 = GetExcelData();
		return data1;
	}
	
	
	
	public String[][] GetExcelData() throws BiffException, IOException
	{
		FileInputStream excel = new FileInputStream("C:\\Users\\manik\\OneDrive\\Desktop\\Mani\\Automation\\Selenium Data file\\LoginDetails.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);// We can use the sheet name or Index as well.
		int rowCount =sheet.getRows();
		int colCount= sheet.getColumns();
		
		String testdata[][] = new String[rowCount-1][colCount];
		for (int i=1 ;i<rowCount ; i++ )
		{
			for (int j=0 ; j<colCount ;j++)
			{
				testdata[i-1][j]=sheet.getCell(j,i).getContents();
			}
		}
		return testdata;
	}
	
	@Test@DataProvider(name ="loginData")
	public void Login(String Uname, String Pwd)
	{
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(Uname);
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(Pwd);
	}
	
	@BeforeTest
	public void LaunchBrowser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\\\Program Files\\\\Google\\\\Chrome\\\\chromedriver.exe");
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		
	}
	
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
	
}
