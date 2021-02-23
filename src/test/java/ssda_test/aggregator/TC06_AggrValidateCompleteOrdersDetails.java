package ssda_test.aggregator;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AggregatorHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC06_AggrValidateCompleteOrdersDetails extends TestBase  {
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
	String readyOrder;
	String COMPLETED;
	String status;
	
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
	public void completedOrderDetails() {
		
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
		lp.getLogin().click();
		util.waitForElementToBeVisible(driver, ahp.getClearFiltersButton(),5);
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed());
		log.info("Succesfully landing to Aggregators orders page");
		ahp.getCompletedOrders().click();
		Assert.assertTrue(ahp.getCompletedOrders().isDisplayed());
		log.info("Succesfully navigated to completed orders");
		log.info("Validation of a completed order details");
		util.doubleClick(driver, ahp.getOrderID());
		log.info("User double clicks on OrderNo header to sort with recent orders ");
	       orderId = ahp.getOrderID().getText();
	        customerName = ahp.getCustomerName().getText();
	        timeSlot = ahp.getTimeSlot().getText();
	        date = ahp.getDate().getText();
	        contact = ahp.getContact().getText();
	        actions = ahp.getActions().getText();
			log.info("OrderID CustomerName Timeslot date contact details are displayed for each order");
	}

	@Test(priority=2)
	public void testOrderDetailWindow() {
		ahp.getActions().click();
		util.waitForElementToBeVisible(driver, ahp.getItemName(), 30);
		log.info("Order details window is displayed");
	    ahp.getOrderDetails().isDisplayed();
	    //Assert.assertEquals(ahp.getOrderNoDetails().getText(), orderId);
	    log.info("Validate ready order details in order details winodw");
	    Assert.assertTrue(ahp.getOrderNoDetails().getText().contains(orderId)); //actual means reading value from the window
		Assert.assertEquals(ahp.getCustomerName().getText(),customerName);
		Assert.assertEquals(ahp.getTimeSlot().getText(),timeSlot);
		Assert.assertEquals(ahp.getDate().getText(),date);
		Assert.assertEquals(ahp.getContact().getText(),contact);
		Assert.assertTrue(ahp.getCompletedOrderStatus().getText().contains("COMPLETED"));
		log.info("Order details has been verified");
	    ahp.getCloseButton().click();
	    log.info("Completed Order tab is displayed");
	    ahp.getCompletedOrders().isDisplayed();
	    
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}
}


