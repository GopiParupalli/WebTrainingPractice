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

public class IMBDTop35Movies {
	
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
		driver.get("https://www.imdb.com/list/ls000632473/");

		// Verify the page title
		wait.until(ExpectedConditions.titleIs("Top 35 inspirational movies - IMDb"));
		
		
		//Fetch all the links into List using findelements locator --tagname-a
		List<WebElement>Imbd35MovieList=driver.findElements(By.xpath("//div[@class='lister-item-content']/h3/a"));
		
		for(int i=1;i<=Imbd35MovieList.size();i++){
			System.out.println("**************************************************");
			
			//fetch the link name
			String movieLinkName=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[3]/div[3]/div["+i+"]/div[2]/h3/a")).getText();
			System.out.println(i+ "th linkname is:" +movieLinkName);
			
			//fetch the linkUrl
			String url=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[3]/div[3]/div["+i+"]/div[2]/h3/a")).getAttribute("herf");
			System.out.println(movieLinkName+"  url  is  "+url);
			
			//fetch the link url
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[3]/div[3]/div["+i+"]/div[2]/h3/a")).click();
			
			//wait for the movie header text
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@data-testid='hero-title-block__title']")));
			
			//wait for the next page title
			wait.until(ExpectedConditions.titleContains(driver.getTitle()));
			
			if(driver.getTitle().contains("movieLinkName")) {
				System.out.println("we are in correct movie link "+movieLinkName+"  page: "+driver.getTitle());
			}else {
				System.out.println("we are not in correct movie link  "+movieLinkName+" page: "+driver.getTitle());
			}
			
			//navigate back
			driver.navigate().back();
		}
		
		Thread.sleep(1000);

		
		driver.quit();
	}

}


