package ShoppingApp.Stepdefination;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ShoppingApp.Pageobject.CartPage;
import ShoppingApp.Pageobject.ConfirmationPage;
import ShoppingApp.Pageobject.LoginPage;
import ShoppingApp.Pageobject.PaymentPage;
import ShoppingApp.Pageobject.ProductPage;
import ShoppingApp.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinationImp extends BaseTest{

	public LoginPage loginPage;
	public ProductPage productPage;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	@Given("I have landed on ecommerce page")
	public void I_have_landed_on_ecommerce_page() throws FileNotFoundException, IOException {
		
		loginPage = launchApplication();
	}
	
	@Given("^Login to the application with username (.+) and password (.+)$")
	public void Login_to_the_application_with_username_and_password(String userName, String password) {
		
		 productPage = loginPage.loginToApplication(userName, password);
		
	}
	
	@When ("^Add the product (.+) to cart$")
	public void Add_the_product_to_cart(String productName) {
		
		productPage.addProductToCart(productName);
		 
	}
	
	@When("^checkout the product(.+) and submit the order$")
	public void checkout_the_product_and_submit_the_order(String productName) throws InterruptedException {
		cartPage = productPage.navigateToCart();
		boolean match = cartPage.verifyProductInTheCart(productName);
		assertTrue(match);
		PaymentPage paymentPage = cartPage.navigateToPayments();
		paymentPage.selectCountry("India");
		 confirmationPage=paymentPage.placeOrder();
	}
	
	@Then("^\"([^\"]*)\" is displayed on the confirmation Page")
	public void is_displayed_on_the_confirmation_Page(String thankyouMsg) {
		String thankyouText = confirmationPage.verifyTheText();
		assertEquals(thankyouText.trim(), thankyouMsg.toUpperCase());
		driver.close();
	}
	@Then("^\"([^\"]*)\" error message is displayed")
	public void error_message_is_displayed(String errorMsg) {
		assertEquals(errorMsg, "Incorrect email or password.");
		driver.close();
	}
	
}
