package ShoppingApp.AbstructComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ShoppingApp.Pageobject.CartPage;
import ShoppingApp.Pageobject.OrdersPage;

public class AbstructComponents {

	WebDriver Driver;
	
	public AbstructComponents(WebDriver driver) {
		this.Driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement myorders;
	
	

	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(Driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForWebElementToDisappear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(Driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForWebElementToClickable(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(Driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	public void waitForaMovement() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	public CartPage navigateToCart() {
		cart.click();
		CartPage cartPage = new CartPage(Driver);
		return cartPage;
		
	}

	public OrdersPage navigateToOrders() {
		myorders.click();
		return new OrdersPage(Driver);
}
}
