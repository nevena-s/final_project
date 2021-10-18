package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	// Getters

	public WebElement getNameField() {
		return this.driver.findElement(By.xpath("//*[contains(@class,'after-arrow')]"));

	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.xpath("//*[contains(text(),'My Account')]"));

	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));

	}

	// Methods

	public void userLogout() {

		this.getNameField().click();
		this.getLogout().click();
	}

}
