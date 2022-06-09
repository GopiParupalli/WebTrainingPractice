package junitpackage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class InternalFrameTest {
	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().setup();
		
		// interface refvar=new implementedclass();
		driver = new ChromeDriver();

		// maximize the window
		driver.manage().window().maximize();

		// add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		
		// create Object for WebDriverWait class
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));

		// open the url in browser
		driver.get("https://jqueryui.com/");

		wait.until(ExpectedConditions.titleContains("jQuery UI"));
		Assertions.assertEquals("jQuery UI", driver.getTitle());
		Assertions.assertTrue(driver.getTitle().contains("jQuery UI"));
		
		// click on AutoComplete link
		//driver.findElement(By.linkText("Autocomplete")).click();
		// wait for the url
		//wait.until(ExpectedConditions.urlContains("https://jqueryui.com/autocomplete/"));
		//switch to iframe
		

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testInternalFrames() {
		System.out.println("started executing the testInternalFrames()....");
		
		// click on AutoComplete link
		driver.findElement(By.linkText("Autocomplete")).click();
		// wait for the url
		wait.until(ExpectedConditions.urlContains("/autocomplete/"));
		wait.until(ExpectedConditions.titleContains("Autocomplete | jQuery UI"));
		
		//fetch total number of frames
		List<WebElement>iframeList=driver.findElements(By.tagName("iframe"));
		System.out.println("Number of iframes in the page :"+iframeList.size());
		
		switchToFrame(driver.findElement(By.className("demo-frame")));
		
		//WebElement iframele=driver.findElement(By.className("demo-frame"));
		//switch to iframe element
		//switchToFrame(iframele);
		
		//type the value tags editbox
		driver.findElement(By.id("tags")).sendKeys("selenium");
		
		//switch back to jqueryui home page
		driver.navigate().back();
		
		wait.until(ExpectedConditions.titleIs("jQuery UI"));
		
		

	}
		//This method will switch to parent frame then to child frame

	private void switchToFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " +ChildFrame+ "which is present on frame with id" +ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + ParentFrame+ " or " +ChildFrame+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id "+ChildFrame+ "which is present on frame with id"+ParentFrame + e.getStackTrace());
		}
	}
	
	  //this method will return from frame to original position
	
	private void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out.println("unable to navigate back to main webpage from frame"+e.getStackTrace());
		}
	}
	
	//this method switch to given fame locator
	
	public void switchToFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with name " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " +frame+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " +frame+e.getStackTrace());
		}
	}
	
	public void switchToFrame(WebElement frameElement) {
		try {
			if (frameElement.isDisplayed()) {
				driver.switchTo().frame(frameElement);
				System.out.println("Navigated to frame with element :::"+frameElement);
			} else {
				System.out.println("Unable to navigate to frame with element "+frameElement);
			}
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " +frameElement +e.getStackTrace());
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " +frameElement+ "is not attached to the page document" +e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with element " +frameElement+e.getStackTrace());
		}
	}
	
}
