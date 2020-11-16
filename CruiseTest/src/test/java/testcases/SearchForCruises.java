package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import base.TestBase;
import pages.SearchResultsPage;
import utilities.TestUtil;
import utilities.WebListeners;



public class SearchForCruises extends TestBase{

	@Test(priority =1)
	public void SearchBahamasCruise(){
		
		//Close ads
		TestUtil.clickAt(100,100);
		//Close privacy policy
		waitElementAndClick("policy_XPATH");
		System.out.println("Policy closed");
		//Select destination
		waitElementAndClick("destination_ID");
		waitElementAndClick("bahamas_XPATH");
		//Select duration
		waitElementAndClick("duration_ID");
		waitElementAndClick("6to9days_XPATH");
		//Search for cruises
		waitElementAndClick("searchBtn_XPATH");
		//Verify results displayed as a grid
		Assert.assertTrue(verifyElementDisplayed("bahamasResults_CSS"));
		Assert.assertTrue(verifyElementsDisplayed("cruiseCards_CSS"));
		//Click at pricing
		click("pricing_CSS");
		TestUtil.clickAtElement("sliderMax_CSS");
		//Move pricing slider
		SearchResultsPage.moveSlidebar();
		
	}
	
	@Test(priority =2)
	public void ChooseASail(){
		
		//Click on learn more from a sail
		click("firstCruise_CSS");
		
//		WebElement element= driver.findElement(By.cssSelector(""));
//		Actions action = new Actions(driver);
//		action.MoveToElement(elementToClick).Build().Perform();
//		elementToClick.Click();
		
		waitElement("day1_XPATH");
		//Verify 8 days itinerary are displayed
		//*[@id="details"]/div[3]/div[4]
		Assert.assertTrue(verifyElementDisplayed("day1_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day2_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day3_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day4_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day5_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day6_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day7_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day8_XPATH"));
		//Tap learn more frome each day
		//Verify information displayed
		//Verify Book now button
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
