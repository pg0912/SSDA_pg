package ssda_test.aggregator;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.AggregatorHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

import org.testng.Assert;




public class TC01_AggrLoginTest extends TestBase{
	
	
		public WebDriver driver;
		LoginPage lp;
		AggregatorHomePage ahp;
		Utilities util;
		public static Logger log = LogManager.getLogger(TestBase.class.getName());

		@BeforeTest
		public void initialize() throws IOException{
			driver = initializeDriver();
			log.info("Driver is initialized");
			util= new Utilities();
			ahp = new AggregatorHomePage(driver);
			lp = new LoginPage(driver);
		}
		@Test(priority=1)
		public void testWithMobileNumberLessThanTenDigits() {
			driver.get(prop.getProperty("url"));
			log.info("Validate if Login button is disabled when mobile number is less than 10 digits");
			
			lp.getMobile().sendKeys("123456789");
			lp.getPassword().sendKeys("password");
			log.info("Enter Mobile number less than 10 digits and verify Login button");
			Assert.assertFalse(lp.getLogin().isEnabled());
			log.info("Login Button is disabled as User entered less than 10 digits in Mobile Number");
		}
		
		@Test(priority=2)
		public void testWithMobileNumberMoreThanTenDigits() {
			driver.get(prop.getProperty("url"));
			log.info("Validate if Login button is disabled when mobile number is more than 10 digits");
			lp.getMobile().sendKeys("12345678912");
			lp.getPassword().sendKeys("password");
			log.info("Enter Mobile number more than 10 digits and verify Login button");
			Assert.assertFalse(lp.getLogin().isEnabled());
			log.info("Login Button is disabled as User entered more than 10 digits in Mobile Number");
		}
		
		@Test(priority=3)
		public void testWithMobileNumberContainsAlphabets() {
			driver.get(prop.getProperty("url"));
			log.info("Validate if Login button is disabled when mobile number contains alphabets");
			lp.getMobile().sendKeys("12c45a6789");
			lp.getPassword().sendKeys("password");
			log.info("Enter Mobile number with alphabets and verify Login button");
			Assert.assertFalse(lp.getLogin().isEnabled());
			log.info("Login Button is disabled as User entered Mobile number with alphabets as input");
		}
		
		@Test(priority=4)
		public void testAggregatorLoginWithInvalidCredentials() {
			driver.get(prop.getProperty("url"));
			log.info("Validate login feature with invalid credentials");
			lp.getMobile().sendKeys("1212121212");
			lp.getPassword().sendKeys("password");
			log.info("Enter Invalid customer credentials and click on Login button");
			lp.getLogin().click();
			util.waitForElementToBeVisible(driver, lp.getAlert(),30);
			Assert.assertTrue(lp.getAlert().isDisplayed());
			Assert.assertTrue(lp.getAlert().getText().contains("Invalid (username or password)"));
			util.waitForElementToBeInvisible(driver, lp.getAlert(), 5);
			log.info("User entered invalid (username or password) or User is no longer active");
		}

		@Test(priority=5)
		public void testAggregatorLoginWithValidCredentials() {
			driver.get(prop.getProperty("url"));
			log.info("Validate login feature with valid credentials");
			lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
			lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
			log.info("Enter valid aggregator credentials and click on Login button");
			lp.getLogin().click();
			
			util.waitForElementToBeVisible(driver, ahp.getClearFiltersButton(),5);
			
			//Assert.assertTrue(ahp.getCheckoutButton().isDisplayed());
			System.out.println(driver.getCurrentUrl());
			Assert.assertTrue(driver.getCurrentUrl().contains("aggregator"));
			log.info("Aggregator home page displayed. Successfully validated customer login with valid credentials");
		}

		@AfterTest
		public void teardown(){
			driver.close();
			driver.quit();
		}
	}


