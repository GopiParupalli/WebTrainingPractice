package linkprogrammes;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CookieDemo {
	
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
		
		//create cookie object
		Cookie ckoobj=new Cookie("testcookie","javaseleniumwebdriver");
		
		//add the cookie to browser
		driver.manage().addCookie(ckoobj);
		
		//fetch all the cookies from the browser
		Set<Cookie>ckCol=driver.manage().getCookies();
		System.out.println("Number of Cookies in the browser is: "+ckCol.size());
		
		for(Cookie ck:ckCol) {
			System.out.println("Cookie Name:  "+ck.getName()+" Cookie Value: "+ck.getValue());
			System.out.println("Cookie Path:  "+ck.getPath()+"  Cookie Domain: "+ck.getDomain());
			System.out.println("Cookie Expiry Date:  "+ck.getExpiry());
		}
		//delete cookie by name
		driver.manage().deleteCookieNamed("testcookie");
		
		//fetch all the cookies from the browser
		Set<Cookie>ckCol2=driver.manage().getCookies();
		System.out.println(" Number of Cookies in the browser after deleting one cookie: "+ckCol2.size());
		
		//delete all the cookies
		driver.manage().deleteAllCookies();
		
		//fetch all cookies after deleting all
		Set<Cookie>ckCol3=driver.manage().getCookies();
		System.out.println(" Number of Cookies in the browser after deleting all cookies: "+ckCol3.size());
		
		
		
		Thread.sleep(1000);

		
		driver.quit();
	}

}


