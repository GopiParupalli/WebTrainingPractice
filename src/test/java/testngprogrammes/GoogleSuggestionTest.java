package testngprogrammes;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
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

public class GoogleSuggestionTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void googleSuggestionTest() throws InterruptedException {
		// open the google.com
		driver.get("https://google.com");
		wait.until(ExpectedConditions.titleIs("Google"));
		Thread.sleep(2000);
		// type the selenium keyword in search editbox.
		WebElement searcheditbox = driver.findElement(By.name("q"));
		searcheditbox.sendKeys("selenium", Keys.BACK_SPACE, "m");

		String expKeyWord = "selenium webdriver";

		String suggestion;
		do {
			searcheditbox.sendKeys(Keys.ARROW_DOWN);

			suggestion = searcheditbox.getAttribute("value");
			Thread.sleep(2000);
			if (suggestion.equals(expKeyWord)) {
				searcheditbox.sendKeys(Keys.ENTER);
				break;
			}
			Thread.sleep(1000);
		} while (!suggestion.isEmpty());

		Thread.sleep(1000);
		// verify the search results count text is present in the webpage or not
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
		// fetch the search results count text and extract the only count from search
		// results count text.
		String txt = driver.findElement(By.xpath("//div[@id='result-stats']")).getText();
		System.out.println("search results text is-->" + txt);
		// String txt="About 3,99,00,000 results (0.59 seconds) ";
		String[] str = txt.split(" ");
		// str[]=["About","3,99,00,000","results","(0.59","seconds)"]
		System.out.println("selenium results count is-->" + str[1]);

	}

	@BeforeMethod
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
		
			//System.setProperty("webdriver.gecko.driver","/Users/Gopireddyparupalli/Desktop/Selenium2022/Executable/geckodriver");
			//FirefoxOptions opt = new FirefoxOptions();
			//opt.setBinary("");
			//Interface refvar=new implementedclass();
			//driver = new FirefoxDriver(opt);

		} else if (browser.equalsIgnoreCase("edge")) {
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