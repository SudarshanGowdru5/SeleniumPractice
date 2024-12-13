package ShoppingApp.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;



public class LoginPage extends AbstructComponents{

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductPage loginToApplication(String emailId, String pwd) {
		//waitForWebElementToAppear(userEmail);
		userEmail.sendKeys(emailId);
		passwordEle.sendKeys(pwd);
		loginBtn.click();
		ProductPage productPage=new ProductPage(driver);
		return productPage;
	}
	public void get() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String errorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
}
