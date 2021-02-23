package ssda_test.aggregator;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AggregatorHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;


public class TC08_AggrValidateCanceledOrdersDetails extends TestBase {
	
	public WebDriver driver;
	LoginPage lp; // reference 
	AggregatorHomePage ahp;
	String orderId;
	String customerName;
	String timeSlot;
	String date;
	String contact;
	String actions;
	String orderChangedAlertMessage;
	Utilities util;
	String LAPSED;
	String deliveryDate;
	 String Date;
	 String CurrentDate;
	 String Canceled;
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@BeforeTest
	public void initialize() throws IOException{
		driver = initializeDriver();
		log.info("Driver is initialized"); 
		ahp = new AggregatorHomePage(driver);
		lp = new LoginPage(driver); 
		util = new Utilities();
		driver.get(prop.getProperty("url")); 
		log.info("Navigated to Login page"); 
	}
	
	@Test(priority=1)
	public void canceledOrderdetails() {	
		
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
		lp.getLogin().click();
		util.waitForElementToBeVisible(driver, ahp.getClearFiltersButton(),5);
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed());
		log.info("Succesfully landing to Aggregators orders page");
		ahp.getCanceledOrders().click();
		Assert.assertTrue(ahp.getCanceledOrders().isDisplayed());
		log.info("Succesfully navigated to Canceled orders");
		log.info("Validation of a canceled order details");
	       orderId = ahp.getOrderID().getText();
	        customerName = ahp.getCustomerName().getText();
	        timeSlot = ahp.getTimeSlot().getText();
	        date = ahp.getDate().getText();
	        contact = ahp.getContact().getText();
	        actions = ahp.getActions().getText();
			log.info("OrderID CustomerName Timeslot date contact details are displayed for each order");
	}

	@Test(priority=2)
	public void testCanceledordersInOrderDetailsWindow() {
		
		log.info("Validate after clicking on Actions button for an order");
		ahp.getActions().click();
		log.info("Order Details window is displayed");
		Assert.assertTrue(ahp.getOrderDetails().isDisplayed());
		Assert.assertTrue(ahp.getOrderNoDetails().getText().contains(orderId)); 
		Assert.assertEquals(ahp.getCustomerName().getText(),customerName);
		Assert.assertEquals(ahp.getTimeSlot().getText(),timeSlot);
		Assert.assertEquals(ahp.getDate().getText(),date);
		Assert.assertEquals(ahp.getContact().getText(),contact);	
		Assert.assertTrue(ahp.getCanceledOrderStatus().getText().contains("CANCELLED"));
		log.info("Order details has been verified");
		
	}
	
	
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}
}

