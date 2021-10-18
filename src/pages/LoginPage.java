package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	// Getters

	public WebElement getUsername() {
		return this.driver.findElement(By.name("username"));

	}

	public WebElement getPassword() {
		return this.driver.findElement(By.name("password"));

	}

	public WebElement getRememberMe() {
		return this.driver.findElement(By.name("remember_me"));

	}

	public WebElement getSubmitBtn() {
		return this.driver.findElement(By.name("btn_submit"));

	}

	// Methods

	public void login(String username, String password) {

		this.getUsername().clear();
		this.getUsername().sendKeys(username);
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
		this.getSubmitBtn().click();

	}

}
