package ShoppingApp.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstructComponents.AbstructComponents;

public class CartPage extends AbstructComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection']/child::h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//div[contains(@class,'ng-star-inserted')]/child::ul/child::li/child::button")
	WebElement checkoutBtn;
	
	@FindBy(xpath="//h1[@class='a-spacing-small']")
	WebElement SignIntext;
	
	By cartProductBy=By.xpath("//div[@class='cartSection']/child::h3");
	
	public boolean verifyProductInTheCart(String productName) {
		waitForWebElementToAppear(driver.findElement(cartProductBy));
	//	List<WebElement> cartProducts = driver.findElements(By.cssSelector("div h3"));
		//System.out.println(cartProducts.get(1).getText());
		boolean match = cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
	public PaymentPage navigateToPayments() {
		checkoutBtn.click();
		 
		
	return new PaymentPage(driver);
	}
	
}
