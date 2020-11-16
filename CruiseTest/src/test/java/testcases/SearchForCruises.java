package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import base.TestBase;
import pages.SearchResultsPage;
import utilities.TestUtil;



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
	public void ChooseASail() throws InterruptedException{
		
		//Click on learn more from a sail
		click("firstCruise_CSS");
		
		waitElement("day1_XPATH");

		Assert.assertTrue(verifyElementDisplayed("day1_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day2_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day3_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day4_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day5_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day6_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day7_XPATH"));
		Assert.assertTrue(verifyElementDisplayed("day8_XPATH"));
		
		//Tap learn more from each day and verify information
		scrollToElement("learnDay1_XPATH");
		waitElementAndClick("learnDay1_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day1Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay2_XPATH");
		waitElementAndClick("learnDay2_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day2Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay3_XPATH");
		waitElementAndClick("learnDay3_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day3Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay4_XPATH");
		waitElementAndClick("learnDay4_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day4Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay5_XPATH");
		waitElementAndClick("learnDay5_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day5Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay6_XPATH");
		waitElementAndClick("learnDay6_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day6Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay7_XPATH");
		waitElementAndClick("learnDay7_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day7Info_XPATH"));
		Thread.sleep(2000);
		
		scrollToElement("learnDay8_XPATH");
		waitElementAndClick("learnDay8_XPATH");
		Assert.assertTrue(verifyElementDisplayed("day8Info_XPATH"));
		Thread.sleep(2000);
		
		//Verify Book now button
		scrollToElement("topBookNow_XPATH");
		Assert.assertTrue(verifyElementDisplayed("topBookNow_XPATH"));
		
		scrollToElement("bottomBookNow_XPATH");
		Assert.assertTrue(verifyElementDisplayed("bottomBookNow_XPATH"));
		
		
		
	}
	
}
