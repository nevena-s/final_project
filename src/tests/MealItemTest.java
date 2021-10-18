package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	@Test

	public void addMealToCart() throws InterruptedException {

		// Add to cart without location
		driver.get(this.mealPageUrlLobster);
		this.locationPopupPage.closeLocationDialog();
		this.mealPage.addToCart("3");
		Assert.assertTrue(this.notificationSistemPage.getMealMsgTextFirst().contains("The Following Errors Occurred:"),
				"Error, Error message does not appear");
		Assert.assertTrue(this.notificationSistemPage.getMealMsgTextSecond().contains("Please Select Location"),
				"Error, 'Please select location'message does not appear");
		this.notificationSistemPage.messageDissapear();

		// Set location
		this.locationPopupPage.getHeaderLocation().click();
		this.locationPopupPage.setLocation("City Center - Albany");
		Thread.sleep(2000);

		// Add to cart
		this.mealPage.addToCart("3");
		Thread.sleep(3000);
		Assert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Meal Added To Cart"),
				"Error, meal is not added to cart");
		Thread.sleep(3000);

	}

	@Test

	public void AddMealToFavorite() throws InterruptedException {

		// Add to favorites without login
		driver.get(this.mealPageUrlLobster);
		this.locationPopupPage.closeLocationDialog();
		this.mealPage.addToFavourites();
		Thread.sleep(2000);
		Assert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Please login first!"),
				"Error, 'please login first' message does not appear");

		// Login
		driver.get(this.loginFormUrl);
		this.loginPage.login(email, password);

		// Add to favorites
		driver.get(this.mealPageUrlLobster);
		this.mealPage.addToFavourites();
		Assert.assertTrue(
				this.notificationSistemPage.getSetupMsgText().contains("Product has been added to your favorites"),
				"Error, product has not been added to your favorites");

	}

	@Test

	public void clearCart() throws IOException, InterruptedException {

		// Set location
		driver.get(this.mealsPageUrl);
		this.locationPopupPage.setLocation("City Center - Albany");

		// Add to cart form excel file
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet mealsSheet = wb.getSheet("Meals");

		for (int i = 1; i <= mealsSheet.getLastRowNum(); i++) {
			String url = mealsSheet.getRow(i).getCell(0).getStringCellValue();
			Thread.sleep(2000);
			this.driver.get(url);
			Thread.sleep(2000);
			this.mealPage.addToCart("3");

			Thread.sleep(2000);
			softAssert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Meal Added To Cart"),
					"Error, meal is not added to cart");
		}

		// Clear cart
		Thread.sleep(2000);
		this.cartSummaryPage.clearAll();
		Thread.sleep(3000);
		this.softAssert.assertTrue(
				this.notificationSistemPage.getSetupMsgText().contains("All meals removed from Cart successfully"),
				"Error, meals are not removed from the cart");
		this.softAssert.assertAll();
		

	}

}
