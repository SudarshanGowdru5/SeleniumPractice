package ShoppingApp.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;

public class OrdersPage extends AbstructComponents{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css="tr td:nth-child(3)")
	WebElement productName;
	
	public String verifytheProductinorders() {
		waitForWebElementToAppear(productName);
		return productName.getText();
	}
}
