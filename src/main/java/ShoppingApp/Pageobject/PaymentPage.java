package ShoppingApp.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;

public class PaymentPage extends AbstructComponents{

	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countrySelect;
	
	@FindBy(css="button span")
	List<WebElement> countriesEle;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;
	
	// By countriesBy=By.cssSelector(".ta-results.list-group.ng-star-inserted");
	
	public void selectCountry(String myCountry) {
		waitForWebElementToAppear(countrySelect);
		countrySelect.sendKeys("Ind");
		WebElement cntry = countriesEle.stream().filter(country->country.getText().trim().equalsIgnoreCase(myCountry)).findFirst().orElse(null);
		cntry.click();	
		
		
	}
	
	public ConfirmationPage placeOrder() throws InterruptedException {
		 waitForWebElementToClickable(placeOrder);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Actions a=new Actions(driver);
		a.click().perform();
		waitForaMovement();
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	
}
