package ssda_test.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CustomerHomePage;
import pageObjects.CustomerOrderListPage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC07_CustOrderListFiltersValidationTest extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	CustomerHomePage chp;
	CustomerOrderListPage colp;
	Utilities util;
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public String deliveryDate = "";
	public String orderNo = "";
	
	
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
	public void testCustomerOrdersDisplayedonOrderListPage() {
		log.info("Validate Customer order displayed on Order List page");
		lp.getMobile().sendKeys(prop.getProperty("cust_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("cust_password"));
		lp.getLogin().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		log.info("Customer clicks on UserMenu and then clicks on Orders link");
		chp.getUserMenu().click();
		chp.getOrdersLink().click();
		log.info("Order list page opens");
		util.waitForElementToBeVisible(driver, colp.getOrderListTable(), 20);
		Assert.assertTrue(colp.getOrderListTable().isDisplayed());
		log.info("Customer orders displayed on Order list page");
		deliveryDate = colp.getDeliveryDateDetails().getText();
		util.doubleClick(driver, colp.getOrderNoHeader());
		log.info("User double clicks on OrderNo header to sort with recent orders placed");
		orderNo = colp.getOrderNoDetails().getText();
		log.info("Orders by Customer displayed on order list page");
	}
	
	@Test(priority=2)
	public void testOrdersDisplayedPerPageFilter() {
		log.info("Validate if Orders are displayed per page as per option selected from dropdown");
		util.clickOnElementUsingActions(driver, colp.getItemsPerPageFilter());
		// Get the all WebElements inside the dropdown in List
		List<WebElement> dropdown_list = colp.getDropdownOptions();
		// Iterating through the list and selecting the desired option
		for( int j = 0; j < dropdown_list.size(); j++){
			if( dropdown_list.get(j).getText().contains("20")){
				dropdown_list.get(j).click();
				break;
			}
		}
		Assert.assertEquals(colp.getItemsPerPageFilter().getText(), "20");
		log.info("On Order list page user clicked option is displayed as selected");
		List<WebElement> totalSearchResult = colp.getTotalSearchResults();
		Assert.assertEquals(colp.getItemsPerPageFilter().getText(), String.valueOf(totalSearchResult.size()));
		log.info("On Order list page Orders displayed per page as per dropdown option selected");
	}
	
	@Test(priority=3)
	public void testSearchByOrderStatusFilter() {
		log.info("Validate if Orders are displayed as per Status selected from dropdown");
		ArrayList<String> orderStatus = new ArrayList<String>(Arrays.asList("Ready", "Pending", "Completed", "Lapsed", "Cancelled"));
		for(String status : orderStatus) {
			util.clickOnElementUsingActions(driver, colp.getStatusFilter());
			// Get the all WebElements inside the dropdown in List
			List<WebElement> dropdown_list = colp.getDropdownOptions();
			// Iterating through the list and selecting the desired option
			for( int j = 0; j < dropdown_list.size(); j++){
				if( dropdown_list.get(j).getText().contains(status)){
					log.info("Select "+ status +" from dropdown");
					dropdown_list.get(j).click();
					util.waitForAllElementsToBeInvisible(driver, colp.getDropdownOptions(), 20);
					break;
				}
			}
			List<WebElement> allOrderStatusDetails = colp.getAllOrderStatusDetails();
			for( int i = 0; i < allOrderStatusDetails.size(); i++){
				Assert.assertEquals(allOrderStatusDetails.get(i).getText(), status.toUpperCase());
			}
			log.info("On Order list page all statuses displayed as per "+ status.toUpperCase() +" option selected from dropdown");
		}
	}
	
	@Test(priority=4)
	public void testSearchByOrderNumberFilter() {
		log.info("Validate search by order number feature on Order list page");
		colp.getClearFiltersButton().click();
		log.info("Customer enters Order number to search from all orders");
		colp.getOrderNoFilter().sendKeys(orderNo);
		List<WebElement> allOrderNoDetails = colp.getAllOrderNoDetails();
		System.out.println("Total Orders found after search : "+ allOrderNoDetails.size());
		log.info("Total Orders found after search : "+ allOrderNoDetails.size());
		for( int i = 0; i < allOrderNoDetails.size(); i++){
			Assert.assertEquals(allOrderNoDetails.get(i).getText(), orderNo);
		}
		log.info("Orders found matches with order number searched by customer");
	}
	
	@Test(priority=5)
	public void testSearchByDeliveryDateFilter() {
		log.info("Validate search by delivery date feature on Order list page");
		colp.getClearFiltersButton().click();
		log.info("Customer enters delivery date to search from all orders");
		colp.getDeliveryDateFilter().sendKeys(deliveryDate);
		colp.getDeliveryDateFilter().sendKeys(Keys.ENTER);
		List<WebElement> allDeliveryDateDetails = colp.getAllDeliveryDateDetails();
		System.out.println("Total Orders found after search : "+ allDeliveryDateDetails.size());
		log.info("Total Orders found after search : "+ allDeliveryDateDetails.size());
		for( int i = 0; i < allDeliveryDateDetails.size(); i++){
			Assert.assertEquals(allDeliveryDateDetails.get(i).getText(), deliveryDate);
		}
		log.info("All Orders found as per delivery date searched by customer");
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	} 
}
