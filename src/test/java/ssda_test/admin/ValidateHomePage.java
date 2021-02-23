package ssda_test.admin;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AdminHomePage;
import pageObjects.LoginPage;
import resources.TestBase;

public class ValidateHomePage extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	AdminHomePage ahp;

	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeTest
	public void initialize() throws IOException{
		driver = initializeDriver();
		log.info("Driver is initialized");
	}

	@Test
	public void basePageNavigation() throws IOException {
		driver.get(prop.getProperty("url"));
		log.info("Navigated to Login page");
		lp = new LoginPage(driver);
		lp.getMobile().sendKeys(prop.getProperty("admin_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("admin_password"));
		lp.getLogin().click();
		ahp = new AdminHomePage(driver);
		Assert.assertTrue(ahp.getDashboard().isDisplayed());
		log.info("Successfully validated text message at dashboard");
	}

	@AfterTest
	public void teardown(){
		driver.quit();
	}
}
