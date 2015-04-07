package com.SupriyaTests.ThoseBabyBasics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SupriyaTests.ThoseBabyBasics.Structure.Cart;
import com.SupriyaTests.ThoseBabyBasics.Structure.ItemNotInCartException;
import com.SupriyaTests.ThoseBabyBasics.Structure.LoginPage;
import com.SupriyaTests.ThoseBabyBasics.Structure.MenuBar;
import com.SupriyaTests.ThoseBabyBasics.Structure.ToolBar;


public class FunctionalTest 
{
	public WebDriver driver;
	public static Logger log = Logger.getLogger(FunctionalTest.class);
	
	private String baseUrl;
	private String email;
	private String password;
	private static int noOfItemsInCart = 1;
	
		
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) throws IOException
	{
		PropertyConfigurator.configure("log4j.properties");		
		log.info("Opening browser");
		
		if (browser=="chrome")
		{
			System.setProperty("webdriver.chrome.driver", "Resources\\Chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browser=="ie")
		{
			System.setProperty("webdriver.ie.driver", "Resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
			driver = new FirefoxDriver();
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("Resources\\login.properties");
		prop.load(fis);
		baseUrl = prop.getProperty("baseurl");
		email = prop.getProperty("email");
		password = prop.getProperty("password");
		fis.close();

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
		LoginPage.fillEmailId(driver, email);
		log.info("Entering password");
		LoginPage.fillPassword(driver, password);		
		log.info("Clicking the Login button");
		LoginPage.clickLoginButton(driver);
		
		Assert.assertTrue(driver.getPageSource().contains("Welcome,"));
	}
	
	@DataProvider
	private Object[][] getItemtoAdd ()
	{
		Object[][] data = new Object[2][6];
		data[1][0] = "0-2 Year   Girl"; data[1][1] = "Tops / Pullovers"; data[1][2] = "Girls Top"; data[1][3] = "Soft Pink"; data[1][4]="18-24 months"; data[1][5]="3";
		data[0][0] = "0-2 Year   Boy"; data[0][1] = "Socks"; data[0][2] = "Socks Set"; data[0][3] = "Navy"; data[0][4]="6-12 months"; data[0][5]="2";
		return data;
	}
	
	@Test(enabled=true, dataProvider="getItemtoAdd", dependsOnMethods="login")
	public void addBabyItem(String menuText, String subMenuText, String itemText, String colorText, String sizeText, String qtyText)
	{
		//TODO: Read test data from Excel file
		log.info("Navigating to menu option: "+menuText+ " > "+subMenuText);
		MenuBar.clickSubMenuItem(driver, menuText, subMenuText);
		log.info("Adding item to the cart: "+itemText+" -- "+colorText+" -- "+sizeText);
		Cart.addItem(driver, itemText, colorText, sizeText, qtyText);
		noOfItemsInCart++;
		log.info("Number of items in cart: "+noOfItemsInCart);
	}
	
	@DataProvider
	private Object[][] getItemtoRemove ()
	{
		Object data [][] = new Object[1][1];
		data[0][0] = "Socks Set";
		return data;
	}
	
	@Test(enabled=true, dataProvider="getItemtoRemove", dependsOnMethods={"addBabyItem"})
	public void removeItemFromCart(String itemText)
	{
		log.info("Navigating to the shopping cart page");
		Cart.openCart(driver);
		log.info("Removing item from shooping cart: "+itemText);
		try
		{
			Cart.removeItem(driver, itemText);
		} 
		catch (ItemNotInCartException e) 
		{
			e.printStackTrace();
			Assert.fail();
		}
		noOfItemsInCart--;
		Cart.openCart(driver);
		log.info("Number of items in cart: "+noOfItemsInCart);
	}
	
	@Test(dependsOnMethods="removeItemFromCart", enabled=true)
	public void logout()
	{
		log.info("Clicking the logout link");
		ToolBar.clickLogoutLink(driver);
		
		Assert.assertFalse(driver.getPageSource().contains("Welcome,"));
	}
	
	@AfterClass
	public void tearDown()
	{
		log.info("Closing the browser");
		driver.close();
		driver.quit();
	}
}
