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
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class GoogleSuggestionAvailabilityCheckTest {
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test
	public void googleSuggestionAvailabilitycheckTest() {
		Reporter.log("open the url in browser");
		driver.get("https://www.google.com/");
		Reporter.log("wait and verify the Google page title");
		String linkedinHomePgTitle = "Google";
		wait.until(ExpectedConditions.titleIs(linkedinHomePgTitle));
		AssertJUnit.assertEquals(driver.getTitle(), linkedinHomePgTitle);
		AssertJUnit.assertTrue(driver.getTitle().contains(linkedinHomePgTitle));
		Reporter.log("type selenium keyword in search edibtox");
		String typekeyword = "selenium";
		driver.findElement(By.name("q")).sendKeys(typekeyword);
		
		// fetch all the suggestions into List collection
		List<WebElement> suggestionList = driver.findElements(By.xpath("//ul[@role='listbox']/li"));
		boolean isAvailable = false;
		for (WebElement suggestion : suggestionList) {
			String suggestiontext = suggestion.getText();
			System.out.println(suggestiontext);
			if (suggestiontext.contains(typekeyword)) {
				isAvailable = true;
				AssertJUnit.assertTrue(isAvailable);
			}
		}

	}

	@BeforeClass
	public void beforeClass() {
		
		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();
	
		WebDriverManager.chromedriver().setup();
		Reporter.log("interface refvar=new implementedclass()");
		driver = new ChromeDriver();
		Reporter.log("Browser is launched");
		Reporter.log("maximize the window");
		driver.manage().window().maximize();
		Reporter.log(" adding implicit wait");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		Reporter.log(" create Object for WebDriverWait class");
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