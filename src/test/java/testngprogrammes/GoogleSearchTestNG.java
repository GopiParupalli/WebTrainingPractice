package testngprogrammes;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class GoogleSearchTestNG {

	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@Test(dataProvider = "dp")
	public void searchTest(String s) {
		Reporter.log("Started executing the searchTest method for keyword:  " + s);
		Reporter.log("type the" + s + "keyword in search editbox");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(s);

		Reporter.log("submit on the search editbox");
		driver.findElement(By.xpath("//input[@name='q']")).submit();

		Reporter.log("verify the search results page title - Google Search");
		wait.until(ExpectedConditions.titleContains(s +" - Google Search"));

		Reporter.log("verify the search results count text is present in the webpage or not.//div[@id='result-stats']");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

		Reporter.log("fetch the search results count text and extract the only count from search results count text");
		String txt = driver.findElement(By.id("result-stats")).getText();
		System.out.println("search result text is:" + txt);
		Reporter.log("search result text for" + s + "   is   " + txt);

		// Extract the only count from search results count text");
		// String text="about 1,410,000000 results (0.41 seconds)"
		// using String class-split(delimeter) returns String[]");
		String[] str = txt.split(" ");

		// string[] str=["About","1,410,000,000","results","(0.41,seconds)"]
		// 					0          1           2          3     4

		System.out.println("search result count is:" + str[1]);
		Reporter.log("search result count for  " +s+ "  is  :" +str[1]);

		// String s1=str1].replace(",","");//i..e..1410000000
		String s1 = str[1].replace(",", "");

		// Convert string into long number
		long count = Long.parseLong(s1);
		System.out.println("search result count in primitive format:" + count);
		Reporter.log("search result count for  " + s + "  in primitive format:  " + count);
		Reporter.log("End of searchTest() for:  " + s + "  completed");

	}

	@BeforeMethod
	public void beforeMethod() {

		Reporter.log("Launch the application url");
		driver.get("http://google.com/");

		Reporter.log("wait for the page title---Google");
		wait.until(ExpectedConditions.titleIs("Google"));

		Assertions.assertEquals("Google", driver.getTitle());
		Assertions.assertTrue(driver.getTitle().contains("Google"));

	}

	@AfterMethod
	public void afterMethod() {

		Reporter.log("started executing the @AfterMethod");
		Reporter.log("Clear the cathe");
		driver.manage().deleteAllCookies();
	}

	@DataProvider
	public Object[][] dp() {
		Object[][] data = new Object[3][1];
		data[0][0] = "selenium";
		data[1][0] = "UFI";
		data[2][0] = "cucumber";
		return data;

	}

	

	@BeforeClass
	public void beforeClass() {
		Reporter.log("@BeforeClass is current class level annotation");

		// set the chromedriver.exe file path System.setproperty(p1,p2)or
		// WebDriverManager.chromedriver().setup();

		WebDriverManager.chromedriver().setup();

		Reporter.log("interface refvar=new implementedclass()");
		driver = new ChromeDriver();

		Reporter.log("maximize the window");
		driver.manage().window().maximize();

		Reporter.log("add implicit wait");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

		Reporter.log("open the url in browser");
		driver.get("https://google.com/");

		Reporter.log("create Object for WebDriverWait class");
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));
		
		
		
		
		/*//In Cucumber we can initialize setup in Steps class
		 * //Reading properties
		 *  configProp=new Properties () ; 
		 *  FileInputStream configPropfile=new FileInputStream ("config.properties");
		 * configProp.load(configPropfile); String br=configProp.getProperty("browser");
		 * if (br.equals ("chrome")) {
		 * System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		 * driver=new ChromeDriver();
		 *  } 
		 * else if (br.equals("firefox"))
		 * { 
		 * System.setProperty ("webdriver.gecko.driver",configProp.getProperty("firefoxpath driver = new FirefoxDriver();
		 *  }
		 *  else if(br.equals ("ie"))
		 * Svstem.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
		 * driver = new InternetExplorerDriver();
		 *  }
		 *   Logger=Logger.getLogger("nopComemrce"); //Added logger
		 * PropertyConfigurator.configure("Log4j.properties");//Added logger
		 * Logger.info("******** Launching browser*********");
		 */

	}

	@AfterClass
	public void afterClass() {
		Reporter.log("quit the browser");
		if (driver != null) {
			driver.quit();
		}

	}

	@BeforeTest
	public void beforeTest() {

		Reporter.log("This annotation method will be repeat for every test tag<test name= \"testtag\" >");
		Reporter.log(
				"Before any @Test in the test tag inside execution suite first @BeoreTest annotation method block will be executed");

	}

	@AfterTest
	public void afterTest() {

		Reporter.log(
				"After all @Test in the test tag have completed then @AfterSuite annotation method block will be executed");

	}

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("Started Executing the @BeforeSuite annotation method block");
		Reporter.log("Before any @Test in the suite first @BeoreSuite annotation method block will be executed");

	}

	@AfterSuite
  public void afterSuite() {
	  
	  Reporter.log("Started Executing the @AfetrSuite annotation method block");
	  Reporter.log("After all @Test in the suite have completed finally @AfterSuite method annotation method block will be executed");


  }

}
