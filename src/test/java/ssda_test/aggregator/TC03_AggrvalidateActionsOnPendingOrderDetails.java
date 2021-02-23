package ssda_test.aggregator;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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

public class TC03_AggrvalidateActionsOnPendingOrderDetails extends TestBase {


	public WebDriver driver;
	LoginPage lp; // reference 
	AggregatorHomePage ahp;
	String orderId = "";
	String customerName = "";
	String timeSlot = "";
	String date = "";
	String contact = "";
	String actions = "";
	String orderChangedAlertMessage = "";
	Utilities util;
	String newTimeSlot = "";
	String quantity = "";
	String canceledOrder = "";
	Integer a;


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
	public void testButtonsOrderDetails()  {
			
		lp.getMobile().sendKeys(prop.getProperty("aggr_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("aggr_password"));
		lp.getLogin().click(); 
		Assert.assertTrue(ahp.getPendingOrders().isDisplayed()); 
		util.waitForElementToBeVisible(driver, ahp.getOrderHeader(), 5);
		util.doubleClick(driver, ahp.getOrderHeader());
		ahp.getOrderID().click();
		log.info("Validate after clicking on Actions button for an order");
		ahp.getActions().click();
		log.info("Order Details window is displayed");
		Assert.assertTrue(ahp.getOrderDetails().isDisplayed());
		quantity= ahp.getQuantity().getAttribute("value");
		System.out.println("quantity : " + quantity);
		int a = Integer.parseInt(quantity);
		if(ahp.getPlusButton().isEnabled()) {
			ahp.getPlusButton().click();
			System.out.println("Quantity after clicking on Plus button: "+ ahp.getQuantity().getAttribute("value"));
			Assert.assertEquals(ahp.getQuantity().getAttribute("value"),String.valueOf(a+1));
			if (ahp.getMinusButton().isEnabled()) {
				ahp.getMinusButton().click();
				System.out.println("Quantity after clicking on Minus button: "+ ahp.getQuantity().getAttribute("value"));
				Assert.assertEquals(ahp.getQuantity().getAttribute("value"),String.valueOf(a));
				log.info("Validate adding and reducing quantity function");
			}
		}
	}

	@Test(priority=2)
	public void testRemoveOrderCancelButton()  {
		//util.doubleClick(driver, ahp.getOrderHeader());
		//orderId = ahp.getOrderID().getText(); 
		log.info("Validate after clicking on remove button for an order");
		List<WebElement> allItemNames = ahp.getAllItemNames();
		int a= allItemNames.size();
		if (allItemNames.size()<2)
		{
			util.waitForElementToBeVisible(driver, ahp.getRemoveButton(), 5);
			util.clickOnElementUsingActions(driver, ahp.getRemoveButton());
			//ahp.getRemoveButton().click();
			log.info("Validate after clicking on remove button if order is conatining 1 item");
			log.info("Cancel The Order window is displayed");
			Assert.assertTrue(ahp.getCancelTheOrderText().isDisplayed());
			log.info("Validate after clicking on cancel button on cancel the order window");
			util.waitForElementToBeVisible(driver, ahp.getCancelTheOrderButton(), 30);
			ahp.getCancelTheOrderButton().click();
			util.waitForElementToBeVisible(driver, ahp.getCustomerName(), 60);
			Assert.assertTrue(ahp.getCustomerName().isDisplayed());
			log.info("Customer Name under Pending order are displayed on aggregator home page");
		}
	
	}
	@Test(priority=3)
	public void testRemoveOrderDetails() throws InterruptedException {
		util.waitForElementToBeVisible(driver, ahp.getActions(), 30);
		ahp.getActions().click();
		//util.doubleClick(driver, ahp.getOrderHeader());
		//orderId = ahp.getOrderID().getText(); 
		log.info("Validate after clicking on remove button for an order");
		List<WebElement> allItemNames = ahp.getAllItemNames();
		int a= allItemNames.size();
		if (allItemNames.size()<2)
		{
			util.waitForElementToBeVisible(driver, ahp.getRemoveButton(), 5);
			ahp.getRemoveButton().click();
			log.info("Validate after clicking on remove button if order is conatining 1 item");
			log.info("Cancel The Order window is displayed");
			Assert.assertTrue(ahp.getCancelTheOrderText().isDisplayed());
			log.info("Validate after clicking on proceed button for cancelling the order");
			ahp.getProceedButton().click();
			//util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
			util.waitForElementToBeVisible(driver, ahp.getAlert(), 60);
			Assert.assertTrue(ahp.getAlert().isDisplayed());
			orderChangedAlertMessage = ahp.getAlert().getText();
			System.out.println(orderChangedAlertMessage);
			Assert.assertTrue(orderChangedAlertMessage.contains("Cancelled"));
			log.info("Order cancelled successfully alert message displayed");
			util.waitForElementToBeInvisible(driver, ahp.getAlert(), 30);
			//util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
			System.out.println("orderChangedAlertMessage");
			log.info("Validate after cancelling of an order it display under cancelled order tab");
			util.waitForElementToBeVisible(driver,ahp.getCanceledOrders(),5);
			ahp.getCanceledOrders().click();
			log.info("Canceled orders tab is opened");
			Assert.assertTrue(ahp.getCanceledOrders().isDisplayed());
			util.doubleClick(driver, ahp.getOrderHeader());
			log.info("User double clicks on OrderNo header to sort with recent orders deleted");
			canceledOrder = ahp.getOrderID().getText();
			Assert.assertEquals(canceledOrder, orderId);
			log.info("Validate pending order id removed is display in canceled tab");
		}
		else
		{                
			ahp.getRemoveButton().click();
			List<WebElement> allItemNameafterremoveclick= ahp.getAllItemNames();
			log.info("Validate after clicking on remove button if order is conatining more than 1 item");
			Assert.assertEquals(allItemNameafterremoveclick.size(), (a-1));
			log.info("Validate number of items in list is reduced by 1");
			ahp.getReadyForDeliveryButton().click();
			log.info("validate order status changed to ready for delivery");
			util.waitForElementToBeVisible(driver, ahp.getAlert(), 60);
			Assert.assertTrue(ahp.getAlert().isDisplayed());
			orderChangedAlertMessage = ahp.getAlert().getText();
			System.out.println(orderChangedAlertMessage);
			Assert.assertTrue(orderChangedAlertMessage.contains("Changed"));
			log.info("Order successfully changed alert message displayed");
			util.waitForElementToBeInvisible(driver, ahp.getAlert(), 60);
			//util.waitForElementToBeInvisible(driver, ahp.getPageLoading(), 60);
			System.out.println("orderChangedAlertMessage");
			util.waitForElementToBeVisible(driver, ahp.getReadyOrders(), 5);
			Thread.sleep(5000);
			ahp.getReadyOrders().click();
			log.info("Ready orders tab is opened");
			Assert.assertTrue(ahp.getReadyOrders().isDisplayed());
			util.doubleClick(driver, ahp.getOrderHeader());
			Assert.assertEquals(ahp.getOrderID(), orderId);
		}

	}


	@AfterTest
	public void teardown(){
		driver.quit();
	}


}

