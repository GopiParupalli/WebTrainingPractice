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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class SelectSuggestionBykeysTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	/**
	 * 1)open the https://twoplugs.com url 
	 * 2)click on Live posting 
	 * 3)Type Toronto in searcheditbox 
	 * 4)select Toronto, OH, USA value from suggestions
	 * @throws InterruptedException
	 */
	@Test
	public void autoSuggestionBykeysTest() throws InterruptedException {
		// open the google.com
		driver.get("https://twoplugs.com");
		wait.until(ExpectedConditions.titleIs("twoPLUGS - A plug for your Service and another for your Need"));
		Thread.sleep(2000);
		// click on Live posting
		driver.findElement(By.xpath("//a[normalize-space()='Live Posting']")).click();

		// identify the suggestion box
		WebElement searcheditbox = driver.findElement(By.id("autocomplete"));
		// clear the content in searcheditbox
		searcheditbox.clear();
		// Type Toronto in searcheditbox
		searcheditbox.sendKeys("Toronto");
		Thread.sleep(2000);
		String suggestion;
		do {
			searcheditbox.sendKeys(Keys.ARROW_DOWN);

			suggestion = searcheditbox.getAttribute("value");
			Thread.sleep(2000);
			if (suggestion.equals("Toronto, OH, USA")) {
				searcheditbox.sendKeys(Keys.ENTER);
				break;
			}
			Thread.sleep(2000);
		} while (!suggestion.isEmpty());

	}

	@Parameters({ "browser" })
	@BeforeClass
	public void setUp(String browser) {
		Reporter.log("started executing the @BeforeClass");
		if (browser.equalsIgnoreCase("chrome")) {
			// set the chromedriver.exe file path System.setproperty(p1,p2)or
			// WebDriverManager.chromedriver().setup();

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			WebDriver driver=new FirefoxDriver();
			
			//System.setProperty("webdriver.gecko.driver","/Users/Gopireddyparupalli/Desktop/Selenium2022/Executable/geckodriver");
			// create Object for FirefoxOptions
			//FirefoxOptions opt = new FirefoxOptions();
			//opt.setBinary("/Applications/Firefox.app");
			// interface refvar=new implementedclass();
			//driver = new FirefoxDriver(opt);

		} else if (browser.equalsIgnoreCase("edge")) {
			
			//WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver","/Users/Gopireddyparupalli/Desktop/Selenium2022/Executable/edgedriver_mac64/msedgedriver");

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