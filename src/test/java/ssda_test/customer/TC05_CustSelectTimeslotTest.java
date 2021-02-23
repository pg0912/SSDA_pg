package ssda_test.customer;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CustomerHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC05_CustSelectTimeslotTest extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	CustomerHomePage chp;
	Utilities util;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public int quantity = 2;
	
	
	@BeforeTest
	public void initialize() throws IOException{
		driver = initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		util = new Utilities();
		lp = new LoginPage(driver);
		chp = new CustomerHomePage(driver);
	}

	@Test(priority=1)
	public void testAddToCartClickCheckoutButton() {
		log.info("Validate Add to Cart and click Checkout Button");
		lp.getMobile().sendKeys(prop.getProperty("cust_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("cust_password"));
		lp.getLogin().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		Assert.assertTrue(chp.getAddToCart().isDisplayed());
		log.info("Select quantity and Click on Add to cart button for product");
		util.selectValueFromDropDownByIndex(chp.getProductQuantity(), quantity);
		chp.getAddToCart().click();
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		log.info("Product Added to cart. Click on Checkout button");
		Assert.assertTrue(chp.getCheckoutButton().isEnabled());
		chp.getCheckoutButton().click();
		log.info("Select timeslot for Product delivery window displayed");
	}
	
	@Test(priority=2)
	public void testDontSelectTimeslotClickSubmit() {
		log.info("Validate without selecting delivery timeslot Customer clicks on Submit button");
		util.waitForElementToBeVisible(driver, chp.getSelectDeliveryDate(), 20);
		Assert.assertTrue(chp.getSelectDeliveryTimeSlot().isEnabled());
		chp.getSubmitDeliveryTimeSlot().click();
		Assert.assertTrue(chp.getAlert().isDisplayed());
		Assert.assertEquals(chp.getAlert().getText(), "Please select timeslot");
		log.info("Please select timeslot alert message displayed");
	}
	
	@Test(priority=3)
	public void testSelectTimeslotForDeliveryClickCancel() {
		log.info("Validate after selecting delivery timeslot Customer clicks on Cancel button");
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		Assert.assertTrue(chp.getSelectDeliveryTimeSlot().isEnabled());
		chp.getSelectDeliveryTimeSlot().click();
		log.info("Customer selects the delivery timeslot for product and click on Cancel");
		chp.getCancelDeliveryTimeSlot().click();
		Assert.assertTrue(chp.getCheckoutButton().isDisplayed());
		log.info("After clicking on Cancel button delivery timeslot window closes");
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	} 
}
