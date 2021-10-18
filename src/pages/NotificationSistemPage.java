package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	public NotificationSistemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	// Getters

	public WebElement getLoginMessageElement() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));

	}

	public String getLoginMsgText() {
		return this.getLoginMessageElement().getText();
	}

	public WebElement getSetupMsgElement() {
		return this.driver.findElement(By.className("content"));

	}

	public String getSetupMsgText() {
		return this.getSetupMsgElement().getText();

	}

	public WebElement getMealMsgElementFirst() {
		return this.driver.findElement(By.className("div_error"));

	}

	public WebElement getMealMsgElementSecond() {
		return this.driver.findElement(By.xpath("//*[@class ='div_error']/ul/li"));

	}

	public String getMealMsgTextFirst() {
		return this.getMealMsgElementFirst().getText();

	}

	public String getMealMsgTextSecond() {
		return this.getMealMsgElementSecond().getText();

	}

	// Methods

	public void messageDissapear() {
		waiter.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

}
