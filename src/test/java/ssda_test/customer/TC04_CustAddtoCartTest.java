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

public class TC04_CustAddtoCartTest extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	CustomerHomePage chp;
	Utilities util;
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public int quantity = 2;
	public double pricePerItemDouble = 0;
	public double totalAmountDouble = 0;
	public String totalAmountString = "";

	
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
	public void testAddToCartFeature() {
		log.info("Validate Add to Cart feature on Product list page");
		lp.getMobile().sendKeys(prop.getProperty("cust_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("cust_password"));
		lp.getLogin().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		Assert.assertTrue(chp.getAddToCart().isDisplayed());
		log.info("Select quantity of product as : " + quantity);
		util.selectValueFromDropDownByIndex(chp.getProductQuantity(), quantity);
		log.info("Click on Add to cart button for product");
		chp.getAddToCart().click();
		Assert.assertTrue(chp.getAlert().isDisplayed());
		Assert.assertTrue(chp.getAlert().getText().contains("added in the cart"));
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		Assert.assertTrue(chp.getCheckoutButton().isEnabled());
		log.info("Product Added to cart and Checkout button is Enabled");
	}
	
	@Test(priority=2)
	public void testProductDetailsAddedInCart() {
		log.info("Validate if correct details displayed for product added in Cart");
		String productName = chp.getproductName().getText();
		String cartDetails = chp.getProductInCart().getText();
		Assert.assertTrue(cartDetails.contains(productName));
		log.info("Name of the product in cart matches with selected product");
		
		Assert.assertTrue(cartDetails.contains("Qty: "+quantity));
		log.info("Quantity of the product in cart matches with selected quantity");
		
		String pricePerItem = chp.getProductPrice().getText();
		pricePerItemDouble=Double.parseDouble(pricePerItem.substring(1));
		Assert.assertTrue(cartDetails.contains(String.valueOf(pricePerItemDouble*quantity)));
		log.info("Product Amount in Cart matches as per selected quantity");
	}
	
	@Test(priority=3)
	public void testTotalAmountPayable() {
		log.info("Validate details of total amount payable are as per product quantity");
		totalAmountString=chp.getTotalAmountPayable().getText();
		Assert.assertTrue(totalAmountString.contains(String.valueOf(pricePerItemDouble*quantity)));
		log.info("Total payable amount matches as per selected quantity");
	}
	
	@Test(priority=4)
	public void testAddProductWithSameQuantity() {
		log.info("Validate when Customer Selects same quantity of product again");
		util.selectValueFromDropDownByIndex(chp.getProductQuantity(), quantity);
		log.info("Click on Add to cart button for product");
		chp.getAddToCart().click();
		util.waitForElementToBeVisible(driver, chp.getAlert(), 20);
		Assert.assertTrue(chp.getAlert().isDisplayed());
		Assert.assertTrue(chp.getAlert().getText().contains("added in the cart already with same quantity"));
		log.info("Alert message displayed as Product added in the cart already with same quantity");
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		Assert.assertTrue(chp.getCheckoutButton().isEnabled());
	}
			
	@Test(priority=5)
	public void testDeleteItemFromCart() {
		log.info("Validate Delete Product from cart feature");
		Assert.assertTrue(chp.getProductInCart().isDisplayed());
		log.info("Product present in cart");
		totalAmountDouble=Double.parseDouble(totalAmountString.substring(1));
		double expectedTotalAmountAfterDeleteItem = (totalAmountDouble - (pricePerItemDouble*quantity));
		chp.getDeleteItemButton().click();
		log.info("Click on Delete Item button");
		Assert.assertTrue(chp.getAlert().isDisplayed());
		Assert.assertTrue(chp.getAlert().getText().contains("removed from Cart"));
		util.waitForElementToBeInvisible(driver, chp.getAlert(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		Assert.assertTrue(chp.getTotalAmountPayable().getText().contains(String.valueOf(expectedTotalAmountAfterDeleteItem)));
		log.info("Product removed from cart. Updated value of Total amount payable displayed");
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	} 
}
