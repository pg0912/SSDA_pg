package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHomePage {

	public WebDriver driver;
	
	private By dashboard = By.xpath("//div[contains(text(),'DASHBOARD')]");
	
	public AdminHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public WebElement getDashboard()
	{
		return driver.findElement(dashboard);
	}
	
}
