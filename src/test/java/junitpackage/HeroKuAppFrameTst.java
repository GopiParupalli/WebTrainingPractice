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
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class HeroKuAppFrameTst {

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

		// open the url in browser
		driver.get("http://the-internet.herokuapp.com/");

		// create Object for WebDriverWait class
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));

		wait.until(ExpectedConditions.titleContains("The Internet"));
		
		// wait for the home page heading css=tagname.classvalue
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.heading")));
		
		// fetch the header text
		String headertxt = driver.findElement(By.cssSelector("h1.heading")).getText();
		
		// assertEquals()
		Assertions.assertEquals(headertxt, "Welcome to the-internet");
		
		// assertTrue()
		Assertions.assertTrue(driver.getPageSource().contains(headertxt));
		
		// click on Frames l
		driver.findElement(By.linkText("Frames")).click();
		
		// wait for the url
		wait.until(ExpectedConditions.urlContains("http://the-internet.herokuapp.com/frames"));
		
		// wait for the page heading
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='example']/h3")));
		
		// fetch teh header text Loginpage
		String frmheadertxt = driver.findElement(By.cssSelector("div.example>h3")).getText();
		
		// using assertEquals()
		Assertions.assertEquals("Frames", frmheadertxt);
		
		// using assertTrue()
		Assertions.assertTrue(driver.getPageSource().contains(frmheadertxt));

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testFrames() {
		System.out.println("started executing the testFrames()....");
		
		// click on 'Nested Frames'
		driver.findElement(By.partialLinkText("Nested Frames")).click();
		
		//wait for the next page title:http://the-internet.herokuapp.com/nested_frames
		wait.until(ExpectedConditions.urlToBe("http://the-internet.herokuapp.com/nested_frames"));
		
		//fetch total number of frames
		List<WebElement>framList=driver.findElements(By.tagName("frame"));
		System.out.println("Number of frames in the page :"+framList.size());
		
		/*//switch to top frame first then switch to child frame
		switchToFrame("frame-top");
		//fetch number of frames inside top frame
		List<WebElement>topframList=driver.findElements(By.tagName("frame"));
		System.out.println("Number of frames in the top frame page :"+topframList.size());
		//switch to middle frame
		switchToFrame("frame-middle");*/
		
		switchToFrame("frame-top", "frame-middle");
		
		//fetch the MIDDLE text
		String txt=driver.findElement(By.id("content")).getText();
		System.out.println("middle frame text is:"+txt);
		Assertions.assertEquals("MIDDLE", txt);

	}
	
	//This method will switch to parent frame then to child frame
	
	private void switchToFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " + ChildFrame
					+ "which is present on frame with id" + ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + ParentFrame+ " or " + ChildFrame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id "+ ChildFrame + "which is present on frame with id"+ ParentFrame + e.getStackTrace());
		}
	}
	
	//this method will return from frame to original position
	
	private void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out.println("unable to navigate back to main webpage from frame"+ e.getStackTrace());
		}
	}
	//this method switch to given fame locator
	
	private void switchToFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with name " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frame+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " + frame+ e.getStackTrace());
		}
	}
	
}


