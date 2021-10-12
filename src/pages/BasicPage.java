package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;

	public BasicPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {

		this.driver = driver;
		this.waiter = waiter;
		this.js = js;
	}

	public boolean elementExist(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements.size() > 0;
	}

}
