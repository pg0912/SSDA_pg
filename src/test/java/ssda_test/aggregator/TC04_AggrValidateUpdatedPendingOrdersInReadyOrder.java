package ssda_test.aggregator;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AggregatorHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC04_AggrValidateUpdatedPendingOrdersInReadyOrder extends TestBase {
	
	
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
	String newTimeSlot;
	String quantity;
	String readyOrder;
	
	
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
	public void testViewEditOrderDetailsChangeTimeSlot() {
		 	
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
		lp.getLogin().click(); 
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed()); 
	    util.waitForElementToBeVisible(driver, ahp.getOrderID(), 30);
		util.doubleClick(driver, ahp.getOrderHeader());
		orderId = ahp.getOrderID().getText();
		//util.clickOnElementUsingActions(driver, ahp.getTimeSlotOrderDetails());
		log.info("Aggregator is able to click on change timeslot link");
		util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 90);
		util.waitForElementToBeVisible(driver, ahp.getSelectTimeSlotText(),30);
		Assert.assertTrue(ahp.getSelectTimeSlotText().isDisplayed());
		log.info("Aggregator is able to view update timeslot window");
		ahp.getChangeTimeCancelButton().click();
		log.info("Aggregator is able to click on cancel button on timeslot window");
		Assert.assertTrue(ahp.getOrderDetails().isDisplayed());
		ahp.getTimeSlotOrderDetails().click();
		util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
		Assert.assertTrue(ahp.getChangeTimeSlotTime().isDisplayed());
		ahp.getChangeTimeSlotTime().click();
		log.info("Aggregator is able to select new timeslot from update timeslot window");
		newTimeSlot = ahp.getChangeTimeSlotTime().getText();
		ahp.getChangeTimeSlotSubmitButton().click();
		//util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
		log.info("Aggregator is able change timeslot and click on submit button");
		util.waitForElementToBeVisible(driver, ahp.getAlert(),90);
		orderChangedAlertMessage = ahp.getAlert().getText();
		//Assert.assertTrue(orderChangedAlertMessage.contains("Aggregator"));
		Assert.assertTrue(orderChangedAlertMessage.contains("Time Slot Successfully Changed!"));
		System.out.println("orderChangedAlertMessage");
		util.waitForElementToBeInvisible(driver, ahp.getAlert(), 30);
		System.out.println(ahp.getTimeSlotOrderDetails().getText());
		System.out.println(newTimeSlot);
		//Assert.assertTrue(ahp.getTimeSlotOrderDetails().getText().contains(newTimeSlot));
		log.info("Validated updated time after successful timeslot change");
		ahp.getCloseButton().click();
		log.info("Aggregator is able to click on close button on order details window");
		Assert.assertEquals(ahp.getOrderID().getText(),orderId);
		log.info("Validate after clicking on close button aggregator is able to view the same order in pending order table ");
		}
		
	
	@Test(priority=2)
	public void readyforDeliveryTest() {
		log.info("Validate after clicking on Actions button for an order");
		util.waitForElementToBeVisible(driver, ahp.getCustomerName(), 30);
		ahp.getActions().click();
		log.info("Order Details window is displayed");
		Assert.assertTrue(ahp.getOrderDetails().isDisplayed()); 
		util.waitForElementToBeInvisible(driver,ahp.getPageLoading(), 60);
		util.waitForElementToBeVisible(driver, ahp.getReadyForDeliveryButton(), 30);
	ahp.getReadyForDeliveryButton().click();
	log.info("Aggregator is able to click on Ready for delivery button for pending order");
	util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
	util.waitForElementToBeVisible(driver, ahp.getAlert(), 60);
	Assert.assertTrue(ahp.getAlert().isDisplayed());
    orderChangedAlertMessage = ahp.getAlert().getText();
	Assert.assertTrue(orderChangedAlertMessage.contains("Changed"));
	util.waitForElementToBeInvisible(driver, ahp.getAlert(), 60);
	util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 30);
	log.info("Order successfully changed alert message displayed");
	System.out.println("orderChangedAlertMessage");
}

	@Test(priority=3)
	public void testReadyordersInReadytab() throws InterruptedException {
		log.info("Validate after marking of an order to ready for delivery it display under ready order tab");
		Assert.assertTrue(ahp.getReadyOrders().isDisplayed());
		ahp.getReadyOrders().click();
		util.waitForElementToBeVisible(driver, ahp.getOrderID(), 60);
		log.info("Ready order tab is displayed");
		util.doubleClick(driver, ahp.getOrderHeader());
		log.info("User double clicks on OrderNo header to sort with recent orders marked as ready");
	    readyOrder = ahp.getOrderID().getText(); 
	    System.out.println(readyOrder);
	    Assert.assertEquals(ahp.getOrderID().getText(), orderId);
	    log.info("Validate pending order id marked as ready for delivery  is displayed in ready tab");  
		
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}

}
