package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	public static void fillEmailId (WebDriver driver, String emailId)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(emailId);
	}
	
	public static void fillPassword (WebDriver driver, String password)
	{
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	}

	public static void clickLoginButton (WebDriver driver)
	{
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
	}
}
