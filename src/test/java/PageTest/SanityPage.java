package PageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SanityPage {
	
WebDriver driver;
	
	@FindBy (id ="username")  
	WebElement username;
	
	@FindBy (id = "password")
	WebElement password;
	
	@FindBy (xpath = "//button[@id='submit']")
	WebElement button;

	@FindBy (tagName = "h1")
	WebElement successMessage;
	
	@FindBy (xpath = "//a[normalize-space()='Log out']")
	WebElement logout;
	
	@FindBy (id = "error")
	WebElement errorMessage;
	
	public SanityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void EnterUsername(String text) {
		username.clear();
		username.sendKeys(text);
	}
	public void EnterPassword(String text) {
		password.clear();
		password.sendKeys(text);
	}
	public void ClickButton() {
		button.click();
	}
	public void ExpectedText() {
		String title = "Logged In Successfully";
		String Expected = successMessage.getText();
		Assert.assertEquals(title, Expected);
		System.out.println("Assertion passed");
	}
	public void clickLogout() {
		logout.click();
	}
	public void ExpectedError( ) {
		String Error1 = "Your username is invalid!";
		String Error2 = errorMessage.getText();
		Assert.assertEquals(Error1, Error2);
		System.out.println("Assertion passed");
	}
	public void ExpectedError1( ) {
		String Error1 = "Your password is invalid!";
		String Error2 = errorMessage.getText();
		Assert.assertEquals(Error1, Error2);
		System.out.println("Assertion passed");
	}

}
