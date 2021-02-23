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
import pageObjects.CustomerOrderListPage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC06_CustPlaceOrderVerifyOrderDetailsTest extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	CustomerHomePage chp;
	CustomerOrderListPage colp;
	Utilities util;
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public int quantity = 2;
	public String productName = "";
	public String pricePerItem = "";
	public String totalAmount = "";
	public String deliveryDate = "";
	public String deliveryTimeslot = "";
	public String orderPlacedAlertMessage = "";
	public String orderNo = "";
	public String deliveryStatus = "";
	
	
	@BeforeTest
	public void initialize() throws IOException{
		driver = initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		util = new Utilities();
		lp = new LoginPage(driver);
		chp = new CustomerHomePage(driver);
		colp = new CustomerOrderListPage(driver);
	}

	@Test(priority=1)
	public void testSelectTimeslotForDeliveryClickSubmit() {
		log.info("Validate after selecting delivery timeslot Customer clicks on Submit button");
		lp.getMobile().sendKeys(prop.getProperty("cust_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("cust_password"));
		lp.getLogin().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		Assert.assertTrue(chp.getAddToCart().isDisplayed());
		productName = chp.getproductName().getText();
		util.selectValueFromDropDownByIndex(chp.getProductQuantity(), quantity);
		pricePerItem = chp.getProductPrice().getText();
		chp.getAddToCart().click();
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		totalAmount = chp.getTotalAmountPayable().getText();
		chp.getCheckoutButton().click();
		util.waitForElementToBeVisible(driver, chp.getSelectDeliveryDate(), 20);
		deliveryDate = chp.getSelectDeliveryDate().getText();
		log.info("Select timeslot for Product delivery window displayed");
		Assert.assertTrue(chp.getSelectDeliveryTimeSlot().isEnabled());
		deliveryTimeslot = chp.getSelectDeliveryTimeSlot().getText();
		chp.getSelectDeliveryTimeSlot().click();
		log.info("Customer selects the delivery timeslot for product and click on Submit");
		chp.getSubmitDeliveryTimeSlot().click();
		util.waitForElementToBeVisible(driver, chp.getAlert(), 20);
		Assert.assertTrue(chp.getAlert().isDisplayed());
		Assert.assertTrue(chp.getAlert().getText().contains(" is placed"));
		orderPlacedAlertMessage = chp.getAlert().getText();
		log.info("Order successfully placed alert message displayed");
	}
	
	@Test(priority=2)
	public void testCustomerOrderDisplayedonOrderListPage() {
		log.info("Validate Customer order displayed on Order List page");
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		log.info("Customer clicks on UserMenu and then Orders link");
		chp.getUserMenu().click();
		chp.getOrdersLink().click();
		log.info("Order list page opens");
		util.waitForElementToBeVisible(driver, colp.getOrderListTable(), 20);
		Assert.assertTrue(colp.getOrderListTable().isDisplayed());
		log.info("Customer orders displayed on Order list page");
		util.doubleClick(driver, colp.getOrderNoHeader());
		log.info("User double clicks on OrderNo header to sort with recent orders placed");
		Assert.assertTrue(orderPlacedAlertMessage.contains(colp.getOrderNoDetails().getText()));
		log.info("New order by Customer displayed on order list page");
	}
	
	@Test(priority=3)
	public void testOrderDetailsonOrderListPage() {
		log.info("Validate order details displayed on Order List page");
		Assert.assertEquals(colp.getDeliveryDateDetails().getText(), deliveryDate);
		Assert.assertEquals(colp.getSlotDetails().getText(), deliveryTimeslot);
		Assert.assertTrue(colp.getStatusDetails().getText().contains("PENDING"));
		deliveryStatus = colp.getStatusDetails().getText();
		log.info("Customer order details displayed on order list page are as expected");
	}
	
	@Test(priority=4)
	public void testOrderDetailsinOrderDetailsWindow() {
		log.info("Validate order details displayed on Order Details window");
		colp.getShowOrderDetails().click();
		util.waitForElementToBeVisible(driver, colp.getOrderDetailsWindow(), 20);
		Assert.assertTrue(colp.getOrderDetailsWindow().isDisplayed());
		log.info("Customer orders details window displayed on Order list page");
		Assert.assertEquals(colp.getProductNameOrderDetailsWindow().getText(), productName);
		Assert.assertEquals(Integer.parseInt(colp.getProductQuantityOrderDetailsWindow().getText()), quantity);
		Assert.assertEquals(colp.getProductPriceOrderDetailsWindow().getText(), pricePerItem);
		Assert.assertEquals(colp.getTotalPriceOrderDetailsWindow().getText(), totalAmount);
		Assert.assertEquals(colp.getOrderNumberOrderDetailsWindow().getText(), colp.getOrderNoDetails().getText());
		Assert.assertTrue(colp.getDeliveryDateOrderDetailsWindow().getText().contains(colp.getDeliveryDateDetails().getText()));
		Assert.assertTrue(colp.getTimeSlotOrderDetailsWindow().getText().contains(deliveryTimeslot));
		Assert.assertTrue(colp.getStatusOrderDetailsWindow().getText().contains("PENDING"));
		colp.getCloseButtonOrderDetailsWindow().click();
		util.waitForElementToBeInvisible(driver, colp.getOrderDetailsWindow(), 20);
		Assert.assertFalse(colp.getOrderDetailsWindow().isDisplayed());
		log.info("Customer order details displayed in Order Details window are correct");
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	} 
}
