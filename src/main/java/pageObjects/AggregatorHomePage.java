package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AggregatorHomePage {

       public WebDriver driver;

       private By pending = By.xpath("//div[@role='tablist']//div[text()='Pending']");

       private By ready = By.xpath("//div[contains(text(),'Ready')]//parent::div[@role='tab']");

       private By completed = By.xpath("//div[@role='tablist']//div[text()='Completed']");

       private By lapsed = By.xpath("//div[@role='tablist']//div[text()='Lapsed']");

       private By canceled = By.xpath("//div[@role='tablist']//div[text()='Canceled']");

       private By clearFilters = By.xpath("//*[text()=' Clear Filters ']");

       private By updateData = By.xpath("//*[text()=' Update Data ']");

       private By navigationBar = By.xpath("//div[@id='navbarText']']");

       private By userMenu = By.xpath("//a[@id='dropdown']");

       private By changeEmailLink = By.xpath("//a[text()='Change Email/Password']");

       private By logoutLink = By.xpath("//a[text()='Logout']");

       private By orderId = By.xpath("//table[@role='grid']/tbody/tr[1]/td[1]");
       private By orderHeader = By.xpath("//table[@role='grid']/thead/tr[1]//button[text()=' ORDER ID ']");
       private By customerName = By.xpath("//table[@role='grid']/tbody/tr/td[2]");
       //table[@role='grid']/thead/tr[1]//button[text()=' CUSTOMER NAME ']
       private By timeSlot = By.xpath("//table[@role='grid']/tbody/tr/td[3]");
       //table[@role='grid']/thead/tr[1]//button[text()=' TIME SLOT ']
       private By date = By.xpath("//table[@role='grid']/tbody/tr/td[4]");
       //table[@role='grid']/thead/tr[1]//button[text()=' DATE ']
       private By contact = By.xpath("//table[@role='grid']/tbody/tr/td[5]");
       //table[@role='grid']/thead/tr[1]/th[text()=' CONTACT ']
       private By actions = By.xpath("//table[@role='grid']/tbody/tr/td[6]/button");             
       //table[@role='grid']/tbody /tr[1]/td[6]/button
       private By orderDetails = By.xpath("//app-order-detail/h2");

       private By orderNoDetails = By.xpath("//div[@class='row']//div[contains(.,'details')]");

       private By status = By.xpath("//div[@class='row']/div/h5[contains(.,'Status:')]/span");

       private By completedOrderStatus = By.xpath("//div/h5/span[@class='COMPLETED']");

       private By readyOrderStatus = By.xpath("//div/h5/span[@class='READY_TO_DELIVER']");

       private By pendingOrderStatus = By.xpath("//div/h5/span[@class='INITIATED']");

       private By lapsedOrderStatus = By.xpath("//div/h5/span[@class='LAPSED']");

       private By canceledOrderStatus = By.xpath("//div/h5/span[@class='CANCEL_BY_ADMIN']");

       private By deliveryDate = By.xpath("//div[@class='row']/div/h5[contains(.,'Delivery Date:')]");

       private By timeSlotOrderDetails = By.xpath("//*[@role='dialog'] //h5[contains(.,'Time Slot:')] //parent::div/h5/a");

       //private By timeSlotOrderDetails = By.cssSelector("a[class='timeslot']");

       private By amountPayable = By.xpath("//div/p[contains(.,'Amount Payable')]");

       private By customerNameOrderDetails = By.xpath("//h5[contains(.,'Customer Name:')]");

       private By customerContactOrderDetails = By.xpath("//h5[contains(.,'Customer Contact:')]");                         

       private By currency = By.xpath("//h1[contains(.,'₹')]");

       private By aggregatorName = By.xpath("//h5[contains(.,'Aggregator Name')]");

       private By aggregatorContact = By.xpath("//h5[contains(.,'Aggregator Contact:')]");                    

       private By srNo = By.xpath("//table[@class='mat-table cdk-table']//th[1]");

       private By itemNameHeader = By.xpath("//table[@class='mat-table cdk-table']//th[2]");

       private By allItemNames = By.xpath("//table[@class='mat-table cdk-table']/tbody/tr/td[2]");
    
       private By itemName = By.xpath("//*[@role='dialog']//table[@role='grid']/tbody/tr");

       private By quantity = By.xpath("//tbody/tr[1]/td[3]/input[1]");

       private By removeText = By.xpath("//table[@class='mat-table cdk-table']//th[3]/span");

       private By price = By.xpath("//table[@class='mat-table cdk-table']//th[4]");

       private By amount = By.xpath("//table[@class='mat-table cdk-table']//th[5]");

       private By plusButton = By.xpath("//table[@class='mat-table cdk-table'] /tbody/tr/td[3]/button[2]");

       private By minusButton = By.xpath("//table[@class='mat-table cdk-table'] /tbody/tr/td[3]/button[1]");

       private By removeButton = By.xpath("//table[@class='mat-table cdk-table'] /tbody/tr[1]/td[3]/ng-conatiner/button");                           

       private By readyForDeliveryButton = By.xpath("//span[contains(.,'Ready for Delivery')]//parent::button");

       private By orderDetailsCloseButton = By.xpath("//span[contains(.,'Close')]");

       private By storeName = By.xpath( "//span[contains(.,' Store: ')]");

       private By selectTimeSlotText = By.xpath("//*[@class='ng-star-inserted']/h3");

       private By changeTimeSlotDate = By.xpath("//div[@class='cdk-global-overlay-wrapper']//div[2]//mat-card[1]//h4");

       private By changeTimeSlotTime = By.xpath("//div[@class='cdk-global-overlay-wrapper']//div[2]//mat-card[1]//button[1]");

       private By changeTimeSlotSubmitButton = By.xpath("//span[contains(.,'Submit')]");

       private By changeTimeCancelButton = By.xpath("//span[contains(.,'Cancel')]");

       private By searchByCustomerName = By.xpath("//input[@placeholder='Customer Name']");

       private By searchByOrderNumber = By.xpath("//input[@placeholder='Order Number']");

       private By searchByDeliveryDate = By.xpath("//span[contains(.,'Delivery Date')]//parent::div/input");

       private By itemsPerPageDropDown = By.xpath("//*[@class='cdk-overlay-pane'] //mat-option");

       private By itemsPerPage = By.xpath("//mat-select[@aria-label='Items per page:']");

       private By alert = By.xpath("//div[@role='alertdialog']");

       private By cancelTheOrderText = By.xpath("//app-cancel-order-dialog[@class='ng-star-inserted']/h2");

       private By cancelTheOrderButton = By.xpath("//button/span[contains(.,'Cancel')]");

       private By proceedButton = By.xpath("//button/span[contains(.,'Proceed')]");

       private By closeButton = By.xpath("//button[contains(.,'Close')]");
       
       private By pageLoading = By.xpath("//p[contains(.,'Please Wait.')]");


       public AggregatorHomePage(WebDriver driver) {
             this.driver=driver;
       }

       public WebElement getPendingOrders(){
             return driver.findElement(pending);
       }
       
       public WebElement getItemName(){
           return driver.findElement(itemName);
     }

       public WebElement getPageLoading(){
             return driver.findElement(pageLoading);
       }

       
       public WebElement getReadyOrders(){
             return driver.findElement(ready);
       }


       public WebElement getCompletedOrders(){
             return driver.findElement(completed);
       }


       public WebElement getLapsedOrders(){
             return driver.findElement(lapsed);
       }

       public WebElement getCanceledOrders(){
             return driver.findElement(canceled);
       }

       public WebElement getUpdateDataButton(){
             return driver.findElement(updateData);
       }

       public WebElement getClearFiltersButton(){
             return driver.findElement(clearFilters);
       }

       public WebElement getNavigationBarButton(){
             return driver.findElement(navigationBar);
       }

       public WebElement getUserMenu(){
             return driver.findElement(userMenu);
       }

       public WebElement getChangeEmailLink(){
             return driver.findElement(changeEmailLink);
       }

       public WebElement getOrderHeader(){
             return driver.findElement(orderHeader);
       }

       public WebElement getLogOutLink(){
             return driver.findElement(logoutLink);
       }


       public WebElement getOrderID(){
             return driver.findElement(orderId);
       }
       public WebElement getCustomerName(){
             return driver.findElement(customerName);
       }


       public WebElement getTimeSlot(){
             return driver.findElement(timeSlot);
       }

       public WebElement getDate(){
             return driver.findElement(date);
       }

       public WebElement getContact(){
             return driver.findElement(contact);
       }

       public WebElement getActions(){
             return driver.findElement(actions);
       }

       public WebElement getOrderDetails(){
             return driver.findElement(orderDetails);
       }

       public WebElement getOrderNoDetails(){
             return driver.findElement(orderNoDetails);
       }

       public WebElement getStatus(){
             return driver.findElement(status);
       }

       public WebElement getDeliveryDate(){
             return driver.findElement(deliveryDate);
       }

       public WebElement getTimeSlotOrderDetails(){
             return driver.findElement(timeSlotOrderDetails);
       }

       public WebElement getAmountPayable(){
             return driver.findElement(amountPayable);
       }

       public WebElement getCustomerNameOrderDetails(){
             return driver.findElement(customerNameOrderDetails);
       }

       public WebElement getCustomerContactOrderDetails(){
             return driver.findElement(customerContactOrderDetails);
       }

       public WebElement getCurrency(){
             return driver.findElement(currency);
       }

       public WebElement getAggregatorName(){
             return driver.findElement(aggregatorName);
       }

       public WebElement getAggregatorContact(){
             return driver.findElement(aggregatorContact);
       }             

       public WebElement getSrNo(){
             return driver.findElement(srNo);
       }             

       public WebElement getItemNameHeader(){
             return driver.findElement(itemNameHeader);
       }


       public List<WebElement> getAllItemNames(){
             return driver.findElements(allItemNames); //return list of elements
       }

       public WebElement getQuantity(){
             return driver.findElement(quantity);
       }

       public WebElement getRemoveText(){
             return driver.findElement(removeText);
       }

       public WebElement getPrice(){
             return driver.findElement(price);
       }

       public WebElement getAmount(){
             return driver.findElement(amount);
       }

       public WebElement getPlusButton(){
             return driver.findElement(plusButton);
       }

       public WebElement getMinusButton(){
             return driver.findElement(minusButton);
       }

       public WebElement getRemoveButton(){
             return driver.findElement(removeButton);
       }

       public WebElement getReadyForDeliveryButton(){
             return driver.findElement(readyForDeliveryButton);
       }

       public WebElement getOrderDetailsCloseButton(){
             return driver.findElement(orderDetailsCloseButton);
       }

       public WebElement getStoreName(){
             return driver.findElement(storeName);
       }

       public WebElement getSelectTimeSlotText(){
             return driver.findElement(selectTimeSlotText);
       }

       public WebElement getChangeTimeSlotDate(){
             return driver.findElement(changeTimeSlotDate);
       }

       public WebElement getChangeTimeSlotTime(){
             return driver.findElement(changeTimeSlotTime);
       }

       public WebElement getChangeTimeSlotSubmitButton(){
             return driver.findElement(changeTimeSlotSubmitButton);
       }

       public WebElement getChangeTimeCancelButton(){
             return driver.findElement(changeTimeCancelButton);
       }

       public WebElement getSearchByCustomerName(){
             return driver.findElement(searchByCustomerName);
       }

       public WebElement getSearchByOrderNumber(){
             return driver.findElement(searchByOrderNumber);
       }

       public WebElement getSearchByDeliveryDate(){
             return driver.findElement(searchByDeliveryDate);
       }

       public WebElement getItemsPerPageDropDown(){
             return driver.findElement(itemsPerPageDropDown);
       }

       public WebElement getItemsPerPage(){
             return driver.findElement(itemsPerPage);
       }

       public WebElement getAlert() {
             return driver.findElement(alert);
       }

       public WebElement getCancelTheOrderText(){
             return driver.findElement(cancelTheOrderText);
       }
       public WebElement getCancelTheOrderButton(){
             return driver.findElement(cancelTheOrderButton);
       }

       public WebElement getProceedButton(){
             return driver.findElement(proceedButton);
       }

       public WebElement getCloseButton(){
             return driver.findElement(closeButton);
       }

       public WebElement getPendingOrderStatus(){
             return driver.findElement(pendingOrderStatus);
       }

       public WebElement getReadyOrderStatus(){
             return driver.findElement(readyOrderStatus);
       }

       public WebElement getCanceledOrderStatus(){
             return driver.findElement(canceledOrderStatus);
       }
       public WebElement getCompletedOrderStatus(){
             return driver.findElement(completedOrderStatus);
       }
       public WebElement getLapsedOrderStatus(){
             return driver.findElement(lapsedOrderStatus);
       }
}








