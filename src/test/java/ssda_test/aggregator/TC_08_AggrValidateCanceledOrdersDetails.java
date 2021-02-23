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


public class TC_08_AggrValidateCanceledOrdersDetails extends TestBase {
	
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
		log.info("Driver is initialized"); // driver intialized before each execution of test in this class and closing at the end
		ahp = new AggregatorHomePage(driver);
		lp = new LoginPage(driver); 
		util = new Utilities();//object created for login and aggregator home page class
	}// to differntiate between two driver instance we use driver in each test
	
	@Test(priority=1)
	public void basePageNavigation() throws IOException {
		driver.get(prop.getProperty("url")); //driver opening the url in browser selenium method
		log.info("Navigated to Login page"); // to add message to log file
		// lp = new LoginPage(driver); //object  driver is instance for reference to that particular initialized driver in the begining
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));// send key-sending values in input fields 
		// to read values from data.peroperty files using methods from property class
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));//methods of property class to read values
		lp.getLogin().click();
		//.click,sendkeys ,get selenium methods is displayed
		util.waitForElementToBeVisible(driver, ahp.getClearFiltersButton(),5);
		// ahp = new AggregatorHomePage(driver);
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed()); //assert testng methods
		//Assert.assertEquals(driver.getTitle(), "Golden Katar URC"); if identify through title
		//Assert.assertTrue(getPendingOrders(), "error message");
		log.info("Succesfully landing to Aggregators orders page");
		ahp.getCanceledOrders().click();
		Assert.assertTrue(ahp.getCanceledOrders().isDisplayed());
		log.info("Succesfully navigated to Canceled orders");
	}
	
	
	@Test(priority=2)
	public void canceledOrderdetails() {		
		log.info("Validation of a canceled order details");
	       orderId = ahp.getOrderID().getText();
	        customerName = ahp.getCustomerName().getText();
	        timeSlot = ahp.getTimeSlot().getText();
	        date = ahp.getDate().getText();
	        contact = ahp.getContact().getText();
	        actions = ahp.getActions().getText();
			log.info("OrderID CustomerName Timeslot date contact details are displayed for each order");
	}

	@Test(priority=3)
	public void testCanceledordersInOrderDetailsWindow() {
		
		log.info("Validate after clicking on Actions button for an order");
		ahp.getActions().click();
		log.info("Order Details window is displayed");
		Assert.assertTrue(ahp.getOrderDetails().isDisplayed());
		//add all field level validations on the order details pop up
		 Assert.assertTrue(ahp.getOrderNoDetails().getText().contains(orderId)); //actual means reading value from the window
		Assert.assertEquals(ahp.getCustomerName().getText(),customerName);
		Assert.assertEquals(ahp.getTimeSlot().getText(),timeSlot);
		Assert.assertEquals(ahp.getDate().getText(),date);
		Assert.assertEquals(ahp.getContact().getText(),contact);	
		Assert.assertTrue(ahp.getCanceledOrderStatus().getText().contains("CANCELLED"));
		log.info("Order details has been verified");
		
	}
	
	
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
	}
}

