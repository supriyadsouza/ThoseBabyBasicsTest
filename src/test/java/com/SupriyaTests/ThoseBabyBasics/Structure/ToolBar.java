package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToolBar {
	public static void clickLoginLink(WebDriver driver)
	{
		driver.findElement(By.xpath(".//*[@id='header-links']//a[@title='Log In']")).click();
	}

	public static void clickLogoutLink(WebDriver driver)
	{
		driver.findElement(By.xpath(".//*[@id='header-links']//a[@title='Log Out']")).click();
	}
}
