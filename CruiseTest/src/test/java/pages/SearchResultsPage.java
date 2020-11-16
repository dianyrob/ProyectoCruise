package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import base.TestBase;
import listeners.CustomListeners;
import utilities.TestUtil;

public class SearchResultsPage extends TestBase{

	
	public static void moveSlidebar() {
		WebElement selection= driver.findElement(By.cssSelector(OR.getProperty("selection_CSS")));
		int selWidth = selection.getSize().getWidth();
        
        int selHeight = selection.getSize().getHeight();        
		
		WebElement slider = driver.findElement(By.cssSelector(OR.getProperty("sliderMax_CSS")));
		
		Point posSlider= slider.getLocation();
		
		int posX= posSlider.getX();
		System.out.println("x: "+posX);
		int posY= posSlider.getY();
		System.out.println("y: "+posY);
		TestUtil.clickAt(900,180);
		//new Actions(driver).dragAndDropBy(slider, selWidth/2,0).perform();
		
		CustomListeners.testReport.get().log(Status.INFO, "Moving slider : sliderMax_CSS");
	}
}
