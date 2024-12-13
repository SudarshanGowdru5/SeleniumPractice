package ShoppingApp.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ShoppingApp.Pageobject.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws FileNotFoundException, IOException  
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("./src/main/java/ShoppingApp/Resources/GlobalData.properties"));

		String browserName = System.getProperty("Browser")!= null ? System.getProperty("Browser") : prop.getProperty("Browser");


		if(browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		}else if(browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws FileNotFoundException, IOException  {
		driver=initializeDriver();
		loginPage=new LoginPage(driver);
		loginPage.get();
		return loginPage;

	}

	@AfterMethod (alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}

	public List<HashMap<String, String>> getDataFromJason(String path) throws IOException {

		//read json to string
		String jasonContent = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);
		//String to HashMap- Jackson Databind
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String, String>> data = map.readValue(jasonContent, new TypeReference<List<HashMap<String, String>>>(){});
		//mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		return data;
	}

	public String takeScreenShot(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File path = new File(System.getProperty("user.dir")+"//Reports//"+TestCaseName+".png");
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, path);
		return System.getProperty("user.dir")+"//Reports//"+TestCaseName+".png";

	}
}
