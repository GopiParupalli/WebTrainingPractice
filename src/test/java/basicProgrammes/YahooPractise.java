package basicProgrammes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class YahooPractise {

	public static void main(String[] args) throws InterruptedException {

		
		//setup chrome browser either with System.setProperty(p1,p2) or with WebDriverManager.chromedriver().set()
		WebDriverManager.chromedriver().setup();
		
		//Interface Varname=new classname()
		WebDriver driver=new ChromeDriver();
		
		//maximise the window
		driver.manage().window().maximize();
		
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//create webdriver wait object		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofMillis(5000));
		
		//Open yahoo.com 
		driver.get("https://au.yahoo.com/");
		
		//calling all the browser methods..
		// title of the page by creating an object t
		String t=driver.getTitle();
		// Currenturl of the page by creating an object exacturl
		String exacturl=driver.getCurrentUrl();
		//source of the page by creating an object src
		String src=driver.getPageSource();
		//windowhandle of the page by creating an object cwi
		String cwi=driver.getWindowHandle();
		//windowhandles of the page by creating an object handles using set type collection
		Set<String>handles=driver.getWindowHandles();
		
		//wait and verify the current page url--Yahoo Australia | News, email and search
		wait.until(ExpectedConditions.titleContains("Yahoo Australia"));
		
		Thread.sleep(2000);
		
		//type the selenium keyword in search editbox.
		driver.findElement(By.xpath("//input[@data-rapid_p='13']")).sendKeys("selenium.dev");
		
		//submit on the search editbox
		driver.findElement(By.xpath("//input[@data-rapid_p='13']")).submit();
		
		//verify the search results page title---selenium.dev - Yahoo Search Results
		wait.until(ExpectedConditions.titleIs("selenium.dev - Yahoo Search Results"));
		
		Thread.sleep(2000);
	
		//fetch the results text and  extract the results count.---About 9,080,000 search results
		String txt=driver.findElement(By.xpath("//span[contains(.,'About 9,080,000 search results')]")).getText();
		System.out.println("search result text is:"+txt);
		
		//Extract the only count from search results count text
		//String text="About 9,080,000 search results"
		//using String class-split(delimeter) returns String[]
		String[] str=txt.split(" ");
		//String[] str=["About","9,080,000","search","results"]
		//                 0        1          2         3
		System.out.println("search result count is:"+str[1]);
		
		//String s1=str1].replace(",","");i..e..9080000---String s1=str[1].replace(","," ");
		String s1=str[1].replace(","," ");
		
		//Converting string into long number
		long count =Long.parseLong(s1);
		System.out.println("search result count in number:"+count);
		
		//click on the selenium.dev link
		driver.findElement(By.xpath("//a[contains(@href,'wgCLKM-')]")).click();
		
		//verify selenium page
		//wait.until(ExpectedConditions.titleIs("Selenium"));
		
		//If its opens in new tab
		driver.get("https://www.selenium.dev/");
		
		//verify page title
		wait.until(ExpectedConditions.titleIs("Selenium"));
		
		Thread.sleep(2000);
		
		//click on the downloads tab
		driver.findElement(By.xpath("//span[contains(.,'Downloads')]")).click();
		
		//verify download page
		wait.until(ExpectedConditions.titleIs("Downloads | Selenium"));
		
		Thread.sleep(2000);
		
		//navigate to selenium home page
		driver.navigate().back();
		
		Thread.sleep(2000);
		
		//navigate to download page
		driver.navigate().forward();
		
		Thread.sleep(2000);
		
		//naviagte to selenium page
		driver.navigate().back();
		
		Thread.sleep(2000);
		
		driver.quit();

	}

}
