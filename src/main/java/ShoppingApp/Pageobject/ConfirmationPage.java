package ShoppingApp.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;

public class ConfirmationPage extends AbstructComponents{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement thankyouText;
	
	public String verifyTheText() {
		String thankyouMsg = thankyouText.getText();
		return thankyouMsg;
	}
	}
