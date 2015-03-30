package com.SupriyaTests.ThoseBabyBasics;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.SupriyaTests.ThoseBabyBasics.Structure.LoginPage;
import com.SupriyaTests.ThoseBabyBasics.Structure.MenuBar;
import com.SupriyaTests.ThoseBabyBasics.Structure.ToolBar;


public class FunctionalTest 
{
	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;
	public static Logger log = Logger.getLogger(FunctionalTest.class);
	public static String baseUrl = "https://www.thosebabybasics.com/en/";
	
	@BeforeClass
	public void setUp()
	{
		PropertyConfigurator.configure("log4j.properties");		
		log.info("Opening broswer");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.ie.driver", "C:\\Library\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		//System.setProperty("webdriver.chrome.driver", "C:\\Library\\Chromedriver.exe");
		//driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		driver.manage().window().maximize();
		log.info("Navigating to :"+baseUrl);
		driver.get(baseUrl);
	}
	
	@Test(enabled=true)
	public void login()
	{		
		log.info("Opening login page");
		ToolBar.clickLoginLink(driver);
		log.info("Entering email ID");
		LoginPage.fillEmailId(driver, "vamsichinta@yahoo.com");
		log.info("Entering password");
		LoginPage.fillPassword(driver, "vamsikrishna");		
		log.info("Clicking the Login button");
		LoginPage.clickLoginButton(driver);
	}
	
	@Test(dependsOnMethods={"login"})
	public void addBabyGirlItems()
	{
		log.info("Navigating to 0-2 Year Girl page");
		driver.navigate().to(baseUrl+"babyclothing/tops-pullovers.html");
		addToCart();
	}

	private void addToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fancybox-frame")));
		WebElement frame = driver.findElement(By.id("fancybox-frame"));
		driver.switchTo().frame(frame);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attribute76")));
		Select color = new Select(driver.findElement(By.id("attribute76")));
		color.selectByIndex(1);
		Select size = new Select(driver.findElement(By.id("attribute499")));
		size.selectByIndex(1);
		driver.findElement(By.xpath(".//*[@id='product_addtocart_form']/div[2]/div[4]/div[2]/button")).click();
		
		driver.switchTo().defaultContent();
	}
	
	@Test(dependsOnMethods={"login"}, enabled=true)
	public void addBabyBoyItems()
	{
		log.info("Navigating to Baby Boy pages");
		driver.navigate().to(baseUrl+"boys-babyclothing/socks.html");
		addToCart();
	}
	
	@Test(dependsOnMethods={"addBabyGirlItems","addBabyBoyItems"})
	public void editCart() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='top-cart']/a[1]")));
		driver.findElement(By.xpath(".//*[@id='top-cart']/a[1]")).click();
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr[1]/td[7]/a")).click();
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr[1]/td[7]/a")).click();
		Thread.sleep(5000);
	}
	
	@Test(dependsOnMethods={"editCart"}, enabled=true)
	public void logout()
	{
		log.info("Clicking the logout link");
		ToolBar.clickLogoutLink(driver);
	}
	
	@AfterClass
	public void tearDown()
	{
		log.info("Closing the browser");
		driver.close();
		driver.quit();
	}
}
