package testngprogrammes;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
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

public class HighLightTypeWoSendKeysTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void highlightAndTypeWoSendKeysTest() throws InterruptedException {
		// ope nthe url
		driver.navigate().to("http://amazon.in");
		wait.until(ExpectedConditions.titleContains("Amazon.in"));
		// create an object for Actions class
		Actions act = new Actions(driver);

		// identify the search editbox
		WebElement srch_editbox = driver.findElement(By.id("twotabsearchtextbox"));
		// highlight the element
		Utility.highLightElement1(driver, srch_editbox);
		// type the value in search editbox
		Utility.setAttribute(srch_editbox, "value", "headphones");
		// Press Enter key on the element
		srch_editbox.sendKeys(Keys.ENTER);

		// press PAGEDOWN from keywboard
		act.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(3000);
		act.sendKeys(Keys.PAGE_UP).perform();
		Thread.sleep(5000);
		// scroll for element
		WebElement backTotop = driver.findElement(By.id("navBackToTop"));
		Utility.scrollForElement(driver, backTotop);
		Thread.sleep(3000);
		// perform click action using javascript click
		Utility.safeJavaScriptClick(driver, backTotop);

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
		Reporter.log("i am inside the @AfterClass ...");
		System.out.println("started executing the @AfterClass ..");
		if (driver != null) {
			driver.close();
		}
		System.out.println("Ended executing the @AfterClass ..");
	}

}