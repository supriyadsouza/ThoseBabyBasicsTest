package com.SupriyaTests.ThoseBabyBasics.Structure;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {
	public static void addItem (WebDriver driver, String itemText, String colorText, String sizeText, String qtyText)
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		String itemXpath = "//div[@class='category-products']//a[text()='"+itemText+"']";
		String buttonAddToCartXpath = itemXpath+"/../../div/button";
		
		WebElement buttonAddToCart = driver.findElement(By.xpath(buttonAddToCartXpath));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonAddToCartXpath)));
		buttonAddToCart.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fancybox-frame")));
		WebElement frame = driver.findElement(By.id("fancybox-frame"));
		driver.switchTo().frame(frame);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attribute76")));
		Select color = new Select(driver.findElement(By.id("attribute76")));
		color.selectByVisibleText(colorText);
		Select size = new Select(driver.findElement(By.id("attribute499")));
		size.selectByVisibleText(sizeText);
		WebElement qty = driver.findElement(By.id("qty")); 
		qty.clear();
		qty.sendKeys(qtyText);
		driver.findElement(By.xpath(".//*[@id='product_addtocart_form']//button")).click();		
		driver.switchTo().defaultContent();
	}
	
	public static void removeItem (WebDriver driver, String itemText) throws ItemNotInCartException
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Actions action = new Actions(driver);
			String itemXpath = ".//*[@id='shopping-cart-table']/tbody//a[text()='"+itemText+"']";
			driver.findElement(By.xpath(itemXpath));
			String btnDeleteXpath = itemXpath+"/../../..//a[@class='btn-remove btn-remove2']";
			WebElement btnDelete = driver.findElement(By.xpath(btnDeleteXpath));
			action.moveToElement(btnDelete).click().build().perform();
			//btnDelete.click();
			//driver.navigate().refresh();
		}
		catch (NoSuchElementException e)
		{
			throw new ItemNotInCartException("Item is not in the shopping cart: "+itemText);
		}
	}
	
	public static void openCart (WebDriver driver)
	{
		WebElement cart = driver.findElement(By.xpath("//*[@id='top-cart']/a[1]/img"));
		cart.click();
	}
}
