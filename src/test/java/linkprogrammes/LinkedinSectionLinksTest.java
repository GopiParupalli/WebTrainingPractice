package linkprogrammes;

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

public class LinkedinSectionLinksTest {

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
		driver.get("https://www.linkedin.com/");

		// Verify the page title
		wait.until(ExpectedConditions.titleIs("LinkedIn: Log In or Sign Up"));
		
		//Identify the General esction from the bottom of the page
		////div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul
		WebElement genSection=driver.findElement(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul"));
		
		//Fetch all the links into List using findelements locator --tagname-a
		List<WebElement>sectionLinksList=genSection.findElements(By.tagName("a"));
		for(int i=1;i<=sectionLinksList.size();i++){
			//fetch the link name
			String linkName=driver.findElement(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li["+i+"]/a")).getText();
			System.out.println(i+ "th linkname is:" +linkName);
			//fetch the linkUrl
			String url=driver.findElement(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li["+i+"]/a")).getAttribute("herf");
			System.out.println(linkName+"  url  is  "+url);
			//click on the each link
			driver.findElement(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li["+i+"]/a")).click();
			//wait for the next page url
			wait.until(ExpectedConditions.urlContains(driver.getCurrentUrl()));
			
			if(driver.getCurrentUrl().contains(linkName.toLowerCase())) {
				System.out.println(linkName+"  page is loaded properly");
				System.out.println(linkName+   "page title is: "+driver.getTitle());
			}
			
			//navigate back to previous page
			driver.navigate().back();
			
		}
		Thread.sleep(2000);
		driver.quit();
	}

}
