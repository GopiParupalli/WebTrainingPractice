package basicProgrammes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;





public class GoogleSearchTestSilentModeChrome {

	public static void main(String[] args) {

		//set the chromedriver.exe file path System.setproperty(p1,p2)or WebDriverManager.chromedriver().setup();

		WebDriverManager.chromedriver().setup();
		
		//interface variablename=new classname();
		WebDriver driver=new ChromeDriver();
		
		//maximize the window
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//create Webdriverwait object
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(5000));
		
		//open the google.com
		driver.get("https://www.google.com.au/");
		
		//call all the browser methods
		String t=driver.getTitle();
		System.out.println("current page title is:"+t);
		String absurl=driver.getCurrentUrl();
		System.out.println("current page absolute url is:"+absurl);
		
		//fetch the source of the page
		String src=driver.getPageSource();
		
		//fetch the current window id
		String cwid=driver.getWindowHandle();
		System.out.println("print the window id:"+cwid);
		
		//fetch all the window ids
		Set<String>handles=driver.getWindowHandles();
		System.out.println("all windows collection is "+handles);
		
		//wait and verify the current page title
		wait.until(ExpectedConditions.titleIs("Google"));
		
		
		//type the selenium.dev keyword in search editbox
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium.dev");
		
		//submit on the search editbox
		driver.findElement(By.xpath("//input[@name='q']")).submit();
	
		//verify the search results page title//Selenium.dev - Google Search
		wait.until(ExpectedConditions.titleContains("Selenium.dev - Google Search"));
		
		//verify the search results count text is present in the webpage or not.//div[@id='result-stats']
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='result-stats']")));
		
		
		//fetch the search results count text and extract the only count from search results count text.
		//String txt=driver.findElement(By.xpath("//div[@id='result-stats']")).getText();
		//System.out.println("search result text is:"+txt);
		
		//Extract the only count from search results count text
		//String text="about 1,410,000000 results (0.41 seconds)"
		//using String class-split(delimeter) returns String[]
		//String[] str=txt.split(" ");
		
		//string[] str=["About","1,410,000,000","results","(0.41,seconds)"]
		//                  0        1              2         3     4

		//System.out.println("search result count is:"+str[1]);
		
		//String s1=str1].replace(",","");i..e..1410000000
		//String s1=str[1].replace(","," ");
		
		//how to convert string into long number
		//long count =Long.parseLong(s1);
		//System.out.println("search result count in number:"+count);
		
		//click on Selenium link////h3[contains(.,'Selenium.dev')]
		driver.findElement(By.xpath("//h3[contains(.,'Selenium.dev')]")).click();
		
		//verify selenium website homepage title
		wait.until(ExpectedConditions.titleContains("Selenium"));
		
		//Click on Downloads Tab
		driver.findElement(By.xpath("//span[contains(.,'Downloads')]")).click();
		
		//Verify the selenium downloads page Title
		wait.until(ExpectedConditions.titleContains("Downloads | Selenium"));
		
		//navigate to selenium home page--use navigate().back().
		driver.navigate().back();
		
		//verify selenium website homepage title
		wait.until(ExpectedConditions.titleContains("Selenium"));
		
		//navigate to download page--use navigate().forward().
		driver.navigate().forward();
		
		//Verify the selenium downloads page Title
		wait.until(ExpectedConditions.titleContains("Downloads | Selenium"));
		
		//download page--use navigate().refresh().
		driver.navigate().refresh();
		
		
		//close the browser or quit
		driver.close();
		
		
		
	}

}

