package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.sun.jmx.snmp.Timestamp;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected SoftAssert softAssert;
	
	// Pages
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;

	// URLs
	protected String baseUrl = "http://demo.yo-meals.com";
	protected String loginFormUrl = baseUrl + "/guest-user/login-form";
	protected String profilePageUrl = baseUrl + "/member/profile";
	protected String mealPageUrlLobster = baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo";
	protected String mealsPageUrl = baseUrl + "/meals";

	// Login credentials
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";

	@BeforeMethod

	public void beforeMethod() throws AWTException {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		waiter = new WebDriverWait(driver, 10);
		softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Pages
		locationPopupPage = new LocationPopupPage(driver, waiter, js);
		loginPage = new LoginPage(driver, waiter, js);
		notificationSistemPage = new NotificationSistemPage(driver, waiter, js);
		profilePage = new ProfilePage(driver, waiter, js);
		authPage = new AuthPage(driver, waiter, js);
		mealPage = new MealPage(driver, waiter, js);
		cartSummaryPage = new CartSummaryPage(driver, waiter, js);

	}

	@AfterMethod

	public void afterMethod(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + timestamp() + ".png"));
				System.out.println("Screenshot taken");

			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		driver.quit();
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}
