package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CustomerOrderListPage {

	public WebDriver driver;
	
	private By orderNoFilter = By.xpath("//input[@placeholder='Order No.']");
	
	private By deliveryDateFilter = By.xpath("//span[contains(.,'Delivery Date')]//parent::div/input");
	
	private By statusFilter = By.xpath("//span[contains(.,'Status')]/label/span");
	
	private By itemsPerPageFilter = By.xpath("//mat-select[@aria-label='Items per page:']");
	
	private By dropdownOptions = By.xpath("//div[@class='cdk-overlay-pane']//mat-option");
	
	private By totalsearchResults = By.xpath("//table[@role='grid']/tbody/tr");
	
	private By orderListTable = By.xpath("//table[@role='grid']/tbody/tr/td");
	
	private By orderNoHeader = By.xpath("//table[@role='grid']//button[contains(., 'ORDER NO')]");
	
	private By deliveryDateHeader = By.xpath("//table[@role='grid']//button[contains(.,'DELIVERY DATE')]");
	
	private By slotHeader = By.xpath("//table[@role='grid']//button[contains(.,'SLOT')]");
	
	private By amountHeader = By.xpath("//table[@role='grid']//button[contains(.,'AMOUNT')]");
	
	private By statusHeader = By.xpath("//table[@role='grid']//button[contains(.,'STATUS')]");
	
	private By orderNoDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[1]");
	
	private By allOrderNoDetails = By.xpath("//table[@role='grid']/tbody/tr/td[1]");
	
	private By deliveryDateDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[2]");
	
	private By allDeliveryDateDetails = By.xpath("//table[@role='grid']/tbody/tr/td[2]");
	
	private By slotDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[3]");
	
	private By amountDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[4]");
	
	private By statusDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[5]");
	
	private By allOrderStatusDetails = By.xpath("//table[@role='grid']/tbody/tr/td[5]");
	
	private By showOrderDetails = By.xpath("//table[@role='grid']/tbody/tr[1]/td[6]/button");
	
	private By clearFilterButton = By.xpath("//button[contains(.,'Clear Filters')]");
	
	private By refreshButton = By.xpath("//button[contains(.,'Refresh')]");
	
	private By backToHomeButton = By.xpath("//button[contains(.,'Back to Home')]");
	
	private By alert = By.xpath("//div[@role='alertdialog']");
	
	private By orderDetailsWindow = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr");
	
	private By productNameOrderDetailsWindow = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr/td[2]");
	
	private By productQuantityOrderDetailsWindow = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr/td[3]");
	
	private By productPriceOrderDetailsWindow = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr/td[4]");
	
	private By totalPriceOrderDetailsWindow = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr/td[5]");
	
	private By closeButtonOrderDetailsWindow = By.xpath("//button[contains(.,'Close')]");
	
	private By statusOrderDetailsWindow = By.xpath("//*[@role='dialog'] //b[contains(.,'Status')]/parent::h5");
	
	private By deliveryDateOrderDetailsWindow = By.xpath("//*[@role='dialog'] //b[contains(.,'Delivery')]/parent::h5");
	
	private By timeSlotOrderDetailsWindow = By.xpath("//*[@role='dialog'] //b[contains(.,'Time')]/parent::h5");
	
	private By orderNumberOrderDetailsWindow = By.xpath("//*[@role='dialog']//div[@class='row']/div[1]/b");
	
			
	public CustomerOrderListPage(WebDriver driver) {
		this.driver=driver;
	}

	public WebElement getOrderNoFilter() {
		return driver.findElement(orderNoFilter);
	}
	
	public WebElement getDeliveryDateFilter() {
		return driver.findElement(deliveryDateFilter);
	}
	
	public WebElement getStatusFilter() {
		return driver.findElement(statusFilter);
	}
	
	public WebElement getItemsPerPageFilter() {
		return driver.findElement(itemsPerPageFilter);
	}
	
	public WebElement getOrderListTable() {
		return driver.findElement(orderListTable);
	}
	
	public WebElement getOrderNoHeader() {
		return driver.findElement(orderNoHeader);
	}
	
	public WebElement getDeliveryDateHeader() {
		return driver.findElement(deliveryDateHeader);
	}
	
	public WebElement getSlotHeader() {
		return driver.findElement(slotHeader);
	}
	
	public WebElement getAmountHeader() {
		return driver.findElement(amountHeader);
	}
	
	public WebElement getStatusHeader() {
		return driver.findElement(statusHeader);
	}
	
	public WebElement getOrderNoDetails() {
		return driver.findElement(orderNoDetails);
	}
	
	public WebElement getDeliveryDateDetails() {
		return driver.findElement(deliveryDateDetails);
	}
	
	public WebElement getSlotDetails() {
		return driver.findElement(slotDetails);
	}
	
	public WebElement getAmountDetails() {
		return driver.findElement(amountDetails);
	}
	
	public WebElement getStatusDetails() {
		return driver.findElement(statusDetails);
	}
	
	public WebElement getShowOrderDetails() {
		return driver.findElement(showOrderDetails);
	}
	
	public WebElement getClearFiltersButton() {
		return driver.findElement(clearFilterButton);
	}
	
	public WebElement getRefreshButton() {
		return driver.findElement(refreshButton);
	}
	
	public WebElement getBackToHomeButton() {
		return driver.findElement(backToHomeButton);
	}
	
	public WebElement getAlert() {
		return driver.findElement(alert);
	}
	
	public WebElement getOrderDetailsWindow() {
		return driver.findElement(orderDetailsWindow);
	}
	
	public WebElement getProductNameOrderDetailsWindow() {
		return driver.findElement(productNameOrderDetailsWindow);
	}
	
	public WebElement getProductQuantityOrderDetailsWindow() {
		return driver.findElement(productQuantityOrderDetailsWindow);
	}
	
	public WebElement getProductPriceOrderDetailsWindow() {
		return driver.findElement(productPriceOrderDetailsWindow);
	}
	
	public WebElement getTotalPriceOrderDetailsWindow() {
		return driver.findElement(totalPriceOrderDetailsWindow);
	}
	
	public WebElement getCloseButtonOrderDetailsWindow() {
		return driver.findElement(closeButtonOrderDetailsWindow);
	}
	
	public WebElement getStatusOrderDetailsWindow() {
		return driver.findElement(statusOrderDetailsWindow);
	}
	
	public WebElement getDeliveryDateOrderDetailsWindow() {
		return driver.findElement(deliveryDateOrderDetailsWindow);
	}
	
	public WebElement getTimeSlotOrderDetailsWindow() {
		return driver.findElement(timeSlotOrderDetailsWindow);
	}
	
	public WebElement getOrderNumberOrderDetailsWindow() {
		return driver.findElement(orderNumberOrderDetailsWindow);
	}
	
	public List<WebElement> getDropdownOptions() {
		return driver.findElements(dropdownOptions);
	}
	
	public List<WebElement> getTotalSearchResults() {
		return driver.findElements(totalsearchResults);
	}
	
	public List<WebElement> getAllOrderStatusDetails() {
		return driver.findElements(allOrderStatusDetails);
	}
	
	public List<WebElement> getAllOrderNoDetails() {
		return driver.findElements(allOrderNoDetails);
	}
	
	public List<WebElement> getAllDeliveryDateDetails() {
		return driver.findElements(allDeliveryDateDetails);
	}
}
