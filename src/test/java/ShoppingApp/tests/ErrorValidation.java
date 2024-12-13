package ShoppingApp.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import ShoppingApp.Pageobject.CartPage;
import ShoppingApp.Pageobject.LoginPage;
import ShoppingApp.Pageobject.ProductPage;
import ShoppingApp.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test(groups = {"ErrorValidate"})
	public void errorMessageCheck() {
		loginPage.loginToApplication("ss@gmail.com", "snnfd");
		String errorMessage = loginPage.errorMessage();
		assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidation() throws Exception {
		String email="ss05@gmail.com";
		String pwd="Suda@123";
		String product = "IPHONE 13 PRO";
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage = loginPage.loginToApplication(email, pwd);
		productPage.addProductToCart(product);
		CartPage cartPage = productPage.navigateToCart();
		boolean match = cartPage.verifyProductInTheCart("Zara");
		assertFalse(match);
		
	}
}
