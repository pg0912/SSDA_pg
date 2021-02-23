package resources;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	public Select select;
	public Actions actions;

	//To Select a value from Drop Down by using SelectByVisibleText Method.
	public void selectValueFromDropDownByText(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByVisibleText(value);
	}

	//To Select a value from Drop Down by using SelectByIndex Method.
	public void selectValueFromDropDownByIndex(WebElement element, int value) 
	{
		select = new Select(element);
		select.selectByIndex(value - 1);
	}

	//To Select a value from Drop Down by using SelectByValue Method.
	public void selectValueFromDropDownByValue(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByValue(value);
	}

	//To get Selected value from Drop Down by using FirstSelectedOption Method.
	public String getSelectedOption(WebElement element) 
	{
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	//To get number of options in Drop Down by using getOptions Method.
	public int getDropdownSize(WebElement element) 
	{
		select = new Select(element);
		return select.getOptions().size();
	}

	//To perform Double Click action using Actions Class.
	public void doubleClick(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	//To Click on Element using Actions Class.
	public void clickOnElementUsingActions(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	//To Mouse Hover and Click or Select an Element using Actions Class.
	public void moveToElement(WebDriver driver, WebElement element) 
	{
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	//Explicit Wait for All Elements To Be Invisible.
	public void waitForAllElementsToBeInvisible(WebDriver driver, List<WebElement> elementsList, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.invisibilityOfAllElements(elementsList));
	}
	
	//Explicit Wait for Element To Be Invisible.
	public void waitForElementToBeInvisible(WebDriver driver, WebElement element, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.invisibilityOf(element));
	}
	
	//Explicit Wait for Element To Be Visible.
	public void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getDate() {
		 
		 // Create object of SimpleDateFormat class and decide the format
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String date1= dateFormat.format(date);
		 
		 return date1;
		 
		 }
		 
	
}
