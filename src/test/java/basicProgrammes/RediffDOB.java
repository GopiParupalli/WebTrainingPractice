package basicProgrammes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RediffDOB {

	private static WebDriver driver=null;	

	public static void selectOption(By by, String str) {


		WebElement redDate = driver.findElement(by);
		//In java prospective int i=10;here int=WebElement;redDate=i;value=10;respectively...


		List<WebElement> opts = redDate.findElements(By.tagName("option"));

		
		for (WebElement o : opts) {
			System.out.println(o.getText());

			if (o.getText().equals(str)) {

				o.click();
				break;
			}
		}
	}

	public static void main(String[] args) {


		//System.setProperty("webdriver.chrome.driver","/Users/Gopireddyparupalli/Desktop/Selenium2022/Executable/Chrome/chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();

		 driver = new ChromeDriver();

	
		driver.get("https://www.rediff.com/");
		
		driver.findElement(By.linkText("Create Account")).click();
		
		String Title=driver.getTitle();
		System.out.println("print the page title :"+Title);
		
		String Url=driver.getCurrentUrl();
		System.out.println("print the absurl:"+Url);
		
		String Id=driver.getWindowHandle();
		System.out.println("print the window id:"+Id);
			
		//entering name on editbox
		driver.findElement(By.xpath("(//input[contains(@name,'name')])[1]")).sendKeys("Gopi");
		driver.findElement(By.xpath("(//input[contains(@name,'login')])[1]")).sendKeys("Reddy1234");
		//driver.findElement(By.xpath("(//input[contains(@value,'Check availability')]"));

		
//country code dropdown button
		
		//selectOption(By.xpath("(//div[contains(.,'+61')])[4]"),"");
		
		//mobile number   //input[@id='mobno']
		//input[contains(@id,'mobno')]
		
		selectOption(By.xpath("//input[@id='mobno']"), "490023822");

		////input[contains(@value,'Check availability')]


		//entering DOB
		selectOption(By.xpath("//select[contains(@name,'DOB_Day')]"), "25");
		selectOption(By.xpath("//select[contains(@name,'DOB_Month')]"), "MAY");
		selectOption(By.xpath("//select[contains(@name,'DOB_Year')]"), "1984");
		//click on female radio button
		driver.findElement(By.xpath("//input[@value='m']")).click();
		//click on dropdown country button
		selectOption(By.xpath("//select[contains(@id,'country')]"),"Australia");

		
//driver.close();
//driver.quit();
	}
}
