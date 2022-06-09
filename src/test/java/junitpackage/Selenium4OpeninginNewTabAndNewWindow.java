package junitpackage;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class Selenium4OpeninginNewTabAndNewWindow {
	private static WebDriver driver = null;
	private static WebDriverWait wait = null;
	String pid=null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//System.setProperty("webdriver.chrome.driver","D:\\webdriverjars\\executables\\chrome99\\chromedriver_win32\\chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();
		// interface variablename=new classname();
		driver = new ChromeDriver();

		// maximize the window
		driver.manage().window().maximize();

		// add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// create WebDriverWaitobject
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));

		// launch the application url
		driver.get("http://the-internet.herokuapp.com/");
		// wait for the page title
		wait.until(ExpectedConditions.titleContains("The Internet"));
		Assertions.assertEquals("The Internet", driver.getTitle());
		Assertions.assertTrue(driver.getTitle().contains("The Internet"));
		// verify the page heading
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.heading")));
		String headingTxt = driver.findElement(By.cssSelector("h1.heading")).getText();
		Assertions.assertEquals("Welcome to the-internet", headingTxt);
		// Click on Multiple Windows link
		driver.findElement(By.partialLinkText("Multiple Windows")).click();

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testOpenNewTab() throws InterruptedException {
		System.out.println(" testOpenNewTab()::::");
		// Verify the login page Addressbar url and page heading
		wait.until(ExpectedConditions.urlContains("/windows"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='example']>h3")));
		String windowheadertxt = driver.findElement(By.cssSelector("div[class='example']>h3")).getText();
		Assertions.assertEquals("Opening a new window", windowheadertxt);
		
	   pid=driver.getWindowHandle();
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		Thread.sleep(3000);
		driver.navigate().to("https://linkedin.com");
		Thread.sleep(3000);
		//close the tab
		driver.close();
		
		//switch back to parentwindow
		driver.switchTo().window(pid);
		driver.switchTo().defaultContent();
		String windowheadertxt1 = driver.findElement(By.cssSelector("div[class='example']>h3")).getText();
		Assertions.assertEquals("Opening a new window", windowheadertxt1);
		
		Thread.sleep(3000);
	}

	
	@Test
	void testOpenNewWindow() throws InterruptedException {
		System.out.println(" testOpenNewWindow()::::");
		// Verify the login page Addressbar url and page heading
		wait.until(ExpectedConditions.urlContains("/windows"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='example']>h3")));
		String windowheadertxt = driver.findElement(By.cssSelector("div[class='example']>h3")).getText();
		Assertions.assertEquals("Opening a new window", windowheadertxt);
		 pid=driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.WINDOW);
		
		Thread.sleep(3000);
		driver.navigate().to("https://facebook.com");
		Thread.sleep(3000);
		//close the tab
		driver.close();
		
		//switch back to parentwindow
		driver.switchTo().window(pid);
		driver.switchTo().defaultContent();
		
		String windowheadertxt1 = driver.findElement(By.cssSelector("div[class='example']>h3")).getText();
		Assertions.assertEquals("Opening a new window", windowheadertxt1);
		
		Thread.sleep(3000);
	}
	
}
