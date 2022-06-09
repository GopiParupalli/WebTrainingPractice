package dropdownprogrammes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RediffDropdownTest {

	static WebDriver driver = null;
	static WebDriverWait wait = null;

	public static void main(String[] args) throws InterruptedException {

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
		driver.get("https://www.rediff.com/");

		// Verify the page title
		wait.until(ExpectedConditions.titleContains("Rediffmail"));

		// click on the create account link
		driver.findElement(By.linkText("Create Account")).click();

		// verify the page title---Rediffmail Free Unlimited Storage
		wait.until(ExpectedConditions.titleIs("Rediffmail Free Unlimited Storage"));

		// entering name on editbox
		driver.findElement(By.xpath("(//input[contains(@name,'name')])[1]")).sendKeys("Gopi");
		driver.findElement(By.xpath("(//input[contains(@name,'login')])[1]")).sendKeys("Reddy1234");

		// entering DOB
		selectOption(By.xpath("//select[contains(@name,'DOB_Day')]"), "25");
		selectOption(By.xpath("//select[contains(@name,'DOB_Month')]"), "MAY");
		selectOption(By.xpath("//select[contains(@name,'DOB_Year')]"), "1984");
		// click on female radio button
		driver.findElement(By.xpath("//input[@value='m']")).click();
		// click on dropdown country button
		selectOption(By.xpath("//select[contains(@id,'country')]"), "Australia");

		Thread.sleep(2000);

		driver.quit();

	}

	private static void selectOption(By by, String str) {

		// identify the dropdown and assign it to a webelement type variable
		WebElement dropdownnameref = driver.findElement(by);

		// fetch all the dropdown options using findElements API and tagname-option and
		// store in the list type collection
		List<WebElement> opts = dropdownnameref.findElements(By.tagName("option"));

		// for(datatype varname:collectionname){}
		for (WebElement o : opts) {
			// print each dropdown option text
			System.out.println(o.getText());
			if (o.getText().equals(str)) {
				// select it
				o.click();
				break;

			}
		}
	}

}
