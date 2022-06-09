package linkprogrammes;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenImagesTest {
	static WebDriver driver = null;
	static WebDriverWait wait = null;

	public static void main(String[] args) throws InterruptedException, IOException {

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

		// open the  home page
		driver.get("https://the-internet.herokuapp.com/");

		// Verify the page title
		wait.until(ExpectedConditions.titleIs("The Internet"));
		
		//click on Broken Images Link
		driver.findElement(By.linkText("Broken Images")).click();
		
		//wait for the broken images header element
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='example']>h3")));
		
		
		//Fetch all the links into List using findelements locator --tagname-a
		List<WebElement>imagesList=driver.findElements(By.tagName("img"));
		
		//iterate all the images using for each loop
		for(WebElement img:imagesList) {
			String imgsrc=img.getAttribute("src");
			verifyLinks(imgsrc);
			
		}
		Thread.sleep(2000);
		
		//close the browser
		driver.quit();
	
	}
	private static void verifyLinks(String url) throws IOException {
		
		//step1 create object for url class
		URL ul=new URL(url);
		
		//step 2
		HttpURLConnection hc=(HttpURLConnection) ul.openConnection();
		
		//step 3 connect to the url
		hc.connect();
		
		//step4 fetch the responceStatusCode & respmessage
		int respCode=hc.getResponseCode();
		
		//fetch respMessage
		String respMsg=hc.getResponseMessage();
		
		if(respCode==200) {
			System.out.println(url+" link is working fine: "+respCode+" respMsg: "+respMsg);
		}else if(respCode==404){
			System.out.println(url+" link is broken/not working fine: "+respCode+" respMsg: "+respMsg);
		}
		
		//step5 disconnect to the url
		hc.disconnect();
	}
}
