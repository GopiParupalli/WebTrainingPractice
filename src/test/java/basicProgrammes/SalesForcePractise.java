package basicProgrammes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForcePractise {

	public static void main(String[] args) throws InterruptedException {
		
		//setup chrome browser
		WebDriverManager.chromedriver().setup();
		
		//interface varname=new classname();
		WebDriver driver=new ChromeDriver();
		
		//maximize the window
		driver.manage().window().maximize();
		
		//add Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//creating webdriver wait object
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(4000));
		
		//open google.com home page
		driver.get("https://www.google.com.au/");
		
		//calling all browser methods
		String t=driver.getTitle();
		System.out.println("page title is "+t);
		
		String absurl=driver.getCurrentUrl();
		System.out.println("Current page title is "+absurl);

		String src=driver.getPageSource();
		//System.out.println("Page source is  "+src);

		String winid=driver.getWindowHandle();
		System.out.println("Current page window id is  "+winid);

		Set<String>winids=driver.getWindowHandles();
		System.out.println("All Window ids are "+winids);

		wait.until(ExpectedConditions.titleIs("Google"));
		
		Thread.sleep(2000);
		
		//Type the key word salesforce
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("salesforce");
		//submit the keyword
		driver.findElement(By.xpath("//input[@name='q']")).submit();
		//wait until expectedcondition display
		wait.until(ExpectedConditions.titleIs("salesforce - Google Search"));
		//wait for 2 seconds to see the result
		Thread.sleep(2000);
		//select the salesforce page from google results and click
		driver.findElement(By.xpath("(//h3[contains(.,'Salesforce')])[1]")).click();
		//wait until page display
		wait.until(ExpectedConditions.titleContains("CRM Software:"));
		//look for 2 seconds to see expected condition 
		Thread.sleep(2000);
		//click on the login button
		driver.findElement(By.xpath("//a[@class='dropdown-toggle disabled']")).click();
		//wait until login page display
		wait.until(ExpectedConditions.titleIs("Login | Salesforce"));
		
		Thread.sleep(2000);
		//pass the keyword
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Gopi");
		
		Thread.sleep(2000);
		//navigate to salesforce home page
		driver.navigate().back();
		driver.navigate().back();

		
		Thread.sleep(2000);
		//In salesforce home page selecting Products tab--(//span[contains(.,'Products')])[1]
		driver.findElement(By.xpath("(//span[contains(.,'Products')])[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[contains(.,'Integration')])[1]")));
		//Select Integration tab from Products
		driver.findElement(By.xpath("(//span[contains(.,'Integration')])[1]")).click();
		//wait---Integration Platform for Connecting SaaS & Enterprise Applications - Salesforce AU & NZ
		wait.until(ExpectedConditions.titleContains("Integration Platform for Connecting"));
		Thread.sleep(2000);
		//driver.quit();
		
		
		

	}

}
