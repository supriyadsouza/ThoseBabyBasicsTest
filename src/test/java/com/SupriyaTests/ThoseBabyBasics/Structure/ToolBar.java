package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToolBar {
	public static void clickLoginLink(WebDriver driver)
	{
		driver.findElement(By.xpath(".//*[@id='header-links']/ul/li[6]/a")).click();
	}

	public static void clickLogoutLink(WebDriver driver)
	{
		driver.findElement(By.xpath(".//*[@id='header-links']/ul/li[6]/a")).click();
	}
}
