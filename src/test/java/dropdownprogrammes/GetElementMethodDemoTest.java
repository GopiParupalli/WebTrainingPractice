package dropdownprogrammes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetElementMethodDemoTest {

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
		
		//fetch top story dynamic text from rediff home page
		String topStoryText=driver.findElement(By.xpath("(//div[@id='topdiv_0']/h2/a)[1]")).getText();
		System.out.println("Top story dynamic text from HomePage :"+topStoryText);
		
		//fetch news top story dynamic text from rediff home page news
		String newsTStoryText=driver.findElement(By.xpath("(//div[@class='secstorybox news']/h2/a)[1]")).getText();
		System.out.println("Top story dynamic text from HomePage :"+newsTStoryText);
		
		//getAttribute("attributename")
		//fetch the tooltip of moneylink
		WebElement moneyLink=driver.findElement(By.xpath("//div[@class='cell topicons']/a[2]"));
		String moneyLinkToolTip=moneyLink.getAttribute("titlr");
		System.out.println("moneylink tooltip is:"+moneyLinkToolTip);
		
		//fetch moneylink url
		String moneyLinkUrl=moneyLink.getAttribute("herf");
		System.out.println("money link url is :"+moneyLinkUrl);
		
		WebElement createAccountLink=driver.findElement(By.linkText("Create Account"));
		
		//getCssValue("cssProperty");
		//fetch the color of the link
		String clr=createAccountLink.getCssValue("color");
		System.out.println("createAccountLink color is :"+clr);
		
		//Get the text-decoration value---check weather it is underline or not
		String ud=createAccountLink.getCssValue("text-decoration");
		System.out.println("createAccountLink is underline or not :"+ud);
		
		//Get the font-family
		String fontFamily=createAccountLink.getCssValue("font-family");
		System.out.println("createAccountLink fontfamily is :"+fontFamily);
		
		//getLocation(); getSize(); getRect();
		WebElement searchEditBox=driver.findElement(By.className("homesrchbox"));
		
		//get the location of searcheditbox in selenium 3
		Point p=searchEditBox.getLocation();
		System.out.println("x coordinate:"+p.getX()+"  y coordinate:"+p.getY());
		
		//get the location of searcheditbox in selenium 4
		System.out.println("x coordinate:"+searchEditBox.getRect().getX()+"  y coordinate:"+searchEditBox.getRect().getY());
		
		//width and height of searchEditBox--Selenium-3
		Dimension d=searchEditBox.getSize();

		//width and height of searchEditBox--Selenium-4
		System.out.println("Height is :"+searchEditBox.getRect().getHeight()+"  Width is : "+searchEditBox.getRect().getWidth());
		
		//Fetch the Tagname of the searcheditbox
		String tagval=searchEditBox.getTagName();
		System.out.println("Tag name of searchEditBox is :"+tagval);
		
		Thread.sleep(2000);

		driver.quit();
		
		

		/*
		 * // click on the create account link
		 * driver.findElement(By.linkText("Create Account")).click();
		 * 
		 * // verify the page title---Rediffmail Free Unlimited Storage
		 * wait.until(ExpectedConditions.titleIs("Rediffmail Free Unlimited Storage"));
		 * 
		 * // entering name on editbox
		 * driver.findElement(By.xpath("(//input[contains(@name,'name')])[1]")).sendKeys
		 * ("Gopi");
		 * driver.findElement(By.xpath("(//input[contains(@name,'login')])[1]")).
		 * sendKeys("Reddy1234");
		 * 
		 * // entering DOB selectOption(By.xpath("//select[contains(@name,'DOB_Day')]"),
		 * "25"); selectOption(By.xpath("//select[contains(@name,'DOB_Month')]"),
		 * "MAY"); selectOption(By.xpath("//select[contains(@name,'DOB_Year')]"),
		 * "1984"); // click on female radio button
		 * driver.findElement(By.xpath("//input[@value='m']")).click(); // click on
		 * dropdown country button
		 * selectOption(By.xpath("//select[contains(@id,'country')]"), "Australia");
		 * 
		 * Thread.sleep(2000);
		 * 
		 * driver.quit();
		 * 
		 * }
		 * 
		 * private static void selectOption(By by, String str) {
		 * 
		 * // identify the dropdown and assign it to a webelement type variable
		 * WebElement dropdownnameref = driver.findElement(by);
		 * 
		 * // fetch all the dropdown options using findElements API and tagname-option
		 * and // store in the list type collection List<WebElement> opts =
		 * dropdownnameref.findElements(By.tagName("option"));
		 * 
		 * // for(datatype varname:collectionname){} for (WebElement o : opts) { //
		 * print each dropdown option text System.out.println(o.getText()); if
		 * (o.getText().equals(str)) { // select it o.click(); break;
		 * 
		 * } }
		 */
	}

}
