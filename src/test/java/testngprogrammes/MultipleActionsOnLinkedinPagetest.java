package testngprogrammes;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class MultipleActionsOnLinkedinPagetest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void compositeActionsTest() throws InterruptedException {
		// open the url
		driver.navigate().to("http://linkedin.com/home");
		wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));

		// click on Sign in link
		driver.findElement(By.linkText("Sign in")).click();

		wait.until(ExpectedConditions.titleContains("LinkedIn Login, Sign in | LinkedIn"));

		// identify the email editbox
		WebElement email_editbox = driver.findElement(By.id("username"));

		// create an object for Actions class
		Actions act = new Actions(driver);

		// composite actions
		Action cmpActions = act.click(email_editbox).keyDown(Keys.SHIFT).sendKeys(email_editbox, "selenium")
				.keyUp(Keys.SHIFT).doubleClick(email_editbox).contextClick().build();
		cmpActions.perform();
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