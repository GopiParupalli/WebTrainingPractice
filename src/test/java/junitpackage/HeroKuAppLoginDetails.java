package junitpackage;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

class HeroKuAppLoginDetails {
	
	private static WebDriver driver=null;
	private static WebDriverWait wait=null;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		

		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();

		WebDriverManager.chromedriver().setup();

		// InterfaceVariablename=new classname();
		driver = new ChromeDriver();

		// Maximize the window
		driver.manage().window().maximize();

		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Create Webdriverwait object
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));
		
		// Launch the application url
		driver.get("http://the-internet.herokuapp.com/");
		
		// wait for the page title
		wait.until(ExpectedConditions.titleIs("The Internet"));

		Assertions.assertEquals("The Internet",driver.getTitle());
		Assertions.assertTrue(driver.getTitle().contains("The Internet"));
		
		// Verifying the page title
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.heading")));
		
		String headingTxt=driver.findElement(By.cssSelector("h1.heading")).getText();
		Assertions.assertEquals("Welcome to the-internet", headingTxt);
		
		//Click on FormAuthentication Link
		driver.findElement(By.linkText("Form Authentication")).click();

		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if(driver!=null) {
			driver.quit();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		
		//verify the login page address bar url and page title
		wait.until(ExpectedConditions.urlContains("http://the-internet.herokuapp.com/login"));
		
		//wait for the page heading
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='example']>h2")));
		
		//header text Loginpage
		String loginpageheadertxt=driver.findElement(By.cssSelector("div[class='example']>h2")).getText();
		
		//using assertEquals()
		Assertions.assertEquals("Login Page", loginpageheadertxt);
		
		// using assertTrue()
		Assertions.assertTrue(driver.getPageSource().contains(loginpageheadertxt));
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		// cleanup the cookies
		driver.manage().deleteAllCookies();
	}

	@Test
	void testLoginWithValidCredentials() {
		System.out.println("started executing the testWithValidCredentials()....");
		doLogin("tomsmith", "SuperSecretPassword!");
		
		// wait for the http://the-internet.herokuapp.com/secure
		wait.until(ExpectedConditions.urlToBe("http://the-internet.herokuapp.com/secure"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='flash-messages']/div")));
		
		// waiting for the secureArea headertext
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content>div>h2")));
		
		// assertTrue
		Assertions.assertTrue(driver.getPageSource().contains("Secure Area"));
		
		// click on logout button
		driver.findElement(By.cssSelector("a.button.secondary.radius")).click();
	}

	@Test
	public void testValidUserNameInvalidPassword() {
		System.out.println("started executing the testValidUserNameInvalidPassword()....");
		doLogin("tomsmith", "abc123");
		verifyErrorMessage("Your password is invalid!");
	}
	
	@Test
	public void testInvalidUserNameAndValidPassword() {
		System.out.println("started executing the testInvalidUserNameAndValidPassword()....");
		doLogin("abc@2345678", "SuperSecretPassword!");
		verifyErrorMessage("Your username is invalid!");
	}
	
	@Test
	public void testWithInvalidCrdentials() {
		System.out.println("started executing the testWithInvalidCrdentials()....");
		doLogin("abc123", "04923789!@aS");
		verifyErrorMessage("Your username is invalid!");
	}
	

 //this method is used to do a login using username and password

	private void doLogin(String username, String pwd) {
		
		// type the username value in usernameTextbox
		WebElement usernameeditbox=driver.findElement(By.id("username"));
		usernameeditbox.clear();
		usernameeditbox.sendKeys(username);
		
		// type the password in passwordtextbox
		WebElement passwordEditBox=driver.findElement(By.name("password"));
		passwordEditBox.clear();
		passwordEditBox.sendKeys(pwd);
		
		// click on Login button
		driver.findElement(By.className("radius")).click();
	}
	
	private void verifyErrorMessage(String msg) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.flash.error")));
		Assertions.assertTrue(driver.getPageSource().contains(msg));
	}
	
	

}