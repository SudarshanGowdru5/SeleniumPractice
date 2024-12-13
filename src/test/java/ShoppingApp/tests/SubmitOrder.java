package ShoppingApp.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import ShoppingApp.Pageobject.CartPage;
import ShoppingApp.Pageobject.ConfirmationPage;
import ShoppingApp.Pageobject.LoginPage;
import ShoppingApp.Pageobject.OrdersPage;
import ShoppingApp.Pageobject.PaymentPage;
import ShoppingApp.Pageobject.ProductPage;
import ShoppingApp.TestComponents.BaseTest;
import ShoppingApp.TestComponents.RetryTest;

public class SubmitOrder extends BaseTest{

	String email="ss05@gmail.com";
	String pwd="Suda@123";
	String productCheck;
	String myCountry="India";
	String thankyouMsg="Thankyou for the order.";
	
	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException{
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage = loginPage.loginToApplication(input.get("email"), input.get("pwd"));
		productPage.addProductToCart(input.get("product"));
		CartPage cartPage = productPage.navigateToCart();
		boolean match = cartPage.verifyProductInTheCart(input.get("product"));
		assertTrue(match);
		PaymentPage paymentPage = cartPage.navigateToPayments();
		paymentPage.selectCountry(myCountry);
		ConfirmationPage confirmationPage=paymentPage.placeOrder();
		String thankyouText = confirmationPage.verifyTheText();
		assertEquals(thankyouText.trim(), thankyouMsg.toUpperCase());
		productCheck=input.get("product");
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, retryAnalyzer = RetryTest.class)
	public void checkOrder() {
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage = loginPage.loginToApplication(email, pwd);
		OrdersPage ordersPage = productPage.navigateToOrders();
		String productName = ordersPage.verifytheProductinorders();
		assertEquals(productCheck, productName);
		
		
	}
	@DataProvider
	public Object[][] getData() throws IOException{
		//System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"
		List<HashMap<String, String>> input = getDataFromJason("./src/test/java/ShoppingApp/Data/purchase.jason");
		return  new Object[][] {{input.get(0)},{input.get(1)}};
		
		
//		HashMap<String, String> input1=new HashMap<String, String>();
//		input1.put("email", "ss05@gmail.com");
//		input1.put("pwd", "Suda@123");
//		input1.put("product", "IPHONE 13 PRO");
//		HashMap<String, String> input2=new HashMap<String, String>();
//		input2.put("email", "sudi06@gmail.com");
//		input2.put("pwd", "Suda@123");
//		input2.put("product", "ADIDAS ORIGINAL");
		
	}
	
//	@DataProvider
//	public Object[][] data() {
//		
//		return new Object[][] {{"ss05@gmail.com","Suda@123","IPHONE 13 PRO"},{"sudi06@gmail.com","Suda@123","ADIDAS ORIGINAL"}};
//	}
}
