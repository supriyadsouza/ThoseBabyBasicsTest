package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuBar {
	
	public static WebElement clickMenuItem (WebDriver driver, String menuText)
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//this does not work
		WebElement toolbaritem = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@id='nav']/li/a/span[text()='"+menuText+"'])")));
		
		//this works
		//WebElement toolbaritem = wait.until(
		//		ExpectedConditions.visibilityOfElementLocated(
		//				By.xpath("(//ul[@id='nav']/li/a/span)[9]")));
		
		action.moveToElement(toolbaritem).build().perform();
		return toolbaritem;
	}

	public static void clickSubMenuItem (WebDriver driver, String menuText, String subMenuText)
	{
		WebElement toolbaritem = clickMenuItem(driver, menuText);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait((WebDriver) toolbaritem, 10);
		WebElement category = toolbaritem.findElement(By.xpath(".//span[text()='"+subMenuText+"']"));
		wait.until(ExpectedConditions.elementToBeClickable(category));
		action.moveToElement(category).click().build().perform();
	}
}
