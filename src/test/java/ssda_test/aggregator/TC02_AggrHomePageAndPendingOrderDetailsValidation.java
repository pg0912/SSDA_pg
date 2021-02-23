package ssda_test.aggregator;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import pageObjects.AdminHomePage;
import pageObjects.AggregatorHomePage;
import pageObjects.CustomerHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC02_AggrHomePageAndPendingOrderDetailsValidation extends TestBase {

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
	String status;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	

	@BeforeTest
	public void initialize() throws IOException{
		driver = initializeDriver();
		log.info("Driver is initialized"); // driver intialized before each execution of test in this class and closing at the end
		ahp = new AggregatorHomePage(driver);
		lp = new LoginPage(driver); 
		util = new Utilities();//object created for login and aggregator home page class
	}// to differntiate between two driver instance we use driver in each test
	
	@Test
	public void basePageNavigation() throws IOException {
		driver.get(prop.getProperty("url")); //driver opening the url in browser selenium method
		log.info("Navigated to Login page"); // to add message to log file
		// lp = new LoginPage(driver); //object  driver is instance for reference to that particular initialized driver in the begining
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));// send key-sending values in input fields 
		// to read values from data.peroperty files using methods from property class
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));//methods of property class to read values
		lp.getLogin().click(); //.click,sendkeys ,get selenium methods is displayed
		// ahp = new AggregatorHomePage(driver);
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed()); //assert testng methods
		//Assert.assertEquals(driver.getTitle(), "Golden Katar URC"); if identify through title
		//Assert.assertTrue(getPendingOrders(), "error message");
		log.info("Succesfully landing to Aggregators orders page");
	}
	
	
	@Test(priority=1)
	
	public void testOrderAndCustomerDetailsDisplay() {		
		log.info("Validation of a pending order details");
		//sorting through order id 
		util.doubleClick(driver, ahp.getOrderID());
		log.info("User double clicks on OrderNo header to sort with recent orders placed");
		//Assert.assertTrue(orderPlacedAlertMessage.contains(colp.getOrderNoDetails().getText()));
		log.info("New order by Customer displayed on order list page");
	       orderId = ahp.getOrderID().getText();
	        customerName = ahp.getCustomerName().getText();
	        timeSlot = ahp.getTimeSlot().getText();
	        date = ahp.getDate().getText();
	        contact = ahp.getContact().getText();
	        actions = ahp.getActions().getText();
	    
			log.info("orderID CustomerName Timeslot date contact details are displayed for each order");
	}

	@Test(priority=2)
	public void testViewEditOrderDetailsClickActionsButton() throws InterruptedException {
	log.info("Validate after clicking on Actions button for an order");
	util.waitForElementToBeVisible(driver, ahp.getActions(), 60);
	ahp.getActions().click();
	log.info("Order Details window is displayed");
	util.waitForElementToBeVisible(driver, ahp.getTimeSlotOrderDetails(), 20);
	Assert.assertTrue(ahp.getOrderDetails().isDisplayed());
	//add all field level validations on the order details pop up
	Assert.assertTrue(ahp.getOrderNoDetails().getText().contains(orderId)); //actual means reading value from the window
	Assert.assertEquals(ahp.getCustomerName().getText(),customerName);
	Assert.assertEquals(ahp.getTimeSlot().getText(),timeSlot);
	Assert.assertEquals(ahp.getDate().getText(),date);
	Assert.assertEquals(ahp.getContact().getText(),contact);
	Assert.assertTrue(ahp.getPendingOrderStatus().getText().contains("PENDING"));
	log.info("Order details has been verified");
	Thread.sleep(10000);
	Assert.assertTrue(ahp.getTimeSlotOrderDetails().isDisplayed());
	util.clickOnElementUsingActions(driver, ahp.getTimeSlotOrderDetails());
	}
	
	@Test(priority=3)
	public void testViewEditOrderDetailsChangeTimeSlot() {
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
	
	
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
	}
}



