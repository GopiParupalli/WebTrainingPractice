package testngprogrammes;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class JqueryDatePickerTest {
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Test
	public void dateSelectionTest() throws InterruptedException {

		driver.get("http://jqueryui.com/datepicker/");

		// switch to iframe
		WebElement frameElement = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frameElement);
		// click to open the date time picker calendar.
		By dtp = By.xpath("//*[@id='datepicker']");
		wait.until(ExpectedConditions.presenceOfElementLocated(dtp));
		driver.findElement(dtp).click();

		// Provide the day of the month to select the date.
		HandleJQueryDateTimePicker("29");

		Thread.sleep(3000);
	}

	// Function to select the day of the month in the date picker.
	private void HandleJQueryDateTimePicker(String day) throws InterruptedException {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ui-datepicker-div")));
		WebElement table = driver.findElement(By.className("ui-datepicker-calendar"));
		System.out.println("JQuery Calendar Dates");
		// fetch all the rows in the table
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			// fetch the all columns for each row
			List<WebElement> cells = row.findElements(By.xpath("//td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(day)) {
					driver.findElement(By.linkText(day)).click();
				}
			}
		}

		// Switch back to the default screen again and scroll up by using
		// the negative y-coordinates.
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0, -250);");

		// Intentional pause for 2 seconds.
		Thread.sleep(2000);
	}

	// Function to select the day of the month in the date picker.
	private void HandleJQuerySelectMonthAndYear(String month, String year) throws InterruptedException {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ui-datepicker-div")));
		WebElement table = driver.findElement(By.className("ui-datepicker-calendar"));
		System.out.println("JQuery Calendar Dates");
		String ActualMonthYearText = null;
		String expectedMonthYearText = null;
		do {

			// fetch the currentmonth value
			String monthVal = driver.findElement(By.className("ui-datepicker-month")).getText();
			// fetch the current year value
			String yearVal = driver.findElement(By.className("ui-datepicker-year")).getText();
			if (!monthVal.equals(month)) {
				// click on next/previous button
				driver.findElement(By.xpath("//span[contains(.,'Next')]")).click();
			}
			ActualMonthYearText = monthVal + " " + yearVal;

			expectedMonthYearText = month + " " + year;
			if (ActualMonthYearText.equals(expectedMonthYearText)) {
				// select the date method call
			}

		} while (!ActualMonthYearText.equals(expectedMonthYearText));
		// Switch back to the default screen again and scroll up by using
		// the negative y-coordinates.
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0, -250);");

		// Intentional pause for 2 seconds.
		Thread.sleep(2000);
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
	public void afterTest() {
		driver.close();
	}

}