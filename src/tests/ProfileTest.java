package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test

	public void editProfileTest() throws InterruptedException {

		// Login
		this.driver.get(this.loginFormUrl);
		this.locationPopupPage.closeLocationDialog();
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationSistemPage.getLoginMsgText().contains("Login Successfull"),
				"Error, login unsuccessful ");

		// Edit profile
		driver.get(this.profilePageUrl);
		this.profilePage.updateUserInfo("Marko", "Markovic", "Adresa 22", "061232323", "18000", "United States",
				"Arizona", "Pine");
		Thread.sleep(2000);
		Assert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Setup Successful"),
				"Error, setup unsuccessful ");

		// Logout
		this.authPage.userLogout();
		Assert.assertTrue(this.notificationSistemPage.getLoginMsgText().contains("Logout Successfull"),
				"Error, logout unsuccessful ");

	}

	@Test

	public void changeProfileImage() throws AWTException, InterruptedException {

		// Login
		this.driver.get(this.loginFormUrl);
		this.locationPopupPage.closeLocationDialog();
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationSistemPage.getLoginMsgText().contains("Login Successfull"),
				"Error, login unsuccessful ");

		// Upload photo
		driver.get(this.profilePageUrl);
		this.profilePage.uploadPhoto();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Assert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Profile Image Uploaded Successfully"),
				"Error, Profile image in not uploaded ");
		this.notificationSistemPage.messageDissapear();

		// Remove photo
		this.profilePage.removePhoto();
		Thread.sleep(2000);
		Assert.assertTrue(this.notificationSistemPage.getSetupMsgText().contains("Profile Image Deleted Successfully"),
				"Error, Profile image is not deleted ");
		this.notificationSistemPage.messageDissapear();

		// Logout
		this.authPage.userLogout();
		Assert.assertTrue(this.notificationSistemPage.getLoginMsgText().contains("Logout Successfull"),
				"Error, logout unsuccessful ");

	}

}
