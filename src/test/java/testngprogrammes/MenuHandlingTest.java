package testngprogrammes;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class MenuHandlingTest {
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Test
	public void menuHandlingyTest() throws InterruptedException {
		// create Object for SoftAssert class
		SoftAssert sa = new SoftAssert();
		// open the url
		driver.get("https://jqueryui.com/menu/");
		wait.until(ExpectedConditions.titleContains("Menu | jQuery UI"));
		sa.assertTrue(driver.getPageSource().contains(driver.getTitle()));
		// switch to iframe based on position
		driver.switchTo().frame(0);

		// Create Object for Actions class
		Actions act = new Actions(driver);
		// identify the Music menu
		WebElement musicMenu = driver.findElement(By.id("ui-id-9"));
		sa.assertTrue(musicMenu.isDisplayed(), "music menu is not available");
		// move to the About element
		act.moveToElement(musicMenu).perform();

		// identify the Music menu
		WebElement rockSubMenu = driver.findElement(By.id("ui-id-10"));

		wait.until(ExpectedConditions.visibilityOf(rockSubMenu));
		sa.assertTrue(rockSubMenu.isDisplayed(), "rocksubmenu is not available");
		// move to the About element
		act.moveToElement(rockSubMenu).perform();

		// identify the Music ->rock-->classic submenu
		WebElement classicSubMenu = driver.findElement(By.id("ui-id-12"));

		wait.until(ExpectedConditions.visibilityOf(classicSubMenu));
		sa.assertTrue(classicSubMenu.isDisplayed(), "classic SubMenu is not available");
		// move to the About element
		// act.moveToElement(classicSubMenu).perform();
		act.click(classicSubMenu).perform();
		sa.assertAll();
		Thread.sleep(3000);

	}

	@Parameters({ "browser" })
	@BeforeClass
	public void setUp(String browser) {
		Reporter.log("started executing the @BeforeClass");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		Reporter.log("maxmize the window");
		driver.manage().window().maximize();

		Reporter.log("add implicitwait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("create object for WebDriverWait class");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@AfterClass
	public void afterClass() {
		// close the browser
		driver.close();
	}
}