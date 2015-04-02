package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuBar {
	
	public static void clickMenuItem (WebDriver driver, String menuText)
	{
		Actions action = new Actions(driver);
		String menuXpath = "//div[@class='row menu-wrapper']//ul[@id='nav']/li/a/span[text()='"+menuText+"']";
		
		WebElement menu = driver.findElement(By.xpath(menuXpath));
		action.moveToElement(menu).click().build().perform();
	}

	public static void clickSubMenuItem (WebDriver driver, String menuText, String subMenuText)
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String menuXpath = "//div[@class='row menu-wrapper']//ul[@id='nav']/li/a/span[text()='"+menuText+"']";
		String subMenuXpath = menuXpath + "/../..//a/span[text()='"+subMenuText+"']";
		
		WebElement menu = driver.findElement(By.xpath(menuXpath));
		action.moveToElement(menu).build().perform();
		WebElement subMenu = driver.findElement(By.xpath(subMenuXpath));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subMenuXpath)));
		action.moveToElement(subMenu).click().build().perform();
	}
}
