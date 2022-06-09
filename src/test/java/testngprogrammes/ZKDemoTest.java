package testngprogrammes;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class ZKDemoTest {

	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test(description = "button test", priority = 2)
	public void buttonTest() {

		Reporter.log("grab all the buttons and print");
		Reporter.log("started executing the butto test()......");
		Reporter.log("click on button link");
		driver.findElement(By.linkText("Button")).click();
		Reporter.log("wait for the page title---ZK Live Demo - Input");
		String pgTitle = "ZK Live Demo - Button";
		wait.until(ExpectedConditions.titleIs(pgTitle));
		Assertions.assertEquals(pgTitle, driver.getTitle());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("breadCrumb")));
		Reporter.log("grab all the buttons");
		List<WebElement> buttonList = driver.findElements(By.className("z-button"));
		Reporter.log("number of buttons in the page:   " + buttonList.size());
		for (WebElement btn : buttonList) {
			Reporter.log("button name is:  " + btn.getText());
		}

		Reporter.log("****************End of button***************************");
	}
 
	@Test(description = "checkbox test", priority = 1)
	public void checkBoxTest() throws InterruptedException {

		Reporter.log("Started Executing checkBoxTest().....");
		Reporter.log("click on checkbox link");
		driver.findElement(By.linkText("Checkbox")).click();
		Reporter.log("wait for the page title---ZK Live Demo - Checkbox");
		String pgTitle = "ZK Live Demo - Checkbox";
		wait.until(ExpectedConditions.titleIs(pgTitle));

		Assertions.assertEquals(pgTitle, driver.getTitle());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("breadCrumb")));
		Reporter.log("grab all the checkboxes");
		List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox']"));
		Reporter.log("number of buttons in the page:   " + checkBoxList.size());
		for (int i = 0; i < checkBoxList.size(); i++) {
			if (!checkBoxList.get(i).isSelected()) {
				checkBoxList.get(i).click();
				Thread.sleep(1000);
			}
		}
		Reporter.log("select the checked check boxes text");
		String checkBoxTxt = driver
				.findElement(By.xpath("//span[contains(text(),'You have selected')]/following::span")).getText();
		Reporter.log("checked check boxes text is:   " + checkBoxTxt);
		Reporter.log("****************End of checkBox***************************");
	}

	@Test(description = "Radiobutton test", priority = 3)
	public void radioButtonTest() throws InterruptedException {

		Reporter.log("Started Executing radioButtonTest().....");
		Reporter.log("click on radioButton link");
		driver.findElement(By.linkText("Radio Button")).click();
		Reporter.log("wait for the page title---ZK Live Demo - Radio Button");
		String pgTitle = "ZK Live Demo - Radio Button";
		wait.until(ExpectedConditions.titleIs(pgTitle));
		Assertions.assertEquals(pgTitle, driver.getTitle());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("breadCrumb")));

		Reporter.log("select Performance Radio Button");
		driver.findElement(By.xpath("//label[contains(text(),'Performance')]/preceding::input")).click();

		Reporter.log("select the feature text");
		String featureTxt = driver.findElement(By.xpath("//span[contains(text(),'Feature')]/following::span"))
				.getText();
		Reporter.log("Feature value  is:   " + featureTxt);

		Reporter.log("select Forum Radio Button");
		driver.findElement(By.xpath("//label[contains(text(),'Forum')]/preceding::input")).click();

		Reporter.log("select the Web Site text");
		String websiteTxt = driver.findElement(By.xpath("//span[contains(text(),'Web Site')]/following::span"))
				.getText();
		Reporter.log("website value  is:   " + websiteTxt);

		Reporter.log("select Developer Guide Radio Button");
		driver.findElement(By.xpath("//label[contains(text(),'Developer Guide')]/preceding::input")).click();

		Reporter.log("select the Documentation text");
		String documentTxt = driver.findElement(By.xpath("//span[contains(text(),'Documentation')]/following::span"))
				.getText();
		Reporter.log("Documentation value  is:   " + documentTxt);

		Reporter.log("****************End of radioButton***************************");
	}

	@Test(description = "tweetpopup test")
	public void tweetPopupTest() throws InterruptedException {

		Reporter.log("Started Executing tweetPopupTest().....");
		Reporter.log("click on Date and Time Picker link");
		driver.findElement(By.linkText("Date and Time Picker")).click();
		Reporter.log("wait for the page title---ZK Live Demo - Radio Button");
		String pgTitle = "ZK Live Demo - Date and Time Picker";
		wait.until(ExpectedConditions.titleIs(pgTitle));
		Assertions.assertEquals(pgTitle, driver.getTitle());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("breadCrumb")));

		Reporter.log("switch to the iframe");
		// identify the iframe webelement
		WebElement iframel = driver.findElement(By.id("twitter-widget-0"));
		driver.switchTo().frame(iframel);

		Reporter.log("click on tweet button");
		// driver.findElement(By.xpath("//*[text()='Tweet']")).click();
		// WebElement tweetBtn=driver.findElement(By.xpath("//*[text()='Tweet']"));
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("arguments[0].click()", driver.findElement(By.xpath("//*[text()='Tweet']")));

		Reporter.log("fetch all the window ids");
		Set<String> handles = driver.getWindowHandles();

		// convert the set collection into list collection
		List<String> windowids = new ArrayList<>(handles);

		// switch to tweet window
		driver.switchTo().window(windowids.get(1));

		// wait for the tweet page title
		wait.until(ExpectedConditions.titleIs("Twitter"));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='css-1dbjc4n r-18u37iz']/div/span")));

		// perform the type action
		driver.findElement(By.name("session[username_or_email]")).sendKeys("gopi3610@gmail.com");
		Thread.sleep(2000);
		// close the twitter
		driver.close();
		// switch back to Parent window
		driver.switchTo().window(windowids.get(0));
		// clear the content in the edit box
		WebElement dateEditBox = driver.findElement(By.className("z-datebox-input"));
		dateEditBox.clear();
		// type your own date
		dateEditBox.sendKeys("01/01/2022");

		Reporter.log("****************End of tweetPopup***************************");
	}

	@BeforeMethod
	public void beforeMethod() {

		Reporter.log("Launch the application url");
		driver.get("https://www.zkoss.org/zkdemo/input");

		Reporter.log("wait for the page title---ZK Live Demo - Input");
		String pgTitle = "ZK Live Demo - Input";
		wait.until(ExpectedConditions.titleIs(pgTitle));

		Assertions.assertEquals(pgTitle, driver.getTitle());
		Assertions.assertTrue(driver.getTitle().contains(pgTitle));

	}

	@AfterMethod
	public void afterMethod() {

		Reporter.log("started executing the @AfterMethod");
		Reporter.log("Clear the cathe");
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	public void beforeClass() {
		Reporter.log("@BeforeClass is current class level annotation");

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
		Reporter.log("quit the browser");
		if (driver != null) {
			driver.quit();
		}

	}

}
