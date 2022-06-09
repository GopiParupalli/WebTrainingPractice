package linkprogrammes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollAndTkeScreenShot {

	static WebDriver driver = null;
	static WebDriverWait wait = null;

	public static void main(String[] args) throws InterruptedException, IOException {

		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().setup();

		// interface variablename=new classname();
		driver = new ChromeDriver();

		// maximize the window
		driver.manage().window().maximize();

		// add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// create Webdriverwait object
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));

		// open the rediff home page
		driver.get("https://www.amazon.com.au/");

		// Verify the page title--Amazon.com.au: Shop online for Electronics, Apparel,Toys, Books, DVDs & more
		wait.until(ExpectedConditions.titleContains("Amazon.com.au:"));

		// Identify the categories drop down
		WebElement searchEditBox = driver.findElement(By.id("twotabsearchtextbox"));

		// Type the value in search edit box
		searchEditBox.sendKeys("laptop");

		// submit on the searchEditBox
		searchEditBox.submit();

		// wait
		wait.until(ExpectedConditions.titleContains("Amazon.com.au : laptop"));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//span[contains(.,'1-48 of over 5,000 results for')])[2]")));

		// Scroll for few Pixels
		JavascriptExecutor jsx = (JavascriptExecutor) driver;

		// Scroll down
		jsx.executeScript("window.scrollBy(0,3500)", "");

		// Takes screenshot of entire page
		captureScreenshot("scrolldownusingJSExecutor");
		Thread.sleep(2000);

		// Scroll UP
		jsx.executeScript("window.scrollBy(0,-3000)", "");

		// Takes screenshot of entire page
		captureScreenshot("scrollUPUsingJSExecutor");
		Thread.sleep(2000);

		// Scroll until the element is visible
		WebElement navBackToTop = driver.findElement(By.id("navBackToTop"));
		jsx.executeScript("arguments[0].scrollIntoView(true);", navBackToTop);
		captureScreenshot("scrollForNavbackToTopElmnt");
		Thread.sleep(2000);

		// Click on navBackToTop button
		navBackToTop.click();

		// Scroll based on Keys Enum
		for (int i = 1; i <= 10; i++) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		}
		captureScreenshot("scrollDOWNUsingKEYSDOWNKey");
		Thread.sleep(2000);

		// Scrollup using key UP
		for (int i = 1; i <= 10; i++) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.UP);

		}
		captureScreenshot("scrollUPUsingKEYSUPKey");
		Thread.sleep(2000);

		// Take a screenshot of Amazon logo
		WebElement amazonlogo = driver.findElement(By.xpath("//a[@id='nav-logo-sprites']"));
		captureScreenshot(amazonlogo, "AmazonLogoScreenshot");

	}

	// Taking the entire browser screenshot

	private static void captureScreenshot(String screenName) throws IOException {

		// Taking screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Create Object for Date class
		Date d = new Date();
		screenName = screenName + "-" + d.toString().replace(":", "-").replace(" ", "-") + ".jpg";

		// Copy the file name under project directory
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\screenshots\\" + screenName));

	}

	// Taking the element screenshot

	private static void captureScreenshot(WebElement element, String screenName) throws IOException {

		// Taking screenshot
		File src = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);

		// Create Object for Date class
		Date d = new Date();
		screenName = screenName + "-" + d.toString().replace(":", "-").replace(" ", "-") + ".jpg";

		// Copy the file name under project directory
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\screenshots\\" + screenName));

	}

}
