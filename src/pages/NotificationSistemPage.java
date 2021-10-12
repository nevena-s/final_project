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

//	get metodu za element koji prikazuje poruku
//	//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
//	metodu koja vraća poruku koja se nalazi u obaveštenju
//	metodu koja čeka da obaveštenje nestane
//	čeka se da element //*[contains(@class, 'system_message')]
//	za atribut style dobije vrednost  "display: none;"

	public WebElement getMessageElement() {

		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));

	}

	public String getMsgText() {

		return this.getMessageElement().getText();
	}

	public void messageDissapear() {

		waiter.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

}
