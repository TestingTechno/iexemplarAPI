/*package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

*//**
 * @author Arun
 *
 *//*

public class Browser {
	
	public WebDriver webDriver;
	
	public Browser(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public void launcBrowser(String id) throws InterruptedException
	{
		webDriver = new FirefoxDriver();
		webDriver.get("http://www.yopmail.com/en/");
		webDriver.findElement(By.xpath("//input[@id='login']")).sendKeys(id);
		webDriver.findElement(By.xpath("//input[@id='login']")).click();
		Thread.sleep(Long.valueOf(10));
		String token = webDriver.findElement(By.xpath("//div[@id='mailmillieu']//a[contains(@href,token)]")).getAttribute("href");
		
	}
	
}*/