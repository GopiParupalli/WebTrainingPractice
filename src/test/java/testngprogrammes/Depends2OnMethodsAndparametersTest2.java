package testngprogrammes;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class Depends2OnMethodsAndparametersTest2 {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void linkedinHomePageTest() {
		Reporter.log("open the url in browser");
		driver.get("https://www.linkedin.com/home");
		Reporter.log("wait and verify the LinkedIn: Log In or Sign Up page title");
		String linkedinHomePgTitle = "LinkedIn: Log In or Sign Up";
		wait.until(ExpectedConditions.titleIs(linkedinHomePgTitle));
		AssertJUnit.assertEquals(driver.getTitle(), linkedinHomePgTitle);
		AssertJUnit.assertTrue(driver.getTitle().contains(linkedinHomePgTitle));
		Reporter.log("Click on Signin link in Linkedin Home page ");
		driver.findElement(By.cssSelector("a.nav__button-secondary")).click();
		String singInPgTitle = "LinkedIn Login, Sign in | LinkedIn";
		wait.until(ExpectedConditions.titleIs(singInPgTitle));
		AssertJUnit.assertEquals(driver.getTitle(), singInPgTitle);
		AssertJUnit.assertTrue(driver.getTitle().contains(singInPgTitle));

	}

	@Parameters({ "uname", "pwd" })
	@Test(dependsOnMethods = { "linkedinHomePageTest" }, alwaysRun = true)
	public void doLoginTest(String uname, String pwd) {

		Reporter.log("Started executing the doLoginTest()....");
		Reporter.log("Verify the Sign in Header text");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.header__content__heading ")));
		Reporter.log("type the username in username edibtox");
		driver.findElement(By.id("username")).sendKeys(uname);
		Reporter.log("type the pasword in passowrd edibtox");
		driver.findElement(By.name("session_password")).sendKeys(pwd);

		Reporter.log("click on login button");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Reporter.log("wait for the page tile- Feed | LinkedIn");
		wait.until(ExpectedConditions.titleContains("Feed | LinkedIn"));

	}

	@Test(dependsOnMethods = { "doLoginTest" })
	public void doLogoutTest() {
		////a[@href='/m/logout/']
		////a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]
		Reporter.log("Started executing the doLogoutTest()...");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*='feed-identity-module']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='feed-identity-module']")));
		Reporter.log("wait for the profile image icon");
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("img[class*='global-nav__me-photo ghost-person ember-view']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("img[class*='global-nav__me-photo ghost-person ember-view']")));
		Reporter.log("click on profile image icon");
		driver.findElement(By.cssSelector("img[class*='global-nav__me-photo ghost-person ember-view']")).click();
		WebElement signOutLink = driver
				.findElement(By.xpath("//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]"));
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		Reporter.log("click on logout link");
		signOutLink.click();
	}
	
	@BeforeClass
	public void beforeClass() {
		Reporter.log("@BeforeClass is current class level annotation");

		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();

		WebDriverManager.chromedriver().setup();

		Reporter.log("interface refvar=new implementedclass()");
		driver = new ChromeDriver();

		Reporter.log("maximize the window");
		driver.manage().window().maximize();

		Reporter.log("add implicit wait");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

		Reporter.log("open the url in browser");
		driver.get("https://google.com/");

		Reporter.log("create Object for WebDriverWait class");
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));
	}


	@AfterClass
	public void afterClass() {
		Reporter.log("close the browser in @AftreClass");
		if (driver != null) {
			driver.close();
		}
	}

}