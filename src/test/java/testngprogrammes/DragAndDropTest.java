package testngprogrammes;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class DragAndDropTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	/**
	 * 1)open the https://jqueryui.com/droppable/ 2)switch to iframe 3)identify the
	 * source & target elements 4)create object for Actions class 5)then call
	 * dragAndDrop(src,tgt).perform()
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void autoSuggestionBykeysTest() throws InterruptedException {
		// open the google.com
		driver.get("https://jqueryui.com/droppable/");
		wait.until(ExpectedConditions.titleIs("Droppable | jQuery UI"));

		// switch to Iframe
		driver.switchTo().frame(0);
		// driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		// identify the source
		WebElement src = driver.findElement(By.id("draggable"));
		// identify the target element
		WebElement tgt = driver.findElement(By.id("droppable"));

		// create object for Actions class
		Actions act = new Actions(driver);
		// drag the source element to target element then release
		// act.dragAndDrop(src, tgt).perform();
		// click and hold the source element
		act.clickAndHold(src).perform();
		// move the source element to target element
		act.moveToElement(tgt).perform();
		// release the mouse at target element
		act.release().perform();

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
			driver=new FirefoxDriver();

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
