package testngprogrammes;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
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

public class RightClickOperationTest {
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Test
	public void rightClickOperationTest() throws InterruptedException {
		// open the url
		driver.get("https://google.com/");
		wait.until(ExpectedConditions.titleContains("Google"));
		Assert.assertTrue(driver.getPageSource().contains(driver.getTitle()));
		// identify the About element
		WebElement aboutLink = driver.findElement(By.linkText("About"));

		String tab = Keys.chord(Keys.CONTROL, Keys.RETURN);

		String newWindow = Keys.chord(Keys.SHIFT, Keys.ENTER);
		// openning link in new tab
		aboutLink.sendKeys(tab);

		Thread.sleep(5000);

		aboutLink.sendKeys(newWindow);

		/*
		 * //Create Object for Actions class Actions act=new Actions(driver); //1.move
		 * to the About element act.moveToElement(aboutLink).perform();
		 * 
		 * //2. right click on aboutLink then Press ARROW_DOWN key Then PRESS
		 * RETURN/ENTER key
		 * act.contextClick(aboutLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).
		 * build().perform(); //open about link in new window
		 * //act.contextClick(aboutLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.
		 * ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		 */
		Thread.sleep(5000);

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
		Reporter.log("i am inside the @AfterClass ...");
		System.out.println("started executing the @AfterClass ..");
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Ended executing the @AfterClass ..");
	}

}