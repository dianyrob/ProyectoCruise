package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.google.inject.spi.Element;

import listeners.CustomListeners;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static WebDriverWait wait;

	public static String browser;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				browser = System.getenv("browser");
			}else{
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
					
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 10);
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
		}
	}

	
	
	
	
	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);
	}
	
	public String getText(String locator) {
		String text=null;
		if (locator.endsWith("_CSS")) {
			text=driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_XPATH")) {
			text=driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_ID")) {
			text=driver.findElement(By.id(OR.getProperty(locator))).getText();
		}
		CustomListeners.testReport.get().log(Status.INFO, "Text gotten : " + locator);
		return text;
	}
	
	public String currentMessage(String message) {
		String validateMessage=OR.getProperty(message);
		return validateMessage;
		
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);
	}
	
	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		CustomListeners.testReport.get().log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

			return false;
		}
	}

	
	public Boolean verifyElementDisplayed(String locator) {
		WebElement element = null;
		boolean displayed=false;
		if (locator.endsWith("_CSS")) {
			element=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty(locator))));
			displayed= true;
		} else if (locator.endsWith("_XPATH")) {
			element=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(locator))));
			displayed= true;
		} else if (locator.endsWith("_ID")) {
			element=wait.until(ExpectedConditions.presenceOfElementLocated(By.id(OR.getProperty(locator))));
			displayed= true;
		}else {
			displayed= false;
		}
		
		CustomListeners.testReport.get().log(Status.INFO, "Verify element is displayed : " + locator);
		return displayed;
	}
	public Boolean verifyElementsDisplayed(String locator) {
		List<WebElement> element = null;
		boolean displayed=false;
		if (locator.endsWith("_CSS")) {
			element=driver.findElements(By.cssSelector(OR.getProperty(locator)));
			displayed= true;
		} else if (locator.endsWith("_XPATH")) {
			element=driver.findElements(By.xpath(OR.getProperty(locator)));
			displayed= true;
		} else if (locator.endsWith("_ID")) {
			element=driver.findElements(By.id(OR.getProperty(locator)));
			displayed= true;
		}else {
			displayed= false;
		}
		
		CustomListeners.testReport.get().log(Status.INFO, "Verify elements are displayed : " + locator);
		return displayed;
	}
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
			//CustomListeners.testReport.get().log(Status.FAIL, CustomListeners.testReport.get().addScreenCaptureFromPath(TestUtil.screenshotName));
		}
	}

	public void waitElementAndClick(String locator) {
		WebElement element = null;
		if (locator.endsWith("_CSS")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator))));
		} else if (locator.endsWith("_XPATH")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));
		} else if (locator.endsWith("_ID")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator))));
		}
		element.click();
		CustomListeners.testReport.get().log(Status.INFO, "Wait and click on element : " + locator);
	
	}
	public void waitElement(String locator) {
		WebElement element = null;
		if (locator.endsWith("_CSS")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator))));
		} else if (locator.endsWith("_XPATH")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));
		} else if (locator.endsWith("_ID")) {
			element=wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator))));
		}
		
		CustomListeners.testReport.get().log(Status.INFO, "Wait element : " + locator);
	
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
