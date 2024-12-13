package ShoppingApp.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExternalReportNg {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Test Automation result");
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports event = new ExtentReports();
		event.attachReporter(report);
		event.setSystemInfo("Tester","Sudarshan");
		return event;
	}
}
