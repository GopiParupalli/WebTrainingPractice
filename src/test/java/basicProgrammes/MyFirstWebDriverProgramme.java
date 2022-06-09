package basicProgrammes;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstWebDriverProgramme {

	public static void main(String[] args) {

		// set the chromedriver.exe file path system class setProperty("Key","Value")
		WebDriverManager.chromedriver().setup();
		// set the chromedriver.exe file path system class setProperty("key","value");
		WebDriver driver = new ChromeDriver();

		// to open the url in the browser use get("url")
		driver.get("https://google.com");
		driver.get("https://www.rediff.com/");
			
		
		/* driver.navigate().to("url");
 2)back():it navigates to previous page
 driver.navigate().back();
 3)forward():it navigates to next page
 driver.navigate().forward();
 4)refresh():it refreshes the current page
 driver.navigate().refresh();*/

		
		
		// how can you fetch the page title using getTitle();
		// datatype variablename=objectreference.returntypenonstaticmethod();

		String pgTitle = driver.getTitle();
		System.out.println("current page title is :" + pgTitle);

		// fetch the addressbar url using getCurrent() returns String type data
		// datatype variablename=objectreference.returntypenonstaticmethod();

		String absUrl = driver.getCurrentUrl();
		System.out.println("absolute url is:" + absUrl);

		// fetch the html source code of the current page===getPagesource() return
		// String
		// datatype variablename =objref.returntypenonstaticmethod();

		String src = driver.getPageSource();
		

		// how to fetch the window id using getWindowHandle() returns String
		// datatype variablename =objref.returntypenonstaticmethod();

		String whobj = driver.getWindowHandle();

		// how to fetch the window id using getWindowHandles() set<String>
		// datatype variablename =objref.returntypenonstaticmethod();
		////collectiontype<datatype>collectionname=webdrivermethods(plural);
		//Set<String>handles=driver.getWindowHandles();
		
		Set<String> whsobj = driver.getWindowHandles();
		//driver.close();
		//driver.quit();
		

	}

}
