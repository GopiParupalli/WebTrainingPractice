package dropdownprogrammes;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectRandomOptionDemo {

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

				// open the ramazon home page
				driver.get("https://www.amazon.com.au/");
				
				// Verify the page title---Amazon.com.au: Shop online for Electronics, Apparel, Toys, Books, DVDs & more
				wait.until(ExpectedConditions.titleContains("Amazon.com.au"));
				
				// identify the categories dropdown---searchDropdownBox---
				WebElement catdd=driver.findElement(By.id("searchDropdownBox"));
				
				//Vcreate object for select class
				Select sel=new Select(catdd);
				
				//select an option by lable text
				//sel.selectByVisibleText("Books");
				
				//fecth the selected option
				//System.out.println("selected option by labletext is:"+sel.getFirstSelectedOption().getText());
				
				//Thread.sleep(2000);
				
				//select an option by value attribute
				//sel.selectByValue("search-alias=electronics");
				
				//fecth the selected option
				//System.out.println("selected option by value attribute is :"+sel.getFirstSelectedOption().getText());
				
				//Thread.sleep(2000);
				
				//select option by index
				//sel.selectByIndex(1);
				
				//fetch the selected option
				//System.out.println("select option by attributevalue is:"+sel.getFirstSelectedOption().getText());
				
				//Thread.sleep(2000);
				
				//fetch all the dropdown options - getOptions() returns List<WebElement>
				List<WebElement>opts=sel.getOptions();
				
				//Create object for random class
				Random randob=new Random();
				
				for(int i=1;i<=5;i++) {
					//generate the random number using nextInt(range);
					int randNum=randob.nextInt(opts.size());
					//select last option from dropdown
					sel.selectByIndex(randNum);
					System.out.println(randNum+ "selected option by position is:"+sel.getFirstSelectedOption().getText());
					
					Thread.sleep(2000);

					
				}
				
				//select last option from dropdown
				//sel.selectByIndex(opts.size()-1);
				//System.out.println("selected option from the last position is:"+sel.getFirstSelectedOption().getText());
				
				//Thread.sleep(2000);
				
				//Iterate all the dropdown options
				//for(WebElement o:opts) {
				//	System.out.println(o.getText());
				//}
				driver.quit();
			
		
		
	}

}
