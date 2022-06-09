package dropdownprogrammes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectMultipleOptionDemo {

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

				// open the https://openwritings.net/sites/default/files/selenium-test-pages/select.html
				driver.get("https://openwritings.net/sites/default/files/selenium-test-pages/select.html");
				
				// Verify the page title
				wait.until(ExpectedConditions.titleContains("Test Page: Select"));
				
				//Identify the multiselection dropdown
				WebElement msdrdn=driver.findElement(By.id("multi-selections"));
				
				// identify the categories dropdown---searchDropdownBox---
				//WebElement catdd=driver.findElement(By.id("searchDropdownBox"));
				
				//create object for select class
				Select sel=new Select(msdrdn);
				
				//select an option by lable text
				sel.selectByVisibleText("January");//January
				
				//select an option by value attribute
				sel.selectByValue("Mar");//march
				
				//select an option by index
				sel.selectByIndex(4);//may
				sel.selectByVisibleText("July");
				sel.selectByValue("Sep");
				sel.selectByIndex(10);
				
				//fecth all selected options
				List<WebElement>seloptlist=sel.getAllSelectedOptions();
				System.out.println("Number of options selected:"+seloptlist.size());
				for(WebElement opt:seloptlist) {
					System.out.println(opt.getText());
				}
				System.out.println("------Selectedoptions printng is finished------");
				
				//deselect an option by lable text
				sel.deselectByVisibleText("January");
				sel.deselectByValue("Mar");
				sel.deselectByIndex(10);
				
				//now fetch all the selected options
				List<WebElement>seloptlist1=sel.getAllSelectedOptions();
				System.out.println("Number of options selected after deselecting :"+seloptlist1.size());
				for(WebElement opt:seloptlist1) {
					System.out.println(opt.getText());
				}
				System.out.println("------Selectedoptions printng is finished after deselecting------");
				
				//deselect all the options
				sel.deselectAll();
				
				//after deselecting all the options-now fetch all the selected options
				List<WebElement>seloptlist2=sel.getAllSelectedOptions();
				System.out.println("Number of options selected after deselecting all :"+seloptlist2.size());
				
				//fetch all the dropdown options - getOptions() returns List<WebElement>
				List<WebElement>opts=sel.getOptions();	
				
				//select last option from dropdown 
				sel.selectByIndex(opts.size()-1);
				System.out.println("selected option from the last position is:"+sel.getFirstSelectedOption().getText());
				
				Thread.sleep(2000);
				
				//Iterate all the dropdown options 
				for(WebElement o:opts) {
				 System.out.println(o.getText());
				}

				
				
				/*
				 * //fecth the selected option
				 * System.out.println("selected option by labletext is:"+sel.
				 * getFirstSelectedOption().getText());
				 * 
				 * Thread.sleep(2000);
				 * 
				 * //select an option by value attribute
				 * sel.selectByValue("search-alias=electronics");
				 * 
				 * //fecth the selected option
				 * System.out.println("selected option by value attribute is :"+sel.
				 * getFirstSelectedOption().getText());
				 * 
				 * Thread.sleep(2000);
				 * 
				 * //select option by index sel.selectByIndex(1);
				 * 
				 * //fetch the selected option
				 * System.out.println("select option by attributevalue is:"+sel.
				 * getFirstSelectedOption().getText());
				 * 
				 * Thread.sleep(2000);
				 * 
				 * //fetch all the dropdown options - getOptions() returns List<WebElement>
				 * List<WebElement>opts=sel.getOptions();
				 * 
				 * //select last option from dropdown 
				 * sel.selectByIndex(opts.size()-1);
				 * System.out.println("selected option from the last position is:"+sel.getFirstSelectedOption().getText());
				 * 
				 * Thread.sleep(2000);
				 * 
				 * //Iterate all the dropdown options 
				 * for(WebElement o:opts) {
				 * System.out.println(o.getText()); }
				 */
				driver.quit();
			
		
		
	}

}
