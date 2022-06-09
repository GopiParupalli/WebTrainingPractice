package linkprogrammes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImbdPractise {
	
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
		driver.get("https://www.imdb.com/chart/top/");

		// Verify the page title
		wait.until(ExpectedConditions.titleIs("Top 250 Movies - IMDb"));
		
		
		//Fetch all the links into List using findelements locator --tagname-a
		List<WebElement>Imbd250MovieList=driver.findElements(By.xpath("//table[@class='chart full-width']//tbody/tr/td[2]/a"));
		for(int i=1;i<=Imbd250MovieList.size();i++){
			System.out.println("*******************************");
			//fetch the link name
			String movieLinkName=driver.findElement(By.xpath("//table[@class='chart full-width']//tbody/tr["+i+"]/td[2]/a")).getText();
			System.out.println(i+ "th linkname is:" +movieLinkName);
			//fetch the linkUrl
			String url=driver.findElement(By.xpath("//table[@class='chart full-width']//tbody/tr["+i+"]/td[2]/a")).getAttribute("herf");
			System.out.println(movieLinkName+"  url  is  "+url);
			//fetch the link url
			String tooltip=driver.findElement(By.xpath("//table[@class='chart full-width']//tbody/tr["+i+"]/td[2]/a")).getAttribute("title");
			System.out.println(movieLinkName+"  tooltip is:  "+tooltip);
		}
		
		Thread.sleep(1000);

		
		driver.quit();
	}

}


