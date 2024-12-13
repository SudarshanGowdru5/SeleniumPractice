package ShoppingApp.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;

public class ProductPage extends AbstructComponents{

	WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css="#toast-container")
	WebElement toastMessage;
	
	
	By productBy=By.cssSelector("div b");
	By addToCartBtnBy=By.cssSelector(".btn.w-10.rounded");
	
	public void addProductToCart(String productName) {
		WebElement prod = products.stream().filter(product->product.findElement(productBy).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(addToCartBtnBy).click();
		waitForWebElementToDisappear(spinner);
		waitForWebElementToDisappear(toastMessage);
		
	}
}
