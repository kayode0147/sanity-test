package kay.MavenTestWithDifferentBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import PageTest.SanityPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SanityTest {
	
	WebDriver driver;
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	
	FirefoxOptions firefoxoptions = new FirefoxOptions();
	ChromeOptions chromeoptions = new ChromeOptions();
	EdgeOptions edgeoptions = new EdgeOptions();
	
	
	@Parameters("browserName")
	@BeforeTest
	public void setup(String browserName)
	{
		extent.attachReporter(spark);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.out.println("Browser name is : "+browserName);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.out.println("Browser name is : "+browserName);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Browser name is : "+browserName);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}	
		ExtentTest test = extent.createTest("launch browser and website").assignAuthor("bade")
				.assignCategory("smoke test").assignDevice("Chrome");
		
			driver.get("https://practicetestautomation.com/practice-test-login/");
			
			test.log(Status.PASS, "user launched website");
			test.pass("user launched website verifyed");
			

	}
	@Test
	public void PositiveloginTest() throws InterruptedException 
	{
		
		SanityPage login = new SanityPage(driver);
		
		ExtentTest test = extent.createTest("Verify login with valid data").assignAuthor("bade")
				.assignCategory("Sanity test").assignDevice("Chrome");
		
		// Enter username
		login.EnterUsername("student");
	
		// Enter password
		login.EnterPassword("Password123");
		Thread.sleep(2000);
		// Click submit button
		login.ClickButton();
		Thread.sleep(2000);
		// Verify the new page URL 
		String URL = driver.getCurrentUrl();
		System.out.println(" "+URL);
		test.info("https://practicetestautomation.com/logged-in-successfully/");
		
		// Verify successful text
		login.ExpectedText();
		test.pass("user logged into application");
		
		// Click log out button
		login.clickLogout();
		
	}
	
	@Test
	public void NegativeUsernameTest() throws InterruptedException 
	{
		SanityPage login = new SanityPage(driver);
		
		ExtentTest test = extent.createTest("Verify login with invalid Username").assignAuthor("bade")
				.assignCategory("Sanity test").assignDevice("firefox");
		
		// Enter username
		login.EnterUsername("incorrectUser");
		
		// Enter password
		login.EnterPassword("Password123");
		Thread.sleep(2000);
		// Click submit button
		login.ClickButton();
		
		test.pass("Unable to logged in due to invalid Username");
		
		// Verify Error message display
		login.ExpectedError();
		test.info("Your username is invalid!");
		
	}
	
	@Test
	public void NegativePasswordTest() throws InterruptedException 
	{
		SanityPage login = new SanityPage(driver);
		
		ExtentTest test = extent.createTest("Verify login with invalid Password").assignAuthor("bade")
				.assignCategory("Sanity test").assignDevice("edge");
		
		// Enter username
		login.EnterUsername("student");
		
		// Enter password
		login.EnterPassword("incorrectPassword");
		Thread.sleep(2000);
		// Click submit button
		login.ClickButton();
		
		test.pass("Unable to logged in due to invalid password");
		
		// Verify Error message display
		login.ExpectedError1();
		test.info("Your password is invalid!");
		
	}
	
	@AfterTest
	public void close() {
		extent.flush();
		driver.close();
	}
	
	

}
