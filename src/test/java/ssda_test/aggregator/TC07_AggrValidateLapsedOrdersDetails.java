package ssda_test.aggregator;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import java.util.Date;


public class TC07_AggrValidateLapsedOrdersDetails extends TestBase {
	
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
	public void lapsedOrderdetails() {	
		
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
		lp.getLogin().click();
		util.waitForElementToBeVisible(driver, ahp.getClearFiltersButton(),5);
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed());
		log.info("Succesfully landing to Aggregators orders page");
		log.info("Succesfully landing to Aggregators orders page");
		ahp.getLapsedOrders().click();
		Assert.assertTrue(ahp.getLapsedOrders().isDisplayed());
		log.info("Succesfully navigated to Lapsed orders");
		log.info("Validation of a lapsed order details");
	       orderId = ahp.getOrderID().getText();
	        customerName = ahp.getCustomerName().getText();
	        timeSlot = ahp.getTimeSlot().getText();
	        date = ahp.getDate().getText();
	        contact = ahp.getContact().getText();
	        actions = ahp.getActions().getText();
			log.info("OrderID CustomerName Timeslot date contact details are displayed for each order");
	}

	@Test(priority=2)
	public void testLapsedordersInOrderDetailsWindow() {
		
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
		Assert.assertTrue(ahp.getLapsedOrderStatus().getText().contains("LAPSED"));
		log.info("Order details has been verified");
		
	}
	
	@Test(priority=3)
	public void testLapsedordersWhenDeliveryDatePassed() throws ParseException {
		
		
		log.info("Validate past dated order are displayed under Lapsed order");
		util.doubleClick(driver, ahp.getOrderID());
		log.info("User double clicks on OrderNo header to sort with recent orders marked as lapsed");
	    CurrentDate = util.getDate();
	    System.out.println("date1"+CurrentDate);
	    System.out.println("date2"+date);
	    SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        Date date1 = dateFormat.parse(CurrentDate);
        Date date2 = dateFormat.parse(date);
        if(date2.before(date1)) {
            log.info("lapsed order is displayed as expected");
        } else {
        	log.info("order should not be displayed under lapsed order tab as delivery date is pending ");
        }
        
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}



}
