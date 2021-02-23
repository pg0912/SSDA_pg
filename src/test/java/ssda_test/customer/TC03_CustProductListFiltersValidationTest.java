package ssda_test.customer;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CustomerHomePage;
import pageObjects.LoginPage;
import resources.TestBase;
import resources.Utilities;

public class TC03_CustProductListFiltersValidationTest extends TestBase{

	public WebDriver driver;
	LoginPage lp;
	CustomerHomePage chp;
	Utilities util;
	
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public int quantity = 2;
	public String searchProduct = "JOHNSON";
	public double pricePerItemDouble = 0;
	public double totalAmountDouble = 0;
	public String totalAmountString = "";
	public String totalItems = "";

	
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
	public void testTotalItemsFoundMatcheswithItemsInPaginationFilter() {
		log.info("Validate if total number of items found matches with items number displayed in Pagination filter");
		lp.getMobile().sendKeys(prop.getProperty("cust_mobile"));
		lp.getPassword().sendKeys(prop.getProperty("cust_password"));
		lp.getLogin().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertFalse(chp.getCheckoutButton().isEnabled());
		totalItems = chp.getItemsFoundTotal().getText();
		totalItems = totalItems.replaceAll("[^0-9]", "");
		System.out.println(chp.getItemsFoundInPaginatorRange().getText());
		Assert.assertTrue(chp.getItemsFoundInPaginatorRange().getText().contains(totalItems));
		log.info("Total number of items found matches with items number displayed in Pagination filter");
	}

	@Test(priority=2)
	public void testCategoryFilters() {
		log.info("Validate number of items displayed after selecting checkbox for any category filter");
		List<WebElement> categoryLabel_list = chp.getCategoryFilterLabel();
		List<WebElement> categoryCheckbox_list = chp.getCategoryFilterCheckbox();
		// Iterating through the list and selecting the desired option
		for(int j = 0; j < categoryLabel_list.size(); j++){
			System.out.println(categoryLabel_list.get(j).getText());
			if( categoryLabel_list.get(j).getText().contains("KITCHEN ITEMS")){
				Assert.assertFalse(categoryCheckbox_list.get(j).isSelected());
				log.info("Click on checkbox infront of selected category");
				categoryLabel_list.get(j).click();
				util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
				Assert.assertTrue(categoryCheckbox_list.get(j).isSelected());
				log.info("Checkbox infront of selected category is displayed as selected");
				break;
			}
		}
		Assert.assertTrue(chp.getItemsFoundInPaginatorRange().getText().contains(chp.getItemsFoundTotal().getText().replaceAll("[^0-9]", "")));
		log.info("Total number of items found after selecting category filter checkbox matches with number of items displayed in Pagination filter");
		chp.getClearFiltersButton().click();
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertTrue(chp.getItemsFoundInPaginatorRange().getText().contains(totalItems));
	}

	@Test(priority=3)
	public void testGroupFilters() {
		log.info("Validate number of items displayed after selecting checkbox for any Group filter");
		List<WebElement> groupLabel_list = chp.getGroupFilterLabel();
		List<WebElement> groupCheckbox_list = chp.getGroupFilterCheckbox();
		// Iterating through the list and selecting the desired option
		for(int j = 0; j < groupLabel_list.size(); j++){
			System.out.println(groupLabel_list.get(j).getText());
			if( groupLabel_list.get(j).getText().contains("GROUP-VI")){
				Assert.assertFalse(groupCheckbox_list.get(j).isSelected());
				log.info("Click on checkbox infront of selected group");
				groupLabel_list.get(j).click();
				util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
				Assert.assertTrue(groupCheckbox_list.get(j).isSelected());
				log.info("Checkbox infront of selected group is displayed as selected");
				break;
			}
		}
		Assert.assertTrue(chp.getItemsFoundInPaginatorRange().getText().contains(chp.getItemsFoundTotal().getText().replaceAll("[^0-9]", "")));
		log.info("Total number of items found after selecting group filter checkbox matches with number of items displayed in Pagination filter");
	}


	@Test(priority=4)
	public void testClearAllFilters() {
		log.info("Validate after clearing all filters total items present in inventory are displayed on product list page");
		chp.getClearFiltersButton().click();
		log.info("Click on 'Clear All Filters' button");
		util.waitForElementToBeInvisible(driver, chp.getNoRecordsFound(), 20);
		Assert.assertTrue(chp.getItemsFoundInPaginatorRange().getText().contains(totalItems));
		log.info("Total items displayed in pagination filter matches with number of items present in inventory");
		List<WebElement> categoryCheckbox_list = chp.getCategoryFilterCheckbox();
		for(int j = 0; j < categoryCheckbox_list.size(); j++){
			Assert.assertFalse(categoryCheckbox_list.get(j).isSelected());
		}
		System.out.println("Checkbox infront of all category filters is displayed as unchecked");
		log.info("Checkbox infront of all category filters is displayed as unchecked");
		List<WebElement> groupCheckbox_list = chp.getGroupFilterCheckbox();
		for(int k = 0; k < groupCheckbox_list.size(); k++){
			Assert.assertFalse(groupCheckbox_list.get(k).isSelected());
		}
		System.out.println("Checkbox infront of all Group filters is displayed as unchecked");
		log.info("Checkbox infront of all Group filters is displayed as unchecked");
	}

	@AfterTest
	public void teardown(){
		driver.quit();
	} 
}
